package com.example.logistics.mapper;

import com.example.logistics.dto.CarRequestDto;
import com.example.logistics.dto.CarResponseDto;
import com.example.logistics.exceptions.CarNotFoundException;
import com.example.logistics.model.Car;
import com.example.logistics.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class CarMapper {
    public final ModelMapper modelMapper;

    public final CarService carService;

    public CarMapper(ModelMapper modelMapper, CarService carService) {
        this.modelMapper = modelMapper;
        this.carService = carService;
    }

    public CarResponseDto convertToDto(Car car) {
        CarResponseDto carDto = modelMapper.map(car, CarResponseDto.class);

        Date dateOfCreation = car.getDateOfCreation();
        if (dateOfCreation != null) {
            LocalDate localDateOfCreation = car.getDateOfCreation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            carDto.setDateOfCreation(localDateOfCreation);
        }
        return carDto;
    }

    public Car convertToEntity(CarRequestDto carDto) throws CarNotFoundException {
        Car car = new Car();
        return convertToEntity(carDto, car);
    }

    public Car convertToEntity(CarRequestDto carDto, Car car) throws CarNotFoundException {
        modelMapper.map(carDto, car);

        LocalDate localDateOfCreation = carDto.getDateOfCreation();

        if (localDateOfCreation != null) {
            Date dateOfCreation  = Date.from(localDateOfCreation.atStartOfDay(ZoneId.systemDefault()).toInstant());
            car.setDateOfCreation(dateOfCreation);
        }

        return car;
    }
}
