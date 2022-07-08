package com.appwise.repository;

import com.appwise.model.CarInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarInsuranceRepository extends JpaRepository<CarInsurance, String> {
}
