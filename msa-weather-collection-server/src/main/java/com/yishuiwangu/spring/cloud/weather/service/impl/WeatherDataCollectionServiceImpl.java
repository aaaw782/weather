package com.yishuiwangu.spring.cloud.weather.service.impl;

import com.yishuiwangu.spring.cloud.weather.service.WeatherDataCollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class WeatherDataCollectionServiceImpl implements WeatherDataCollectionService {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataCollectionServiceImpl.class);
    private static final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini?";
    private static final Long TIME_OUT = 3600L;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void syncDateByCityId(String cityId) {
        String uri = WEATHER_URL + "citykey=" + cityId;
        this.saveWeatherData(uri);
    }

    /**
     * 缓存天气数据
     * @param uri
     */
    private void saveWeatherData(String uri){
        String key = uri;
        String strBody = null;
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        //远程调用天气接口
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        if (200 == responseEntity.getStatusCodeValue()) {
            strBody = responseEntity.getBody();
            ops.set(key,strBody,TIME_OUT, TimeUnit.SECONDS);
        }
    }
}
