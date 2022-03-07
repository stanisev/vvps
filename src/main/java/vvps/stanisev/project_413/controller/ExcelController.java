package vvps.stanisev.project_413.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import vvps.stanisev.project_413.entity.Response;
import vvps.stanisev.project_413.entity.StudentLogEntity;
import vvps.stanisev.project_413.service.ExcelFilter;

import java.util.Collection;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelFilter excelFilter;

    @GetMapping("/filter")
    public ResponseEntity<Response> getFilteredData() {
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

    @GetMapping("/filter/count/absolute")
    public ResponseEntity<Response> getAbsoluteCount() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("absolute", excelFilter.filterDataByKey(excelFilter.filterExcel())))
                        .message("Count Absolute frequency:")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/filter/count/relative")
    public ResponseEntity<Response> getPercentCount() {
        Collection<StudentLogEntity> filteredExcel = excelFilter.filterExcel();
        Map<String, Float> filteredDataByKey = excelFilter.filterDataByKey(filteredExcel);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("relative", excelFilter.countPercents(filteredDataByKey, filteredExcel)))
                        .message("Count Relative frequency:")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/filter/count/average")
    public ResponseEntity<Response> getAverageCount() {
        Collection<StudentLogEntity> filteredExcel = excelFilter.filterExcel();
        Map<String, Float> filteredDataByKey = excelFilter.filterDataByKey(filteredExcel);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("average", excelFilter.countAverage(filteredDataByKey, filteredExcel)))
                        .message("Count Average frequency:")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/filter/count/deviation")
    public ResponseEntity<Response> getDeviationCount() {
        Collection<StudentLogEntity> filteredExcel = excelFilter.filterExcel();
        Map<String, Float> filteredDataByKey = excelFilter.filterDataByKey(filteredExcel);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("deviation", excelFilter.deviation(filteredDataByKey, filteredExcel)))
                        .message("Count Standard deviation:")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

}
