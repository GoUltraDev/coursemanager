package co.techo.service;

import be.quodlibet.boxable.*;
import be.quodlibet.boxable.line.LineStyle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.IOException;

public class PdfService {

    public static void main(String[] arg) throws IOException {
        //Creating PDF document object
        String outputFileName = "./pdfs/my_doc.pdf";

        // Create a new font object selecting one of the PDF base fonts
        PDFont fontPlain = PDType1Font.HELVETICA;
        PDFont fontBold = PDType1Font.TIMES_ROMAN;
        PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
        PDFont fontMono = PDType1Font.COURIER;

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream cos = new PDPageContentStream(document, page);

        //Dummy Table
        float margin = 50;
        // starting y position is whole page height subtracted by top and bottom margin
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        // we want table across whole page width (subtracted by left and right margin ofcourse)
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

        boolean drawContent = true;
        float yStart = yStartNewPage;
        float bottomMargin = 70;
        // y position is your coordinate of top left corner of the table
        float yPosition = 550;

        BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, document, page, true, drawContent);
        // the parameter is the row height
        Row<PDPage> headerRow = table.createRow(50);
        // the first parameter is the cell width
        Cell<PDPage> cell = headerRow.createCell(100, "Header");
        cell.setFont(fontBold);
        cell.setFontSize(20);
        // vertical alignment
        cell.setValign(VerticalAlignment.MIDDLE);
        // border style
        cell.setTopBorderStyle(new LineStyle(Color.BLACK, 10));
        table.addHeaderRow(headerRow);

        Row<PDPage> row = table.createRow(20);
        cell = row.createCell(30, "black left plain");
        cell.setFontSize(15);
        cell = row.createCell(70, "black left bold");
        cell.setFontSize(15);
        cell.setFont(fontBold);

//        row = table.createRow(20);
//        cell = row.createCell(50, "red right mono");
//        cell.setTextColor(Color.RED);
//        cell.setFontSize(15);
//        cell.setFont(fontMono);
//        // horizontal alignment
//        cell.setAlign(HorizontalAlignment.RIGHT);
//        cell.setBottomBorderStyle(new LineStyle(Color.RED, 5));
//        cell = row.createCell(50, "green centered italic");
//        cell.setTextColor(Color.GREEN);
//        cell.setFontSize(15);
//        cell.setFont(fontItalic);
//        cell.setAlign(HorizontalAlignment.CENTER);
//        cell.setBottomBorderStyle(new LineStyle(Color.GREEN, 5));

//        row = table.createRow(20);
//        cell = row.createCell(40, "rotated");
//        cell.setFontSize(15);
//        // rotate the text
//        cell.setTextRotated(true);
//        cell.setAlign(HorizontalAlignment.RIGHT);
//        cell.setValign(VerticalAlignment.MIDDLE);
//        // long text that wraps
//        cell = row.createCell(30, "long text long text long text long text long text long text long text");
//        cell.setFontSize(12);
//        // long text that wraps, with more line spacing
//        cell = row.createCell(30, "long text long text long text long text long text long text long text");
//        cell.setFontSize(12);
//        cell.setLineSpacing(2);

        table.draw();

        float tableHeight = table.getHeaderAndDataHeight();
        System.out.println("tableHeight = " + tableHeight);

        cos.close();

        document.save(outputFileName);
        document.close();

    }

    private static void getList() throws IOException {

    }
}
