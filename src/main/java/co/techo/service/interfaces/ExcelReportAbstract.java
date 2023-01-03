package co.techo.service.interfaces;

import co.techo.dto.CourseSigningListDto;

import java.util.List;
import java.util.Map;

public interface ExcelReportAbstract {
    void genCourseConfirmCallListExcel(List<CourseSigningListDto> resultList, Map<String, String> courseInfo, String gender);

    void genVanSigningListExcel(List<CourseSigningListDto> resultList, Map<String, String> courseInfo, String gender);

    void genCourseSigningListExcel(List<CourseSigningListDto> resultList, Map<String, String> courseInfo, String gender);

    public void generateNameTag();
}
