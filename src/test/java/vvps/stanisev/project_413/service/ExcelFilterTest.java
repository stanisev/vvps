package vvps.stanisev.project_413.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import vvps.stanisev.project_413.entity.StudentLogEntity;
import vvps.stanisev.project_413.utils.TestUtils;

import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static vvps.stanisev.project_413.utils.TestUtils.MAP_KEY;

@SpringBootTest
public class ExcelFilterTest {

    @InjectMocks
    private ExcelFilter excelFilter;

    @Test
    void shouldCount302Entities() {
        // GIVEN
        int count = TestUtils.TOTAL_ENTITIES;

        // WHEN
        Collection<StudentLogEntity> entities = excelFilter.filterExcel();

        // THEN
        assertEquals(count, entities.size());
    }

    @Test
    void shouldCountAverageValueForFilteredData() {
        // GIVEN
        float expectedAverage = TestUtils.AVERAGE;

        Collection<StudentLogEntity> filteredExcel = excelFilter.filterExcel();
        Map<String, Float> filteredDataByKey = excelFilter.filterDataByKey(filteredExcel);

        // WHEN
        Float actualAverage = excelFilter.countAverage(filteredDataByKey, filteredExcel);

        // THEN
        assertEquals(expectedAverage, actualAverage);
        assertEquals(filteredDataByKey.get(MAP_KEY),
                TestUtils.mockEntity().get(MAP_KEY));
    }

    @Test
    void shouldCountDistinctKeysOfFilteredColumns() {
        // GIVEN
        int keysCount = TestUtils.filterKeys().size();

        // WHEN
        Collection<StudentLogEntity> filteredExcel = excelFilter.filterExcel();
        Map<String, Float> filteredDataByKey = excelFilter.filterDataByKey(filteredExcel);
        int dataCount = excelFilter.countPercents(filteredDataByKey, filteredExcel).size();

        // THEN
        assertEquals(keysCount, dataCount);
    }

    @Test
    void shouldCountDeviationValue() {
        // GIVEN
        float expectedDeviation = TestUtils.DEVIATION;

        // WHEN
        Collection<StudentLogEntity> filteredExcel = excelFilter.filterExcel();
        Map<String, Float> filteredDataByKey = excelFilter.filterDataByKey(filteredExcel);
        float actualDeviation = excelFilter.deviation(filteredDataByKey, filteredExcel);

        // THEN
        assertEquals(expectedDeviation, actualDeviation);
    }
}
