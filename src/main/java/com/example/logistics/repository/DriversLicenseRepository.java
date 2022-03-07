package com.example.logistics.repository;

import com.example.logistics.model.DriversLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriversLicenseRepository extends JpaRepository<DriversLicense, Long> {
}
