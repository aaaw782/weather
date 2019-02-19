package com.yishuiwangu.spring.cloud.weather.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yishuiwangu.spring.cloud.weather.vo.WeatherResponse;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class WeatherDataServiceTest {

    @Autowired
    private WeatherDataService weatherDataService;

    @Test
    public void getDataByCityId() throws Exception {
    }

    @Test
    public void getDataByCityName() throws Exception {
        String cityName ="泉州市";
        WeatherResponse result = weatherDataService.getDataByCityName(cityName);
        System.out.println(JSON.toJSONString(result));
    }

}