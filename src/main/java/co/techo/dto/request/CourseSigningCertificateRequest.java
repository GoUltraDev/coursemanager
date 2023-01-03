package co.techo.dto.request;

import lombok.Data;

@Data
public class CourseSigningCertificateRequest {

    private Integer course_id;

    private Integer type;

    private String gender;

    private Integer date_diff;
}
