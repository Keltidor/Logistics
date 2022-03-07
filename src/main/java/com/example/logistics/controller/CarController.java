package com.example.logistics.controller;

import com.example.logistics.dto.CarRequestDto;
import com.example.logistics.dto.CarResponseDto;
import com.example.logistics.mapper.CarMapper;
import com.example.logistics.model.Car;
import com.example.logistics.service.impl.CarServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер для обработки CRUD запросов авто
 */

@RestController
public class CarController {

    private final CarServiceImpl carService;

    private final CarMapper carMapper;

    public CarController(CarServiceImpl carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    //Метод вывода всех водителей
    @GetMapping("/cars")
    public List<CarResponseDto> all() {
        List<Car> cars = carService.findAll();
        return cars.stream()
                .map(carMapper::convertToDto)
                .collect(Collectors.toList());
    }

    //Метод создания авто
    @PostMapping("/cars")
    public CarResponseDto createCar(@RequestBody CarRequestDto newCarDto) {
        Car car;
        car = carMapper.convertToEntity(newCarDto);
        car = carService.saveCar(car);
        return carMapper.convertToDto(car);
    }

    //Метод поиска авто
    @GetMapping("/cars/{id}")
    public CarResponseDto findOneCar(@PathVariable Long id) {
        Car car = carService.findCarById(id);
        return carMapper.convertToDto(car);
    }

    //Метод обновления авто
    @PutMapping("/cars/{id}")
    public void updateCar(@RequestBody CarRequestDto carDto,
                          @PathVariable Long id) {
        Car car = carService.findCarById(id);
        car = carMapper.convertToEntity(carDto, car);
        carService.saveCar(car);
    }

    //Метод удаления авто
    @DeleteMapping("/cars/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        carService.deleteCarById(id);
    }
}
