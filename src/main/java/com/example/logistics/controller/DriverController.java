package com.example.logistics.controller;

import com.example.logistics.dto.DriverCarRelationDto;
import com.example.logistics.dto.DriverRequestDto;
import com.example.logistics.dto.DriverResponseDto;
import com.example.logistics.dto.DriversLicenseRequestDto;
import com.example.logistics.mapper.DriverMapper;
import com.example.logistics.mapper.DriversLicenseMapper;
import com.example.logistics.model.Driver;
import com.example.logistics.model.DriversLicense;
import com.example.logistics.service.impl.DriverServiceImpl;
import com.example.logistics.service.impl.DriversLicenseServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер для обработки CRUD запросов водителей
 */

@RestController
public class DriverController {

    private final DriverServiceImpl driverService;

    private final DriverMapper driverMapper;

    public DriverController(DriverServiceImpl driverService, DriverMapper driverMapper) {
        this.driverService = driverService;
        this.driverMapper = driverMapper;
    }

    //Метод вывода всех водителей
    @GetMapping("/drivers")
    public List<DriverResponseDto> all() {
        List<Driver> drivers = driverService.findAll();
        return drivers.stream()
                .map(driverMapper::convertToDto)
                .collect(Collectors.toList());
    }

    //Метод создания водителя
    @PostMapping("/drivers")
    public DriverResponseDto createDriver(@RequestBody DriverRequestDto newDriverDto) {
        Driver driver;
        driver = driverMapper.convertToEntity(newDriverDto);
        driver = driverService.saveDriver(driver);
        return driverMapper.convertToDto(driver);
    }

    @GetMapping("/drivers/{id}")
    public DriverResponseDto findOneDriver(@PathVariable Long id) {
        Driver driver = driverService.findDriverById(id);
        return driverMapper.convertToDto(driver);
    }

    @PutMapping("/drivers/{id}")
    public void updateDriver(@RequestBody DriverRequestDto driverDto,
                             @PathVariable Long id) {
        Driver driver = driverService.findDriverById(id);
        driver = driverMapper.convertToEntity(driverDto, driver);
        driverService.saveDriver(driver);
    }

    @DeleteMapping("/drivers/{id}")
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriverById(id);
    }

    @PostMapping("/attach_car/")
    public void attachCar(@RequestBody DriverCarRelationDto relationDto) {
        driverService.attachVehicle(relationDto.getDriverId(), relationDto.getCarId());
    }

    @PostMapping("/detach_car/")
    public void detachCar(@RequestBody DriverCarRelationDto relationDto) {
        driverService.detachVehicle(relationDto.getDriverId(), relationDto.getCarId());
    }
}
