package com.ag.Controller;

import com.ag.Response.SearchResponse;
import com.ag.Service.ReportService;
import com.ag.requst.SearchRequst;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportRestController {
    @Autowired
    private ReportService service;
    @GetMapping("/plans")
    public ResponseEntity<List<String>> getPlanNames(){
        List<String> planNames = service.getUniqeplaneName();
        return  new ResponseEntity<>(planNames, HttpStatus.OK);
    }
    @GetMapping("/statuses")
    public  ResponseEntity<List<String>> getPlanStatus(){
        List<String> planStatuses = service.getUniqePlanStatus();
        return new ResponseEntity<>(planStatuses,HttpStatus.OK);
    }
    @PostMapping("/search")
    public ResponseEntity<List<SearchResponse>> SerchRequst(@RequestBody SearchRequst requst){
        List<SearchResponse> responses = service.Search(requst);
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }
     @GetMapping("/excel")
    public void excelReport(HttpServletResponse response)throws Exception{
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue="attahment filename=data.xls";

        response.setHeader(headerKey,headerValue);
        service.generateExcel(response);
    }
   @GetMapping("/pdf")
    public void pdfReport(HttpServletResponse response)throws Exception{
        response.setContentType("application/pdf");
        String headerKey ="Content-Disposition";
        String headerValue = "attachment;filename=data.pdf";
        response.setHeader(headerKey,headerValue);
        service.generatepdf(response);
    }


}
