package com.example.logistics.service;

import com.example.logistics.exceptions.DriversLicenseNotFoundException;
import com.example.logistics.model.DriversLicense;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DriversLicenseService {
    List<DriversLicense> findAll();

    DriversLicense findDriversLicenseById(Long id) throws DriversLicenseNotFoundException;

    DriversLicense saveDriversLicense(DriversLicense driver);

    void deleteDriversLicenseById(Long id) throws DriversLicenseNotFoundException;
}
