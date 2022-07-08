package com.appwise;

import com.appwise.model.CarInsurance;
import com.appwise.model.InsuranceCase;
import com.appwise.repository.CarInsuranceRepositoryImpl;
import com.appwise.service.CarInsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;


@SpringBootTest
public class CarInsuranceServiceTest {

    @Autowired
    CarInsuranceService carInsuranceService;

    @MockBean
    CarInsuranceRepositoryImpl carInsuranceRepository;

    @Test
    public void testInvalidLicenseNumber() {
        when(carInsuranceRepository.getInsurance("bad license number")).thenReturn(null);
        assertNull(carInsuranceService.getInsurancePayCheck("bad license number"));
    }

    @Test
    public void testExpiredInsurance() {
        Calendar cal = Calendar.getInstance();
        cal.set(2022, Calendar.JANUARY, 1);
        InsuranceCase insuranceCase = new InsuranceCase("test", 5000, cal.getTime());
        cal.set(2020, Calendar.JANUARY, 1);
        CarInsurance carInsurance = new CarInsurance("test", "test car", cal.getTime(), 10000, insuranceCase);
        when(carInsuranceRepository.getInsurance("test")).thenReturn(carInsurance);
        assertEquals(carInsuranceService.getInsurancePayCheck("test").longValue(), 0);
    }

    @Test
    public void testDamageValueOver70Percent() {
        Calendar cal = Calendar.getInstance();
        cal.set(2022, Calendar.APRIL, 1);
        InsuranceCase insuranceCase = new InsuranceCase("test", 8000, cal.getTime());
        cal.set(2022, Calendar.JANUARY, 1);
        CarInsurance carInsurance = new CarInsurance("test", "test car", cal.getTime(), 10000, insuranceCase);
        when(carInsuranceRepository.getInsurance("test")).thenReturn(carInsurance);
        long currentValue = (carInsurance.getInsuranceCarValue() - carInsurance.getInsuranceCarValue() * 3 / 100);
        assertEquals(carInsuranceService.getInsurancePayCheck("test").longValue(), currentValue * 70 / 100);
    }

    @Test
    public void testDamageValueUnder70Percent() {
        Calendar cal = Calendar.getInstance();
        cal.set(2022, Calendar.APRIL, 1);
        InsuranceCase insuranceCase = new InsuranceCase("test", 2000, cal.getTime());
        cal.set(2022, Calendar.JANUARY, 1);
        CarInsurance carInsurance = new CarInsurance("test", "test car", cal.getTime(), 10000, insuranceCase);
        when(carInsuranceRepository.getInsurance("test")).thenReturn(carInsurance);
        long currentValue = (carInsurance.getInsuranceCarValue() - carInsurance.getInsuranceCarValue() * 3 / 100);
        assertEquals(carInsuranceService.getInsurancePayCheck("test").longValue(), carInsurance.getInsuranceCase().getDamageValue());
    }

    @Test
    public void testDamageDoneEarlierThanInsuranceWasBought() {
        Calendar cal = Calendar.getInstance();
        cal.set(2022, Calendar.JANUARY, 1);
        InsuranceCase insuranceCase = new InsuranceCase("test", 2000, cal.getTime());
        cal.set(2022, Calendar.MAY, 1);
        CarInsurance carInsurance = new CarInsurance("test", "test car", cal.getTime(), 10000, insuranceCase);
        when(carInsuranceRepository.getInsurance("test")).thenReturn(carInsurance);
        assertEquals(carInsuranceService.getInsurancePayCheck("test").longValue(), 0);
    }

    @Test
    public void testInsuranceAtSameMonthAsDamage() {
        Calendar cal = Calendar.getInstance();
        cal.set(2022, Calendar.JANUARY, 20);
        InsuranceCase insuranceCase = new InsuranceCase("test", 8000, cal.getTime());
        cal.set(2022, Calendar.JANUARY, 2);
        CarInsurance carInsurance = new CarInsurance("test", "test car", cal.getTime(), 10000, insuranceCase);
        when(carInsuranceRepository.getInsurance("test")).thenReturn(carInsurance);
        assertEquals(carInsuranceService.getInsurancePayCheck("test").longValue(), carInsurance.getInsuranceCarValue() * 70 / 100);
    }

    @Test
    public void testCaseWithCarWorthNothing() {
        Calendar cal = Calendar.getInstance();
        cal.set(2022, Calendar.MAY, 1);
        InsuranceCase insuranceCase = new InsuranceCase("test", 8000, cal.getTime());
        cal.set(2022, Calendar.JANUARY, 1);
        CarInsurance carInsurance = new CarInsurance("test", "test car", cal.getTime(), 0, insuranceCase);
        when(carInsuranceRepository.getInsurance("test")).thenReturn(carInsurance);
        assertEquals(carInsuranceService.getInsurancePayCheck("test").longValue(), 0);
    }
}
