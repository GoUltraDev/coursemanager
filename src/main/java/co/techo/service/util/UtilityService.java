package co.techo.service.util;

public class UtilityService {

    public static String getThaiMonthFromNumber(String month) {
        String res = "";
        int monthInt = Integer.parseInt(month);
        switch (monthInt)  {
            case 1:
                res = "มกราคม";
                break;
            case 2:
                res = "กุมภาพันธ์";
                break;
            case 3:
                res = "มีนาคม";
                break;
            case 4:
                res = "เมษายน";
                break;
            case 5:
                res = "พฤษภาคม";
                break;
            case 6:
                res = "มิถุนายน";
                break;
            case 7:
                res = "กรกฎาคม";
                break;
            case 8:
                res = "สิงหาคม";
                break;
            case 9:
                res = "กันยายน";
                break;
            case 10:
                res = "ตุลาคม";
                break;
            case 11:
                res = "พฤศจิกายน";
                break;
            case 12:
                res = "ธันวาคม";
                break;
        }
        return res;
    }
}
