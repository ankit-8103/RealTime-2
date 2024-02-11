package com.ag.Service;
import com.ag.Entatiy.EligbilteyDetailes;
import com.ag.Repo.EligbilteyDetailesRepo;
import com.ag.Response.SearchResponse;
import com.ag.requst.SearchRequst;
import com.lowagie.text.*;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    EligbilteyDetailesRepo detlsRepo;

    @Override
    public List<String> getUniqeplaneName() {

        return detlsRepo.findPlaneName();

    }

    @Override
    public List<String> getUniqePlanStatus() {

        return detlsRepo.findPlaneStatus();
    }

    @Override
    public List<SearchResponse> Search(SearchRequst requst) {
        List<SearchResponse> response = new ArrayList<>();
        EligbilteyDetailes queryBuilder = new EligbilteyDetailes();
        String planeName = requst.getPlaneName();
        if (planeName != null && !planeName.equals("")) {
            queryBuilder.setPlaneName(planeName);
        }
        String planeStatus = requst.getPlaneStatus();
        if (planeStatus != null && !planeStatus.equals("")) {
            queryBuilder.setPlaneStatus(planeStatus);
        }
        LocalDate planeStarteDate = requst.getPlaneStarteDate();
        if (planeStarteDate != null) {
            queryBuilder.setPlanStartDate(planeStarteDate);
        }
        LocalDate planeEndDate = requst.getPlaneEndDate();
        if (planeEndDate != null) {
            queryBuilder.setPlanEndDate(planeEndDate);
        }

        Example<EligbilteyDetailes> example = Example.of(queryBuilder);
        List<EligbilteyDetailes> entatiys = detlsRepo.findAll(example);
        for (EligbilteyDetailes entatiy : entatiys) {
            SearchResponse sr = new SearchResponse();
            BeanUtils.copyProperties(sr, entatiy);
            response.add(sr);
        }
        return response;
    }

    @Override
    public void generateExcel(HttpServletResponse response) throws Exception{
        List<EligbilteyDetailes> entatiys = detlsRepo.findAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Name");
        headerRow.createCell(1).setCellValue("Email");
        headerRow.createCell(2).setCellValue("Mobile");
        headerRow.createCell(3).setCellValue("Number");
        headerRow.createCell(4).setCellValue("SSN");

        entatiys.forEach(entity -> {
            int i = 1;
            HSSFRow dataRow = sheet.createRow(i);
            dataRow.createCell(0).setCellValue(entity.getName());
            dataRow.createCell(1).setCellValue(entity.getNumber());
            dataRow.createCell(3).setCellValue(entity.getGender());
            dataRow.createCell(4).setCellValue(entity.getSsn());
            i++;
        });
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }

    @Override
    public void generatepdf(HttpServletResponse response) throws DocumentException, IOException {
        List<EligbilteyDetailes> entatiys = detlsRepo.findAll();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
        Paragraph p = new Paragraph("Serch Report", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

         font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        cell.setPhrase(new Phrase("Name", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Phno", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Gender", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("SSN", font));
        table.addCell(cell);
        for (EligbilteyDetailes entity :entatiys){
            table.addCell(entity.getName());
            table.addCell(entity.getEmail());
            table.addCell(String.valueOf(entity.getNumber()));
            table.addCell(String.valueOf(entity.getGender()));
            table.addCell(String.valueOf(entity.getSsn()));
        }

        document.add(table);

        document.close();
    }
}
