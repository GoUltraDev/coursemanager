package co.techo.service;

import co.techo.dto.CourseSigningListDto;
import co.techo.dto.RequestPayload;
import co.techo.repository.ApplyRepository;
import co.techo.repository.CourseRepository;
import co.techo.repository.MemberRepository;
import co.techo.service.interfaces.ListingReportAbstract;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static co.techo.common.CourseManagerConstant.*;

@Service
@Log4j2
public class CourseManagerService implements ListingReportAbstract {

    private final ApplyRepository applyRepository;

    private final CourseRepository courseRepository;

    private final MemberRepository memberRepository;

    private final ReportGeneratorService generator;

    public CourseManagerService(ApplyRepository applyRepository, CourseRepository courseRepository, MemberRepository memberRepository, ReportGeneratorService generator) {
        this.applyRepository = applyRepository;
        this.courseRepository = courseRepository;
        this.memberRepository = memberRepository;
        this.generator = generator;
    }

    @Transactional(rollbackOn = {Exception.class})
    public List<Object> getAllQuery() {
        return applyRepository.selectAll();
    }

    public List<Object> getCourseSigningReport(int courseId) {
        List<Object> courseSigningCertificateList = null;
        if (courseId != 0) {
            courseSigningCertificateList = applyRepository.findCourseSigningCertificate(courseId);
        }
        return courseSigningCertificateList;
    }

    @Override
    public String[][] getCourseConfirmReport(RequestPayload request) {
        String[][] courseConfirmSigningDataList = new String[0][];
        if (request.getRequest_data() != null && request.getRequest_data().getType() == 1 && request.getRequest_data().getDate_diff() >= 0) {
            int courseId = request.getRequest_data().getCourse_id();
            Map<String, String> courseInfo = getCourseInfo(courseId);
            int dateDiff = request.getRequest_data().getDate_diff() >= 0 ? request.getRequest_data().getDate_diff() : 0;
            String gender = request.getRequest_data().getGender().trim().equals("M") ? "ชาย" : "หญิง";
            String courseType = courseInfo.get("courseType");
            switch (courseType) {
                case "1":
                    log.info("CourseConfirmReport 1 is running : {}", COURSE_ANAPANASATI_1DAYS_ENG);
                    courseConfirmSigningDataList = applyRepository.findCourseConfirmAnapanasati1Day(courseId, 500, gender, dateDiff);
                    break;
                case "2":
                    log.info("CourseConfirmReport 2 is running : {}", COURSE_ANAPANASATI_3DAYS_ENG);
                    courseConfirmSigningDataList = applyRepository.findCourseConfirmAnapanasati3Days(courseId, 500, gender, dateDiff);
                    break;
                case "3":
                    log.info("CourseConfirmReport 3 is running : {}", COURSE_ALUMNI_3DAYS_ENG);
                    courseConfirmSigningDataList = applyRepository.findCourseConfirmAlumni3Days(courseId, 500, gender, dateDiff);
                    break;
                case "4":
                    log.info("CourseConfirmReport 4 is running: {}", COURSE_TECHO_VIPASSANA_ENG);
//                    courseSigningCertificateList = applyRepository.findCourseSigningCertificateTecho(courseId, 100, gender);
                    break;
            }
            if (courseConfirmSigningDataList.length != 0) {
                generator.genCourseConfirmCallListExcel(getDataMappingList(courseConfirmSigningDataList), courseInfo, gender);
            } else {
                log.info("There are not data available.");
            }
        }
        return courseConfirmSigningDataList;
    }

    @Override
    public String[][] getVanSigningReport(RequestPayload request) {
        String[][] vanSigningDataList = new String[0][];

        if (request == null || request.getRequest_data() == null || request.getRequest_data().getType() != 3) {
            return vanSigningDataList;
        }

        int courseId = request.getRequest_data().getCourse_id();
        Map<String, String> courseInfo = getCourseInfo(courseId);
        String gender = request.getRequest_data().getGender().trim().equals("M") ? "ชาย" : "หญิง";
        vanSigningDataList = applyRepository.findVanSigningData(courseId, 100, gender);

        if (vanSigningDataList.length != 0) {
            log.info("VanSigning is running.");
            generator.genVanSigningListExcel(getDataMappingList(vanSigningDataList), courseInfo, gender);
        } else {
            log.info("There are not data available.");
        }

        return vanSigningDataList;
    }


    @Override
    public String[][] getCourseSigningReport(RequestPayload request) {
        String[][] courseSigningReportDataList = new String[0][];

        if (request == null || request.getRequest_data() == null || request.getRequest_data().getType() != 2) {
            return courseSigningReportDataList;
        }

        int courseId = request.getRequest_data().getCourse_id();
        Map<String, String> courseInfo = getCourseInfo(courseId);
        String gender = request.getRequest_data().getGender().trim().equals("M") ? "ชาย" : "หญิง";
        String courseType = courseInfo.get("courseType");

        switch (courseType) {
            case "1":
                log.info("CourseSigningReport 1 is running : {}", COURSE_ANAPANASATI_1DAYS_ENG);
                courseSigningReportDataList = applyRepository.findCourseSigningCertificateAnapanasati1Day(courseId, 100, gender);
                break;
            case "2":
                log.info("CourseSigningReport 2 is running : {}", COURSE_ANAPANASATI_3DAYS_ENG);
                courseSigningReportDataList = applyRepository.findCourseSigningCertificateAnapanasati3Days(courseId, 100, gender);
                break;
            case "3":
                log.info("CourseSigningReport 3 is running : {}", COURSE_ALUMNI_3DAYS_ENG);
                courseSigningReportDataList = applyRepository.findCourseSigningCertificateAlumni3Days(courseId, 100, gender);
                break;
            case "4":
                log.info("CourseSigningReport 4 is running: {}", COURSE_TECHO_VIPASSANA_ENG);
                courseSigningReportDataList = applyRepository.findCourseSigningCertificateTecho(courseId, 100, gender);
                break;
            default:
        }

        if (courseSigningReportDataList.length != 0) {
            generator.genCourseSigningListExcel(getDataMappingList(courseSigningReportDataList), courseInfo, gender);
        } else {
            log.info("There are not data available.");
        }

        return courseSigningReportDataList;
    }

//    @Override
//    public String[][] getNameTag(RequestPayload request) {
//        return new String[0][];
//    }

    private List<CourseSigningListDto> getDataMappingList(String[][] data) {
        List<CourseSigningListDto> resultList = new ArrayList<>();
        for (String[] obj : data) {
            CourseSigningListDto courseSigningCertificateDto = new CourseSigningListDto();
            log.info(Arrays.toString(obj));
//            courseSigningCertificateDto.setDiscipleStatus(obj[0]);
//            courseSigningCertificateDto.setRegisterDate(obj[1]);
            courseSigningCertificateDto.setNumber(null);
            courseSigningCertificateDto.setPreface(obj[2]);
            courseSigningCertificateDto.setFirstName(obj[3]);
            courseSigningCertificateDto.setLastName(obj[4]);
            courseSigningCertificateDto.setNickName(obj[5]);
            courseSigningCertificateDto.setAge(obj[6]);
            courseSigningCertificateDto.setDiscipleStatusShort(obj[7]);
            courseSigningCertificateDto.setVanStatus(obj[8]);
            courseSigningCertificateDto.setPhoneNumber(obj[9]);
            courseSigningCertificateDto.setHouse(null);
            courseSigningCertificateDto.setSeat(null);
            courseSigningCertificateDto.setSign(null);
//            courseSigningCertificateDto.setLastCourse(obj[10]);
            resultList.add(courseSigningCertificateDto);
        }
        return resultList;
    }


    Map<String, String> getCourseInfo(int course_id) {
        Map<String, String> info = new HashMap<>();
        String[][] courseEntity = courseRepository.findCourseType2(course_id);
        for (String[] entity : courseEntity) {
            String lastValue = entity[entity.length - 1];
            if (lastValue.equals(CATEGORY_ID_ANAPANASATI_1DAYS)) {
                info.put("courseType", "1");
                info.put("courseName", entity[3]);
                info.put("dateStart", entity[4]);
                info.put("dateEnd", entity[5]);
                info.put("categoryId", lastValue);
            } else if (lastValue.equals(CATEGORY_ID_ANAPANASATI_3DAYS)) {
                info.put("courseType", "2");
                info.put("courseName", entity[3]);
                info.put("dateStart", entity[4]);
                info.put("dateEnd", entity[5]);
                info.put("categoryId", lastValue);
            } else if (lastValue.equals(CATEGORY_ID_ALUMNI_3DAYS)) {
                info.put("courseType", "3");
                info.put("courseName", entity[3]);
                info.put("dateStart", entity[4]);
                info.put("dateEnd", entity[5]);
                info.put("categoryId", lastValue);
            } else if (lastValue.equals(CATEGORY_ID_TECHO_VIPASSANA)) {
                info.put("courseType", "4");
                info.put("courseName", entity[3]);
                info.put("dateStart", entity[4]);
                info.put("dateEnd", entity[5]);
                info.put("categoryId", lastValue);
            }
        }
        return info;
    }

    private Map<String, String> getCourseInfo2(int course_id) {
        Map<String, String> info = new HashMap<>();
        String[][] courseEntity = courseRepository.findCourseType2(course_id);

        if (courseEntity == null || courseEntity.length == 0) {
            return info;
        }

        String[] entity = courseEntity[0];
        String lastValue = entity[entity.length - 1];

        if (CATEGORY_ID_ANAPANASATI_1DAYS.equals(lastValue)) {
            info.put("courseType", "1");
            info.put("courseName", entity[3]);
            info.put("dateStart", entity[4]);
            info.put("dateEnd", entity[5]);
            info.put("categoryId", lastValue);
        } else if (CATEGORY_ID_ANAPANASATI_3DAYS.equals(lastValue)) {
            info.put("courseType", "2");
            info.put("courseName", entity[3]);
            info.put("dateStart", entity[4]);
            info.put("dateEnd", entity[5]);
            info.put("categoryId", lastValue);
        } else if (CATEGORY_ID_ALUMNI_3DAYS.equals(lastValue)) {
            info.put("courseType", "3");
            info.put("courseName", entity[3]);
            info.put("dateStart", entity[4]);
            info.put("dateEnd", entity[5]);
            info.put("categoryId", lastValue);
        } else if (CATEGORY_ID_TECHO_VIPASSANA.equals(lastValue)) {
            info.put("courseType", "4");
            info.put("courseName", entity[3]);
            info.put("dateStart", entity[4]);
            info.put("dateEnd", entity[5]);
            info.put("categoryId", lastValue);
        }

        return info;
    }

}
