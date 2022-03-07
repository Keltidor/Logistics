package com.example.logistics.service.impl;

import com.example.logistics.exceptions.CarNotFoundException;
import com.example.logistics.model.Car;
import com.example.logistics.repository.CarRepository;
import com.example.logistics.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }

    @Override
    public Car findCarById(Long id) throws CarNotFoundException {
        return repository.findById(id).orElseThrow(() -> new CarNotFoundException("Id not found"));
    }

    @Override
    public Car saveCar(Car car) {
        return repository.save(car);
    }

    @Override
    public void deleteCarById(Long id) throws CarNotFoundException {
        repository.delete(findCarById(id));
    }
}
