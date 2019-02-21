package com.yishuiwangu.spring.cloud.weather.controller;

import com.yishuiwangu.spring.cloud.weather.service.WeatherDataService;
import com.yishuiwangu.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherDataService weatherDataService;

    @GetMapping("/cityId/{cityId}")
    public WeatherResponse getWeatherByCityId(@PathVariable("cityId") String cityId) {
        return weatherDataService.getDataByCityId(cityId);
    }

    @GetMapping("/cityName")
    public WeatherResponse getWeatherByCityName(String cityName) {
        System.out.println("进来了");
        return weatherDataService.getDataByCityName(cityName);
    }

}
