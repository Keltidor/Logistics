package com.example.logistics.service;

import com.example.logistics.exceptions.DriverNotFoundException;
import com.example.logistics.model.Driver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DriverService {
    List<Driver> findAll();

    Driver findDriverById(Long id) throws DriverNotFoundException;

    Driver saveDriver(Driver driver);

    void deleteDriverById(Long id) throws DriverNotFoundException;

    void attachVehicle(Long driverId, Long carId);

    void detachVehicle(Long driverId, Long carId);
}
