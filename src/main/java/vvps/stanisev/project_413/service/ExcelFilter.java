package vvps.stanisev.project_413.service;

import com.poiji.bind.Poiji;
import org.springframework.stereotype.Service;
import vvps.stanisev.project_413.entity.StudentLogEntity;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static vvps.stanisev.project_413.constants.FilterConstants.FILE_PATH;
import static vvps.stanisev.project_413.constants.FilterConstants.FILTER;

@Service
public class ExcelFilter {
    public Collection<StudentLogEntity> filterExcel() {
        File file = new File(FILE_PATH);
        List<StudentLogEntity> entities = Poiji.fromExcel(file, StudentLogEntity.class);

        return entities
                .stream()
                .filter(eventName -> FILTER.equals(eventName.getEventName()))
                .collect(Collectors.toList());
    }

    public Map<String, Float> filterDataByKey(Collection<StudentLogEntity> entities) {
        Set<String> keys = new HashSet<>();

        entities.forEach(entity -> keys.add(entity.getEventContext()));

        Map<String, Float> map = new HashMap<>();

        entities.forEach(entity -> keys.forEach(key -> {
            if (key.equals(entity.getEventContext())) {
                if (map.containsKey(key)) {
                    map.put(key, (map.get(key) + 1));
                } else {
                    map.put(key, 1F);
                }
            }
        }));

        return map;
    }

    public Map<String, String> countPercents(Map<String, Float> map, Collection<StudentLogEntity> entities) {
        int countTotalEntities = entities.size();

        Map<String, String> shallowCopy = new HashMap<>();
        Set<Map.Entry<String, Float>> entries = map.entrySet();
        for (Map.Entry<String, Float> mapEntry : entries) {
            shallowCopy.put(mapEntry.getKey(), String.format("%.2f", mapEntry.getValue() / countTotalEntities * 100) + " %");
        }

        return shallowCopy;
    }

    public Float countAverage(Map<String, Float> map, Collection<StudentLogEntity> entities) {
        float size = map.size();

        return entities.size() / size;
    }

    public Float deviation(Map<String, Float> map, Collection<StudentLogEntity> entities) {
        float average = countAverage(map, entities);
        map.entrySet().forEach(set -> set.setValue((float) Math.pow((set.getValue() - average), 2)));

        float count = 0;
        Set<Map.Entry<String, Float>> entries = map.entrySet();
        for (Map.Entry<String, Float> mapEntry : entries) {
            count += mapEntry.getValue();
        }

        float stats = count / map.size();

        return (float) Math.sqrt(stats);
    }
}
