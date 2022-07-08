package com.appwise.repository;

import com.appwise.model.CarInsurance;
import com.appwise.model.InsuranceCase;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class CarInsuranceRepositoryImpl implements CarInsuranceRepository {

    private final Map<String, CarInsurance> carInsurances = mockInsurances();

    private Map<String, CarInsurance> mockInsurances() {
        HashMap<String, CarInsurance> mockedInsurances =  new HashMap<>();
        Calendar cal = Calendar.getInstance();
        //case no 1
        cal.set(2021, Calendar.MARCH, 1);
        CarInsurance insuranceNo1 = new CarInsurance("ABC1234", "Audi A6", cal.getTime(), 40000, null);
        cal.set(2021, Calendar.JUNE, 1);
        InsuranceCase insuranceCaseNo1 = new InsuranceCase("ABC1234", 4500, cal.getTime());
        insuranceNo1.setInsuranceCase(insuranceCaseNo1);
        mockedInsurances.put("ABC1234", insuranceNo1);

        //case no 2
        cal.set(2021, Calendar.JANUARY, 1);
        CarInsurance insuranceNo2 = new CarInsurance("DEF567", "VW Passat", cal.getTime(), 12000, null);
        cal.set(2021, Calendar.SEPTEMBER, 1);
        InsuranceCase insuranceCaseNo2 = new InsuranceCase("DEF567", 7730, cal.getTime());
        insuranceNo2.setInsuranceCase(insuranceCaseNo2);
        mockedInsurances.put("DEF567", insuranceNo2);

        //case no 3
        cal.set(2020, Calendar.JANUARY, 1);
        CarInsurance insuranceNo3 = new CarInsurance("GHI8910", "Skoda Octavia", cal.getTime(), 25000, null);
        cal.set(2021, Calendar.MAY, 1);
        InsuranceCase insuranceCaseNo3 = new InsuranceCase("GHI8910", 7000, cal.getTime());
        insuranceNo3.setInsuranceCase(insuranceCaseNo3);
        mockedInsurances.put("GHI8910", insuranceNo3);

        return mockedInsurances;
    }

    public CarInsurance getInsurance(String licenseNumber) {
        return carInsurances.get(licenseNumber);
    }

    @Override
    public List<CarInsurance> findAll() {
        return null;
    }

    @Override
    public List<CarInsurance> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CarInsurance> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CarInsurance> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(CarInsurance entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends CarInsurance> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends CarInsurance> S save(S entity) {
        return null;
    }

    @Override
    public <S extends CarInsurance> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CarInsurance> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends CarInsurance> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends CarInsurance> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<CarInsurance> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CarInsurance getOne(String s) {
        return null;
    }

    @Override
    public CarInsurance getById(String s) {
        return null;
    }

    @Override
    public CarInsurance getReferenceById(String s) {
        return null;
    }

    @Override
    public <S extends CarInsurance> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CarInsurance> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CarInsurance> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CarInsurance> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CarInsurance> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CarInsurance> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CarInsurance, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
