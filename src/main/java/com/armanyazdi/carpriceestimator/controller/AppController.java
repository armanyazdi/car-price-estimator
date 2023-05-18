package com.armanyazdi.carpriceestimator.controller;

import com.armanyazdi.carpriceestimator.Car;
import com.armanyazdi.carpriceestimator.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    @Autowired
    AppService appService;

    @GetMapping("/")
    public String getForm(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("brands", appService.addBrands());
        model.addAttribute("gearboxes", appService.addGearboxes());
        model.addAttribute("colors", appService.addColors());
        model.addAttribute("statuses", appService.addStatuses());
        return "index";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(Car car) {
        appService.addCar(car);
        return "redirect:/price";
    }

    @GetMapping("/price")
    public String getPrice(Model model) {
        model.addAttribute("car", appService.getCarDetails().get(0));
        model.addAttribute("minimum", appService.getMinPrice());
        model.addAttribute("maximum", appService.getMaxPrice());
        model.addAttribute("day", appService.getDay());
        model.addAttribute("date", appService.getDate());
        return "price";
    }
}
