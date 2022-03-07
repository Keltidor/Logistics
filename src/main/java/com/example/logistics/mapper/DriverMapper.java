package com.example.logistics.mapper;

import com.example.logistics.dto.DriverRequestDto;
import com.example.logistics.dto.DriverResponseDto;
import com.example.logistics.dto.DriversLicenseResponseDto;
import com.example.logistics.exceptions.DriverNotFoundException;
import com.example.logistics.model.Driver;
import com.example.logistics.model.DriversLicense;
import com.example.logistics.service.DriverService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Маппер для конверта водителя из сущности в DTO и обратно
 */

@Service
public class DriverMapper {
    public final ModelMapper modelMapper;

    public final DriverService driverService;

    public final DriversLicenseMapper driversLicenseMapper;

    public DriverMapper(DriverService driverService,
                        ModelMapper modelMapper,
                        DriversLicenseMapper driversLicenseMapper) {
        this.driverService = driverService;
        this.modelMapper = modelMapper;
        this.driversLicenseMapper = driversLicenseMapper;
    }

    public DriverResponseDto convertToDto(Driver driver) {
        DriverResponseDto driverDto = modelMapper.map(driver, DriverResponseDto.class);
        DriversLicense driversLicense = driver.getDriversLicense();

        if (driversLicense != null) {
            DriversLicenseResponseDto driversLicenseDto = driversLicenseMapper.convertToDto(driversLicense);
            driverDto.setDriversLicense(driversLicenseDto);
        }

        LocalDate date = driver.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        driverDto.setDateOfBirth(date);

        return driverDto;
    }

    public Driver convertToEntity(DriverRequestDto driverDto) throws DriverNotFoundException {
        Driver driver = new Driver();
        return convertToEntity(driverDto, driver);
    }

    public Driver convertToEntity(DriverRequestDto driverDto, Driver driver) throws DriverNotFoundException {
        modelMapper.map(driverDto, driver);
        Date date = Date.from(driverDto.getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant());
        driver.setDateOfBirth(date);
        return driver;
    }
}
