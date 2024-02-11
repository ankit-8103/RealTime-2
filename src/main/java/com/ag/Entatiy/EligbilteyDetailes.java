package com.ag.Entatiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Eligbiltey_Detaile")
public class EligbilteyDetailes {
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
    private  String eligId;
    private  String name;
    private Long number;
    private String email;
    private Character gender;
    private Long ssn;
    private  String planeName;
    private String planeStatus;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private LocalDate createDate;
    private LocalDate updateDate;
    private String createBy;
    private String updateBy;
}
