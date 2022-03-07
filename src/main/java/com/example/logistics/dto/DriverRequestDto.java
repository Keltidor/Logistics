package com.example.logistics.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DriverRequestDto {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    @NotBlank
    @Size(min = 0, max = 255)
    private String firstName;

    @NotBlank
    @Size(min = 0, max = 255)
    private String lastName;

    @Size(min = 0, max = 255)
    private String middleName;

    @Size(min = 0, max = 255)
    @Email
    private String email;

    @NotBlank
    @Size(min = 0, max = 255)
    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private DriversLicenseRequestDto driversLicense;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public DriversLicenseRequestDto getDriversLicense() {
        return driversLicense;
    }

    public void setDriversLicense(DriversLicenseRequestDto driversLicense) {
        this.driversLicense = driversLicense;
    }
}
