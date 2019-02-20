package com.yishuiwangu.spring.cloud.weather.service;

import com.alibaba.fastjson.JSON;
import com.yishuiwangu.spring.cloud.weather.vo.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CityDataServiceTest {

    @Autowired
    private CityDataService cityDataService;

    /**
     * 获取城市
     * @throws Exception
     */
    @Test
    public void listCity() throws Exception {
        List<City> cities = cityDataService.listCity();
        cities.forEach(x->{
            System.out.println("city-> "+ JSON.toJSONString(x));
        });
    }

}