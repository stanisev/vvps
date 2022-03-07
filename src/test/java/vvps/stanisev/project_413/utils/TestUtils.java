package vvps.stanisev.project_413.utils;

import org.springframework.http.ResponseEntity;
import vvps.stanisev.project_413.entity.Response;
import vvps.stanisev.project_413.entity.StudentLogEntity;
import vvps.stanisev.project_413.service.ExcelFilter;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

public class TestUtils {
    public static final float DEVIATION = 26.284765F;
    public static final float AVERAGE =  100.666664F;
    public static final int TOTAL_ENTITIES = 302;
    public static final String MAP_KEY = "Wiki: Избор нa тема за проект (40 т.)";

    public static ResponseEntity<Response> mockTestResponse(Collection<StudentLogEntity> data) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("filtered", data))
                        .message("Filtered Data retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    public static ResponseEntity<Response> mockAbsoluteResponse(ExcelFilter filter) {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(of("absolute", filter.filterDataByKey(filter.filterExcel())))
                            .message("Count Absolute frequency:")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
    }

    public static ResponseEntity<Response> mockRelativeResponse(ExcelFilter filter) {
        Collection<StudentLogEntity> filteredExcel = filter.filterExcel();
        Map<String, Float> filteredDataByKey = filter.filterDataByKey(filteredExcel);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("relative", filter.countPercents(filteredDataByKey, filteredExcel)))
                        .message("Count Relative frequency:")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    public static ResponseEntity<Response> mockAverageCountResponse(ExcelFilter filter) {
        Collection<StudentLogEntity> filteredExcel = filter.filterExcel();
        Map<String, Float> filteredDataByKey = filter.filterDataByKey(filteredExcel);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("average", filter.countAverage(filteredDataByKey, filteredExcel)))
                        .message("Count Average frequency:")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    public static ResponseEntity<Response> mockDeviationCountResponse(ExcelFilter filter) {
        Collection<StudentLogEntity> filteredExcel = filter.filterExcel();
        Map<String, Float> filteredDataByKey = filter.filterDataByKey(filteredExcel);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("deviation", filter.deviation(filteredDataByKey, filteredExcel)))
                        .message("Count Standard deviation:")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    public static Set<String> filterKeys() {
        return Set.of("Wiki: Избор нa тема за проект (40 т.)",
                "Wiki: Курсова задача/ реферат (30 т.)",
                "Wiki: Защита на проект");
    }

    public static Map<String, Float> mockEntity() {
        return Map.of("Wiki: Избор нa тема за проект (40 т.)", 136.0F);
    }
}
