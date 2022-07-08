package com.appwise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class InsuranceCase {

    @Getter
    private String licenseNumber;

    @Getter
    @Setter
    private long damageValue;

    @Getter
    @Setter
    private Date damageReportDate;
}
