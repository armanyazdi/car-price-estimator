package com.armanyazdi.carpriceestimator.service;

import com.armanyazdi.carpriceestimator.Car;
import com.armanyazdi.carpriceestimator.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {

    @Autowired
    AppRepository appRepository;

    public String[] addGearboxes() {
        return appRepository.addGearboxes();
    }

    public List<String> addBrands() {
        return appRepository.addBrands();
    }

    public List<String> addColors() {
        return appRepository.addColors();
    }

    public List<String> addStatuses() {
        return appRepository.addStatuses();
    }

    public void addCar(Car car) {
        appRepository.addCar(car);
    }

    public List<Car> getCarDetails() {
        return appRepository.getCarDetails();
    }

    public long getMinPrice() {
        return appRepository.getMinPrice();
    }

    public long getMaxPrice() {
        return appRepository.getMaxPrice();
    }

    public String getDay() {
        return appRepository.getDay();
    }

    public String getDate() {
        return appRepository.getDate();
    }
}
