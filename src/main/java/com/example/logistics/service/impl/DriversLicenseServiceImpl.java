package com.example.logistics.service.impl;

import com.example.logistics.exceptions.DriversLicenseNotFoundException;
import com.example.logistics.model.DriversLicense;
import com.example.logistics.repository.DriversLicenseRepository;
import com.example.logistics.service.DriversLicenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriversLicenseServiceImpl implements DriversLicenseService {
    private final DriversLicenseRepository repository;

    public DriversLicenseServiceImpl(DriversLicenseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DriversLicense> findAll() {
        return repository.findAll();
    }

    @Override
    public DriversLicense findDriversLicenseById(Long id) throws DriversLicenseNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new DriversLicenseNotFoundException("Drivers License not found"));
    }

    @Override
    public DriversLicense saveDriversLicense(DriversLicense driver) {
        return repository.save(driver);
    }

    @Override
    public void deleteDriversLicenseById(Long id) throws DriversLicenseNotFoundException {
        repository.delete(findDriversLicenseById(id));
    }
}
