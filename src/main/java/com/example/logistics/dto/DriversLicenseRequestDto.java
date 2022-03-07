package com.example.logistics.dto;

import com.example.logistics.model.enums.LicenseType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DriversLicenseRequestDto {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank
    private LocalDate dateOfIssue;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank
    private LocalDate dateOfExpiry;

    private LicenseType licenseType;

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public LocalDate getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(LocalDate dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseType licenseType) {
        this.licenseType = licenseType;
    }
}
