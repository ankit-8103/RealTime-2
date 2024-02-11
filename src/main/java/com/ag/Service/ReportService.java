package com.ag.Service;

import com.ag.Response.SearchResponse;
import com.ag.requst.SearchRequst;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface ReportService {

    public  List<String> getUniqeplaneName();
    public  List<String> getUniqePlanStatus();
    public List<SearchResponse> Search(SearchRequst requst);

    public void generateExcel(HttpServletResponse response)throws Exception;
    public   void generatepdf(HttpServletResponse response)throws DocumentException,IOException;



}
