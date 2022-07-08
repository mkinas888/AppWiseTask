package com.appwise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class CarInsurance {

    @Getter
    private String licenseNumber;

    @Getter
    @Setter
    private String carModel;

    @Getter
    @Setter
    private Date insuranceStartDate;

    @Getter
    @Setter
    private long insuranceCarValue;

    @Getter
    @Setter
    private InsuranceCase insuranceCase;
}
