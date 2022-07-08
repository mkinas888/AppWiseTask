package com.appwise.controller;

import com.appwise.exception.NoRecordFoundException;
import com.appwise.model.PayCheck;
import com.appwise.service.CarInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class CarInsuranceController {

    @Autowired
    private CarInsuranceService carInsuranceService;

    @RequestMapping(value="/paycheck/{licenseNumber}", method= RequestMethod.GET)
    public PayCheck getPayCheck(@PathVariable(value = "licenseNumber") String licenseNumber) {
        Long paycheck = carInsuranceService.getInsurancePayCheck(licenseNumber);
        if (paycheck == null) {
         throw new NoRecordFoundException(HttpStatus.NOT_FOUND, "Car insurance with " + licenseNumber + " id not found");
        }
        return new PayCheck(carInsuranceService.getInsurancePayCheck(licenseNumber), licenseNumber);
    }
}
