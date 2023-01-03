package co.techo.service.interfaces;

import co.techo.dto.RequestPayload;

public interface ListingReportAbstract {
    String[][] getVanSigningReport(RequestPayload request);
    String[][] getCourseConfirmReport(RequestPayload request);
    String[][] getCourseSigningReport(RequestPayload request);

//    String[][] getNameTag(RequestPayload request);
}
