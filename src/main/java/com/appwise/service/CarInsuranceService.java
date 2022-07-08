package com.appwise.service;

import com.appwise.model.CarInsurance;
import com.appwise.repository.CarInsuranceRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Service
public class CarInsuranceService {

    @Autowired
    private CarInsuranceRepositoryImpl carInsuranceRepository;

    public Long getInsurancePayCheck(String licenseNumber) {
        CarInsurance carInsurance = carInsuranceRepository.getInsurance(licenseNumber);
        if (carInsurance == null) {
            return null;
        }
        if (isInsuranceValid(carInsurance)) {
            Period diff = Period.between(carInsurance.getInsuranceStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    carInsurance.getInsuranceCase().getDamageReportDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            long currentCarValue = carInsurance.getInsuranceCarValue() - carInsurance.getInsuranceCarValue() * diff.getMonths() / 100;
            if (carInsurance.getInsuranceCase().getDamageValue() >= (currentCarValue * 70 / 100)) {
                return (currentCarValue * 70 / 100);
            } else {
                return carInsurance.getInsuranceCase().getDamageValue();
            }
        } else {
            return (long) 0;
        }
    }

    private boolean isInsuranceValid(CarInsurance carInsurance) {
        if (carInsurance.getInsuranceCase().getDamageReportDate().before(carInsurance.getInsuranceStartDate())) {
            return false;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(carInsurance.getInsuranceStartDate());
        cal.add(Calendar.YEAR, 1);
        Date insuranceEndDate = cal.getTime();
        return !carInsurance.getInsuranceCase().getDamageReportDate().after(insuranceEndDate);
    }
}
