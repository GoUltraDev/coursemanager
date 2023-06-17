package co.techo.common;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CourseManagerConstant {

    public CourseManagerConstant() {
        throw new RuntimeException("");
    }

    public static final String PRINT_LIST_TELEPHONE = "";

    // template constant
    public static final String COURSE_TECHO_TITLE = "เตโชวิปัสสนาสถาน";
    public static final String GENDER_STATUS_MALE = "ศิษย์ชาย";
    public static final String GENDER_STATUS_FEMALE = "ศิษย์หญิง";
    public static final String COURSE_SIGNING_FILENAME_PREFIX = "CourseSigning";
    public static final String COURSE_VAN_SIGNING_FILENAME_PREFIX = "VanSigning";
    public static final String COURSE_CONFIRM_FILENAME_PREFIX = "CourseConfirm";

//    public static String COURSE_ANAPANASATI_1DAYS_THAI = "คอร์สอานาปานสติ ๑ วัน";
    public static final String COURSE_ANAPANASATI_1DAYS_ENG = "Anapanasati1Day";
    public static final String CATEGORY_ID_ANAPANASATI_1DAYS = "8";
//    public static String COURSE_ANAPANASATI_3DAYS_THAI = "คอร์สอานาปานสติ";
    public static final String COURSE_ANAPANASATI_3DAYS_ENG = "Anapanasati3Days";
    public static final String CATEGORY_ID_ANAPANASATI_3DAYS = "5";
//    public static String COURSE_ALUMNI_3DAYS_NAME = "คอร์สศิษย์เก่า (๓ วัน)";
    public static final String COURSE_ALUMNI_3DAYS_ENG = "Alumni3Days";
    public static final String CATEGORY_ID_ALUMNI_3DAYS = "6";
//    public static String COURSE_TECHO_VIPASSANA_NAME = "คอร์สเตโชวิปัสสนา";
    public static final String COURSE_TECHO_VIPASSANA_ENG = "TechoVipassana";
    public static final String CATEGORY_ID_TECHO_VIPASSANA = "1";

    public static final String GENDER_MALE_THAI = "ชาย";
    public static final String GENDER_FEMALE_THAI = "หญิง";
    public static final String GENDER_MALE_ENG = "Male";
    public static final String GENDER_FEMALE_ENG = "Female";

    public static String getDateTime() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String result = now.format(format);
        return result + " (Asia/Bangkok) ";
    }
}
