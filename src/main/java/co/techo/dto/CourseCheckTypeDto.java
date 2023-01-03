package co.techo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CourseCheckTypeDto {
    Long id;
    String state;
    String location;
    String category;
    Date date_start;
    Date date_end;
}
