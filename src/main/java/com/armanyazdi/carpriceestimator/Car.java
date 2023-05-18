package com.armanyazdi.carpriceestimator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {
    private String brand;
    private String gearbox;
    private String production;
    private String mileage;
    private String color;
    private String status;
}
