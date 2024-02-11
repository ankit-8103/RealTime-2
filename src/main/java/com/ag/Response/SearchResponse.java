package com.ag.Response;

import lombok.Data;

@Data
public class SearchResponse {


    private  String name;
    private Long number;
    private String email;
    private Character gender;
    private Long ssn;
    private  String planeName;
    private String planeStatus;
}
