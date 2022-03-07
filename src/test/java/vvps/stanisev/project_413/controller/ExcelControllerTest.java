package vvps.stanisev.project_413.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import vvps.stanisev.project_413.entity.Response;
import vvps.stanisev.project_413.entity.StudentLogEntity;
import vvps.stanisev.project_413.service.ExcelFilter;
import vvps.stanisev.project_413.utils.TestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class ExcelControllerTest {

    @Mock
    ExcelFilter excelFilter = new ExcelFilter();

    @InjectMocks
    ExcelController excelController = new ExcelController(excelFilter);

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldTestCountTotalEntitiesOf302() {
        // GIVEN
        int count = 302;

        // WHEN
        ResponseEntity<Response> entities = excelController.getFilteredData();

        List<StudentLogEntity> filtered = (List<StudentLogEntity>) entities.getBody().getData().get("filtered");
        // THEN
        assertEquals(count, filtered.size());
    }

    @Test
    void shouldTestShowTheFirstFilteredEntity() {
        // GIVEN
        ResponseEntity<Response> mockResponse = TestUtils.mockTestResponse(excelFilter.filterExcel());

        // WHEN
        ResponseEntity<Response> entities = excelController.getFilteredData();

        // THEN
        AsserEquals(mockResponse, entities);
    }

    @Test
    void shouldTestAbsoluteMethodResponse() {
        // GIVEN
        ResponseEntity<Response> mockResponse = TestUtils.mockAbsoluteResponse(excelFilter);

        // WHEN
        ResponseEntity<Response> entities = excelController.getAbsoluteCount();

        // THEN
        AsserEquals(mockResponse, entities);
    }

    @Test
    void shouldTestRelativeMethodResponse() {
        // GIVEN
        ResponseEntity<Response> mockResponse = TestUtils.mockRelativeResponse(excelFilter);

        // WHEN
        ResponseEntity<Response> entities = excelController.getPercentCount();

        // THEN
        AsserEquals(mockResponse, entities);
    }

    @Test
    void shouldTestAverageCountMethodResponse() {
        // GIVEN
        ResponseEntity<Response> mockResponse = TestUtils.mockAverageCountResponse(excelFilter);

        // WHEN
        ResponseEntity<Response> entities = excelController.getAverageCount();

        // THEN
        AsserEquals(mockResponse, entities);
    }

    @Test
    void shouldTestDeviationCountMethodResponse() {
        // GIVEN
        ResponseEntity<Response> mockResponse = TestUtils.mockDeviationCountResponse(excelFilter);

        // WHEN
        ResponseEntity<Response> entities = excelController.getDeviationCount();

        // THEN
        AsserEquals(mockResponse, entities);
    }

    private void AsserEquals(ResponseEntity<Response> mockResponse, ResponseEntity<Response> entities) {
        assertEquals(mockResponse.getBody().getData(), entities.getBody().getData());
        assertEquals(mockResponse.getBody().getMessage(), entities.getBody().getMessage());
        assertEquals(mockResponse.getBody().getStatus(), entities.getBody().getStatus());
        assertEquals(mockResponse.getBody().getStatusCode(), entities.getBody().getStatusCode());
        assertEquals(mockResponse.getBody().getTimeStamp().getYear(), entities.getBody().getTimeStamp().getYear());
        assertNull(entities.getBody().getReason());
        assertNull(entities.getBody().getDeveloperMessage());
    }
}
