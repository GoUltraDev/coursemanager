package co.techo.dto;

import co.techo.dto.request.CourseSigningCertificateRequest;
import lombok.Data;

@Data
public class RequestPayload {
    private CourseSigningCertificateRequest request_data;
}
