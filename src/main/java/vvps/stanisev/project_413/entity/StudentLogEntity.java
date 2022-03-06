package vvps.stanisev.project_413.entity;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;
import lombok.Data;

@Data
public class StudentLogEntity {

    @ExcelRow
    private int rowIndex;

    @ExcelCellName("Time")
    private String time;
    @ExcelCellName("Event context")
    private String eventContext;
    @ExcelCellName("Component")
    private String component;
    @ExcelCellName("Event name")
    private String eventName;
    @ExcelCellName("Description")
    private String description;
}
