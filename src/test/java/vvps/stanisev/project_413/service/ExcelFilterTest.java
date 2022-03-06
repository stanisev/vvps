package vvps.stanisev.project_413.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import vvps.stanisev.project_413.entity.StudentLogEntity;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ExcelFilterTest {

    @InjectMocks
    private ExcelFilter excelFilter;

    @Test
    void shouldCount302Entities() {
        // GIVEN
        int count = 302;

        // WHEN
        Collection<StudentLogEntity> entities = excelFilter.filterExcel();

        // THEN
        assertEquals(count, entities.size());

    }
}
