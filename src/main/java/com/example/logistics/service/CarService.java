package com.example.logistics.service;

import com.example.logistics.exceptions.CarNotFoundException;
import com.example.logistics.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    List<Car> findAll();

    Car findCarById(Long id) throws CarNotFoundException;

    Car saveCar(Car car);

    void deleteCarById(Long id) throws CarNotFoundException;
}
