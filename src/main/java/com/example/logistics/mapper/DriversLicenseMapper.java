package com.example.logistics.mapper;

import com.example.logistics.dto.DriversLicenseRequestDto;
import com.example.logistics.dto.DriversLicenseResponseDto;
import com.example.logistics.exceptions.DriversLicenseNotFoundException;
import com.example.logistics.model.DriversLicense;
import com.example.logistics.service.DriversLicenseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Маппер для конверта водительских прав из сущности в DTO и обратно
 */

@Service
public class DriversLicenseMapper {
    public final ModelMapper modelMapper;

    public final DriversLicenseService driversLicenseService;

    public DriversLicenseMapper(DriversLicenseService driversLicenseService, ModelMapper modelMapper) {
        this.driversLicenseService = driversLicenseService;
        this.modelMapper = modelMapper;
    }

    public DriversLicenseResponseDto convertToDto(DriversLicense driversLicense) {
        DriversLicenseResponseDto driversLicenseDto = modelMapper.map(driversLicense, DriversLicenseResponseDto.class);
        Date date = driversLicense.getDateOfIssue();

        LocalDate localDateOfIssue = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateOfExpiry = driversLicense.getDateOfExpiry().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        driversLicenseDto.setDateOfIssue(localDateOfIssue);
        driversLicenseDto.setDateOfExpiry(localDateOfExpiry);
        return driversLicenseDto;
    }

    public DriversLicense convertToEntity(DriversLicenseRequestDto driversLicenseDto) throws DriversLicenseNotFoundException {
        DriversLicense driversLicense = new DriversLicense();
        return convertToEntity(driversLicenseDto, driversLicense);
    }

    public DriversLicense convertToEntity(DriversLicenseRequestDto driversLicenseDto,
                                          DriversLicense driversLicense) throws DriversLicenseNotFoundException {
        modelMapper.map(driversLicenseDto, driversLicense);

        Date dateOfIssue  = Date.from(driversLicenseDto.getDateOfIssue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateOfExpiry = Date.from(driversLicenseDto.getDateOfExpiry().atStartOfDay(ZoneId.systemDefault()).toInstant());

        driversLicense.setDateOfIssue(dateOfIssue);
        driversLicense.setDateOfExpiry(dateOfExpiry);

        return driversLicense;
    }
}
