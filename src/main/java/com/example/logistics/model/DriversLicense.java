package com.example.logistics.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "drivers_licenses")
public class DriversLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drivers_license_id")
    private Long driversLicenseId;

    @OneToOne(mappedBy = "driversLicense")
    private Driver driver;

    @Column(name="date_of_issue", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfIssue;

    @Column(name="date_of_expiry", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfExpiry;

    @Enumerated(EnumType.STRING)
    @Column(name = "license_type")
    private LicenseType licenseType;

    public enum LicenseType {
        B,
        C,
        D,
    }
}
