package com.ag.requst;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchRequst {

    private String planeName;
  private String planeStatus;
  private LocalDate planeStarteDate;
  private LocalDate planeEndDate;
}
