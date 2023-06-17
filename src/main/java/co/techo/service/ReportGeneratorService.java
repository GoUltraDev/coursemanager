package co.techo.service;

import co.techo.common.Config;
import co.techo.dto.CourseSigningListDto;
import co.techo.service.interfaces.ExcelReportAbstract;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.techo.common.CourseManagerConstant.*;
import static co.techo.service.util.UtilityService.getThaiMonthFromNumber;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReportGeneratorService implements ExcelReportAbstract {

    public static final String SHEET = "Sheet";
    public static final String ZERO_TIME_REPLACED = "00:00:00.0";
    public static final String PUNCTUATION_MARK = "-";
    private final Config config;

    @Override
    public void genCourseConfirmCallListExcel(List<CourseSigningListDto> resultList, Map<String, String> courseInfo, String gender) {
        try {

            HSSFWorkbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet(SHEET);

            Map<String, CellStyle> styles = createStyles(workbook);

            setSheetSpec(sheet);
            setConfirmTitleRow(courseInfo, gender, sheet, styles);
            setHeaderConfirmRow(sheet, styles);
            setDataConfirmRow(sheet, resultList, styles);

            String courseName = getCourseName(courseInfo.get("categoryId"));
            String exportFullPath = getExportFullPath(COURSE_CONFIRM_FILENAME_PREFIX, courseName, gender);

            try (FileOutputStream fileOut = new FileOutputStream(exportFullPath)) {
                workbook.write(fileOut);
                workbook.close();
                log.info("CourseConfirmExcel has been generated!");
            }
        } catch (Exception e) {
            log.error("Error generating CourseConfirmExcel", e);
        }
    }

    private String getCourseName(String categoryId) {
        String res;
        switch (categoryId) {
            case "8":
                res = COURSE_ANAPANASATI_1DAYS_ENG;
                break;
            case "5":
                res = COURSE_ANAPANASATI_3DAYS_ENG;
                break;
            case "6":
                res = COURSE_ALUMNI_3DAYS_ENG;
                break;
            case "1":
                res = COURSE_TECHO_VIPASSANA_ENG;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + categoryId);
        }
        return res;
    }

    @Override
    public void genVanSigningListExcel(List<CourseSigningListDto> resultList, Map<String, String> courseInfo, String gender) {
        try {

            HSSFWorkbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet(SHEET);

            Map<String, CellStyle> styles = createStyles(workbook);

            setSheetSpec(sheet);
            setTitleRow(sheet, courseInfo, gender, styles);
            setHeaderRow(sheet, styles);
            setDataRow(sheet, resultList, styles);

            String courseName = getCourseName(courseInfo.get("categoryId"));
            String exportFullPath = getExportFullPath(COURSE_VAN_SIGNING_FILENAME_PREFIX, courseName, gender);
            FileOutputStream fileOut = new FileOutputStream(exportFullPath);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            log.info("VanSigningExcel has been generated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void genCourseSigningListExcel(List<CourseSigningListDto> resultList, Map<String, String> courseInfo, String gender) {
        try {

            HSSFWorkbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet");

            Map<String, CellStyle> styles = createStyles(workbook);

            setSheetSpec(sheet);
            setTitleRow(sheet, courseInfo, gender, styles);
            setHeaderRow(sheet, styles);
            setDataRow(sheet, resultList, styles);

            String courseName = getCourseName(courseInfo.get("categoryId"));
            String exportFullPath = getExportFullPath(COURSE_SIGNING_FILENAME_PREFIX, courseName, gender);
            FileOutputStream fileOut = new FileOutputStream(exportFullPath);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            log.info("CourseSigningExcel has been generated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDateStringTitle(Map<String, String> courseInfo) {
        String[] dateStart = courseInfo.get("dateStart").replace(ZERO_TIME_REPLACED, "").split(PUNCTUATION_MARK, 3);
        String[] dateEnd = courseInfo.get("dateEnd").replace(ZERO_TIME_REPLACED, "").split(PUNCTUATION_MARK, 3);
        String[] thaiYear = courseInfo.get("dateEnd").replace(ZERO_TIME_REPLACED, "").split(PUNCTUATION_MARK, 3);
        int yearInt = Integer.parseInt(thaiYear[0]);
        String thaiMonth = getThaiMonthFromNumber(dateEnd[1]);
        return "วันที่ " + dateStart[2].trim() + " - " + dateEnd[2].trim() + " " + thaiMonth + " " + (yearInt + 543);
    }

    private void setSheetSpec(Sheet sheet) {
        PrintSetup sp = sheet.getPrintSetup();
        sp.setFitHeight((short) 0.75);
        sp.setFitWidth((short) 0.25);
        sp.setPaperSize(PrintSetup.A4_PAPERSIZE);
    }

    private void setConfirmTitleRow(Map<String, String> courseInfo, String gender, Sheet sheet, Map<String, CellStyle> styles) {
        Row titleRow1 = sheet.createRow(0);
        titleRow1.setHeightInPoints(93);
        Cell titleCellLine1 = titleRow1.createCell(0);
        titleCellLine1.setCellValue(COURSE_TECHO_TITLE);
        titleCellLine1.setCellStyle(styles.get("title1"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$J$1"));

        Row titleRow2 = sheet.createRow(1);
        titleRow2.setHeightInPoints(44);
        Cell titleCellLine2 = titleRow2.createCell(0);
        titleCellLine2.setCellValue(courseInfo.get("courseName"));
        titleCellLine2.setCellStyle(styles.get("title2"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$J$2"));

        Row titleRow3 = sheet.createRow(2);
        titleRow3.setHeightInPoints(44);
        Cell titleCellLine3 = titleRow3.createCell(0);
        titleCellLine3.setCellValue(getDateStringTitle(courseInfo));
        titleCellLine3.setCellStyle(styles.get("title3"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$J$3"));

        Row titleRow4 = sheet.createRow(3);
        titleRow4.setHeightInPoints(68);
        Cell titleCellLine4 = titleRow4.createCell(0);
        titleCellLine4.setCellValue(gender.equals(GENDER_MALE_THAI) ? GENDER_STATUS_MALE : GENDER_STATUS_FEMALE);
        titleCellLine4.setCellStyle(styles.get("title4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$J$4"));
    }

    private void setTitleRow(Sheet sheet, Map<String, String> courseInfo, String gender, Map<String, CellStyle> styles) {
        Row titleRow1 = sheet.createRow(0);
        titleRow1.setHeightInPoints(93);
        Cell titleCellLine1 = titleRow1.createCell(0);
        titleCellLine1.setCellValue(COURSE_TECHO_TITLE);
        titleCellLine1.setCellStyle(styles.get("title1"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$L$1"));

        Row titleRow2 = sheet.createRow(1);
        titleRow2.setHeightInPoints(44);
        Cell titleCellLine2 = titleRow2.createCell(0);
        titleCellLine2.setCellValue(courseInfo.get("courseName"));
        titleCellLine2.setCellStyle(styles.get("title2"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$L$2"));

        Row titleRow3 = sheet.createRow(2);
        titleRow3.setHeightInPoints(44);
        Cell titleCellLine3 = titleRow3.createCell(0);
        titleCellLine3.setCellValue(getDateStringTitle(courseInfo));
        titleCellLine3.setCellStyle(styles.get("title3"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$L$3"));

        Row titleRow4 = sheet.createRow(3);
        titleRow4.setHeightInPoints(68);
        Cell titleCellLine4 = titleRow4.createCell(0);
        titleCellLine4.setCellValue(gender.equals(GENDER_MALE_THAI) ? GENDER_STATUS_MALE : GENDER_STATUS_FEMALE);
        titleCellLine4.setCellStyle(styles.get("title4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$L$4"));
    }

    private String getExportFullPath(String prefix, String courseName, String gender) {
        String fileName = config.getCourseSigningFileName().trim().replace("{$courseName}", courseName).replace("{$prefix}", prefix);
        String path = config.getExportPath().trim().concat("/");
        String fileNameModified;
        if (gender.equals(GENDER_FEMALE_THAI)) {
            fileNameModified = fileName.replace("{$gender}", GENDER_FEMALE_ENG);
        } else {
            fileNameModified = fileName.replace("{$gender}", GENDER_MALE_ENG);
        }
        return path.concat(fileNameModified);
    }

    private void setDataRow(Sheet sheet, List<CourseSigningListDto> resultList, Map<String, CellStyle> styles) {
        int rowInitial = 5;
        int rowNumber = 1; // fix start in no.1

        for (CourseSigningListDto dto : resultList) {
            Row rowData = sheet.createRow(rowInitial);
//            rowData.setHeightInPoints((short)50);
            Cell c0 = rowData.createCell(0);
            c0.setCellValue(rowNumber);
            c0.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(0);
            Cell c1 = rowData.createCell(1);
            c1.setCellValue(dto.getPreface());
            c1.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(1);
            Cell c2 = rowData.createCell(2);
            c2.setCellValue(dto.getFirstName());
            c2.setCellStyle(styles.get("dataFont2"));
            sheet.autoSizeColumn(2);
            Cell c3 = rowData.createCell(3);
            c3.setCellValue(dto.getLastName());
            c3.setCellStyle(styles.get("dataFont2"));
            sheet.autoSizeColumn(3);
            Cell c4 = rowData.createCell(4);
            c4.setCellValue(dto.getNickName());
            c4.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(4);
            Cell c5 = rowData.createCell(5);
            c5.setCellValue(dto.getAge());
            c5.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(5);
            Cell c6 = rowData.createCell(6);
            c6.setCellValue(dto.getDiscipleStatusShort());
            c6.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(6);
            Cell c7 = rowData.createCell(7);
            c7.setCellValue(dto.getVanStatus().equals("N") ? "" : "Y");
            c7.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(7);
            Cell c8 = rowData.createCell(8);
            c8.setCellValue(dto.getPhoneNumber());
            c8.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(8);
            Cell c9 = rowData.createCell(9);
            c9.setCellValue("");
            c9.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(9);
            Cell c10 = rowData.createCell(10);
            c10.setCellValue("");
            c10.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(10);
            Cell c11 = rowData.createCell(11);
            c11.setCellValue("");
            c11.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(11);
            rowNumber++;
            rowInitial++;
        }
    }

    private void setDataConfirmRow(Sheet sheet, List<CourseSigningListDto> resultList, Map<String, CellStyle> styles) {
        int rowInitial = 5;
        int rowNumber = 1; // fix start in no.1

        for (CourseSigningListDto dto : resultList) {
            Row rowData = sheet.createRow(rowInitial);
//            rowData.setHeightInPoints((short)50);
            Cell c0 = rowData.createCell(0);
            c0.setCellValue(rowNumber);
            c0.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(0);
            Cell c1 = rowData.createCell(1);
            c1.setCellValue(dto.getPreface());
            c1.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(1);
            Cell c2 = rowData.createCell(2);
            c2.setCellValue(dto.getFirstName());
            c2.setCellStyle(styles.get("dataFont2"));
            sheet.autoSizeColumn(2);
            Cell c3 = rowData.createCell(3);
            c3.setCellValue(dto.getLastName());
            c3.setCellStyle(styles.get("dataFont2"));
            sheet.autoSizeColumn(3);
            Cell c4 = rowData.createCell(4);
            c4.setCellValue(dto.getNickName());
            c4.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(4);
            Cell c5 = rowData.createCell(5);
            c5.setCellValue(dto.getAge());
            c5.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(5);
            Cell c6 = rowData.createCell(6);
            c6.setCellValue(dto.getDiscipleStatusShort());
            c6.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(6);
            Cell c7 = rowData.createCell(7);
            c7.setCellValue(dto.getVanStatus().equals("N") ? "" : "Y");
            c7.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(7);
            Cell c8 = rowData.createCell(8);
            c8.setCellValue(dto.getPhoneNumber());
            c8.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(8);
            Cell c9 = rowData.createCell(9);
            c9.setCellValue("");
            c9.setCellStyle(styles.get("dataFont1"));
            sheet.autoSizeColumn(9);
            rowNumber++;
            rowInitial++;
        }
    }

    private void setHeaderRow(Sheet sheet, Map<String, CellStyle> styles) {
        String[] headerRowDataList = {"ลำดับ", "คำนำ", "ชื่อ", "นามสกุล", "ชื่อเล่น", "อายุ", "ศิษย์", "รถตู้", "เบอร์ติดต่อ", "เรือนพัก", "ที่นั่ง", "ลงชื่อ"};
        Row row = sheet.createRow(4);
        row.setHeightInPoints(50);
        int columCount = 0; // 0 -> 1
        for (String data : headerRowDataList) {
            Cell cellTitle = row.createCell(columCount++);
            cellTitle.setCellValue(data);
            cellTitle.setCellStyle(styles.get("headerFont1"));
        }
    }

    private void setHeaderConfirmRow(Sheet sheet, Map<String, CellStyle> styles) {
        String[] headerRowDataList = {"ลำดับ", "คำนำ", "ชื่อ", "นามสกุล", "ชื่อเล่น", "อายุ", "ศิษย์", "รถตู้", "เบอร์ติดต่อ", "ข้อมูลเพิ่มเติม"};
        Row row = sheet.createRow(4);
        row.setHeightInPoints(50);
        int columCount = 0; // 0 -> 1
        for (String data : headerRowDataList) {
            Cell cellTitle = row.createCell(columCount++);
            cellTitle.setCellValue(data);
            cellTitle.setCellStyle(styles.get("headerFont1"));
        }
    }

    private static Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<>();
        CellStyle style;

        // Venue
        Font titleFont1 = wb.createFont();
        titleFont1.setFontName("Arial");
        titleFont1.setFontHeightInPoints((short) 48);
        titleFont1.setBold(true);
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(titleFont1);
        styles.put("title1", style);

        // Course
        Font titleFont2 = wb.createFont();
        titleFont2.setFontName("Arial");
        titleFont2.setFontHeightInPoints((short) 36);
        titleFont2.setBold(false);
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(titleFont2);
        styles.put("title2", style);

        // Date
        Font titleFont3 = wb.createFont();
        titleFont3.setFontName("Arial");
        titleFont3.setFontHeightInPoints((short) 24);
        titleFont3.setBold(false);
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(titleFont3);
        styles.put("title3", style);

        // Gender ex, M, F
        Font titleFont4 = wb.createFont();
        titleFont4.setFontName("Arial");
        titleFont4.setFontHeightInPoints((short) 36);
        titleFont4.setBold(true);
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(titleFont4);
        styles.put("title4", style);

        Font headerFont1 = wb.createFont();
        headerFont1.setFontName("Arial");
        headerFont1.setFontHeightInPoints((short) 24);
        headerFont1.setBold(true);
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setFont(headerFont1);
        styles.put("headerFont1", style);

        Font dataFont1 = wb.createFont();
        dataFont1.setFontName("Arial");
        dataFont1.setFontHeightInPoints((short) 24);
        dataFont1.setBold(false);
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setFont(dataFont1);
        styles.put("dataFont1", style);

        Font dataFont2 = wb.createFont();
        dataFont2.setFontName("Arial");
        dataFont2.setFontHeightInPoints((short) 24);
        dataFont2.setBold(false);
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setFont(dataFont2);
        styles.put("dataFont2", style);


//        Font monthFont = wb.createFont();
//        monthFont.setFontHeightInPoints((short) 14);
//        monthFont.setColor(IndexedColors.WHITE.getIndex());
//        style = wb.createCellStyle();
//        style.setAlignment(HorizontalAlignment.CENTER);
//        style.setVerticalAlignment(VerticalAlignment.CENTER);
//        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        style.setFont(monthFont);
//        style.setWrapText(true);
//        styles.put("header", style);
//
//        style = wb.createCellStyle();
//        style.setAlignment(HorizontalAlignment.CENTER);
//        style.setWrapText(true);
//        style.setBorderRight(BorderStyle.THIN);
//        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
//        style.setBorderLeft(BorderStyle.THIN);
//        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
//        style.setBorderTop(BorderStyle.THIN);
//        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
//        style.setBorderBottom(BorderStyle.THIN);
//        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
//        styles.put("cell", style);
//
//        style = wb.createCellStyle();
//        style.setAlignment(HorizontalAlignment.CENTER);
//        style.setVerticalAlignment(VerticalAlignment.CENTER);
//        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
//        styles.put("formula", style);
//
//        style = wb.createCellStyle();
//        style.setAlignment(HorizontalAlignment.CENTER);
//        style.setVerticalAlignment(VerticalAlignment.CENTER);
//        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
//        styles.put("formula_2", style);

        return styles;
    }


    @Override
    public void generateNameTag() {

    }
}
