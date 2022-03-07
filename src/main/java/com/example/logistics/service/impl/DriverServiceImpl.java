package com.example.logistics.service.impl;

import com.example.logistics.exceptions.DriverNotFoundException;
import com.example.logistics.exceptions.InvalidLicenseCategoryException;
import com.example.logistics.exceptions.LicenseExpiredException;
import com.example.logistics.model.Car;
import com.example.logistics.model.Driver;
import com.example.logistics.model.DriversLicense;
import com.example.logistics.repository.DriverRepository;
import com.example.logistics.service.DriverService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository repository;
    private final CarServiceImpl carService;

    public DriverServiceImpl(DriverRepository repository, CarServiceImpl carService) {
        this.repository = repository;
        this.carService = carService;
    }

    @Override
    public List<Driver> findAll() {
        return repository.findAll();
    }

    @Override
    public Driver findDriverById(Long id) throws DriverNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException("Driver id not found"));
    }

    @Override
    public Driver saveDriver(Driver driver) {
        return repository.save(driver);
    }

    @Override
    public void deleteDriverById(Long id) throws DriverNotFoundException {
        repository.delete(findDriverById(id));
    }

    // Метод привязки авто к водителю
    @Override
    public void attachVehicle(Long driverId, Long carId) {
        Driver driver = repository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException("Driver id not found"));

        //Получаем тип авто
        Car car = carService.findCarById(carId);
        String carType = car.getCarType().toString();

        //Получаем текущие права у водителя
        DriversLicense driversLicense = driver.getDriversLicense();
        String driversLicenseType = driversLicense.getLicenseType().toString();

        if (!checkPermissions(carType, driversLicenseType)) {
            throw new InvalidLicenseCategoryException();
        }

        if (!checkLicenseDate(driversLicense.getDateOfExpiry())) {
            throw new LicenseExpiredException();
        }

        car.setDriverId(driverId);
        carService.saveCar(car);
    }

    @Override
    public void detachVehicle(Long driverId, Long carId) {
        Driver driver = repository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found"));

        Car car = carService.findCarById(carId);
        car.setDriverId(driverId);
        carService.saveCar(car);
    }

    private Boolean checkLicenseDate(Date date) {
        return date.after(new Date());
    }

    private Boolean checkPermissions(String carType, String driversLicenseType) {
        HashMap<String, HashMap<String, Boolean>> permissions = new HashMap<>();
        // Для того чтобы избавиться от кучи If else
        permissions.put("B", new HashMap<>());
        permissions.put("C", new HashMap<>());
        permissions.put("D", new HashMap<>());
        // категория B дает разрешение на закреп только обычных авто
        permissions.get("B").put("PASSENGER", true);
        // категория C дает разрешение на закреп обычных авто и грузовиков
        permissions.get("C").put("PASSENGER", true);
        permissions.get("C").put("CARGO", true);
        // категория D дает разрешение на закреп обычных авто и автобусов
        permissions.get("D").put("PASSENGER", true);
        permissions.get("D").put("BUS", true);

        return permissions.get(driversLicenseType).containsKey(carType);
    }
}
