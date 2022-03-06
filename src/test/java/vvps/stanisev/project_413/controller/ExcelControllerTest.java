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

import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.http.HttpStatus.OK;

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
    void shouldCount302Entities() {
        // GIVEN
        int count = 302;

        // WHEN
        ResponseEntity<Response> entities = excelController.getFilteredData();

        List<StudentLogEntity> filtered = (List<StudentLogEntity>) entities.getBody().getData().get("filtered");
        // THEN
        assertEquals(count, filtered.size());
    }

    @Test
    void shouldShowTheFirstFilteredEntity() {
        // GIVEN
        ResponseEntity<Response> mockResponse = mockTestResponse();

        // WHEN
        ResponseEntity<Response> entities = excelController.getFilteredData();

        // THEN
        assertEquals(mockResponse.getBody().getData(), entities.getBody().getData());
        assertEquals(mockResponse.getBody().getMessage(), entities.getBody().getMessage());
        assertEquals(mockResponse.getBody().getStatus(), entities.getBody().getStatus());
        assertEquals(mockResponse.getBody().getStatusCode(), entities.getBody().getStatusCode());
        assertEquals(mockResponse.getBody().getTimeStamp().getYear(), entities.getBody().getTimeStamp().getYear());
        assertNull(entities.getBody().getReason());
        assertNull(entities.getBody().getDeveloperMessage());
    }

    private ResponseEntity<Response> mockTestResponse() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("filtered", excelFilter.filterExcel()))
                        .message("Filtered Data retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
