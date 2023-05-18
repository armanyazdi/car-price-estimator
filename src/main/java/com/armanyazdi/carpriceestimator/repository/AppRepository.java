package com.armanyazdi.carpriceestimator.repository;

import com.armanyazdi.carpriceestimator.Car;
import com.armanyazdi.carpriceestimator.DataReader;
import com.armanyazdi.carpriceestimator.DateConvertor;
import com.armanyazdi.carpriceestimator.PriceEstimator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AppRepository {

    @Autowired
    DataReader dataReader;
    @Autowired
    PriceEstimator priceEstimator;
    @Autowired
    DateConvertor dateConvertor;
    private final List<Car> carDetails = new ArrayList<>();

    public String[] addGearboxes() {
        return new String[]{"اتوماتیک", "دنده ای"};
    }

    public List<String> addBrands() {
        List<String> brands = new ArrayList<>();
        dataReader.readFile("brands.txt", brands);
        return brands;
    }

    public List<String> addColors() {
        List<String> colors = new ArrayList<>();
        dataReader.readFile("colors.txt", colors);
        return colors;
    }

    public List<String> addStatuses() {
        List<String> statuses = new ArrayList<>();
        dataReader.readFile("status.txt", statuses);
        return statuses;
    }

    public void addCar(Car car) {
        carDetails.clear();
        carDetails.add(car);
        priceEstimator.averagePrice(
                car.getBrand(),
                car.getGearbox(),
                car.getProduction(),
                car.getMileage(),
                car.getColor(),
                car.getStatus());
    }

    public List<Car> getCarDetails() {
        return carDetails;
    }

    public long getMinPrice() {
        return priceEstimator.minimumPrice();
    }

    public long getMaxPrice() {
        return priceEstimator.maximumPrice();
    }

    public String getDay() {
        return dateConvertor.dayName();
    }

    public String getDate() {
        return dateConvertor.jalaliDate();
    }
}
