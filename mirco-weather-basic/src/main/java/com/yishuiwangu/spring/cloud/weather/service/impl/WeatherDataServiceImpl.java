package com.yishuiwangu.spring.cloud.weather.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yishuiwangu.spring.cloud.weather.service.WeatherDataService;
import com.yishuiwangu.spring.cloud.weather.util.StringUtil;
import com.yishuiwangu.spring.cloud.weather.vo.WeatherResponse;
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
public class WeatherDataServiceImpl implements WeatherDataService {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);
    private static final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini?";
    private static final Long TIME_OUT = 3600L;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URL + "citykey=" + cityId;
        return this.doGetWeather(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URL + "city=" + cityName;
        return this.doGetWeather(uri);
    }

    @Override
    public void syncDateByCityId(String cityId) {
      String uri = WEATHER_URL+"citykey="+cityId;
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
            ops.set(key,strBody,TIME_OUT,TimeUnit.SECONDS);
        }
    }


    /**
     * 发送请求
     *
     * @param uri
     * @return
     */
    private WeatherResponse doGetWeather(String uri) {
        String key = uri;
        //先查缓存,缓存有的取缓存钟的数据
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        Boolean hasKey = redisTemplate.hasKey(key);
        String strBody = null;
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse resp = null;

        if (hasKey) {
            logger.info("Redis has data");
            strBody = ops.get(key);
        } else {
            logger.info("Redis no data");
            //缓存没有,再调用服务接口来获取数据,再缓存数据
            ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

            if (respString.getStatusCodeValue() == 200) {
                strBody = respString.getBody();
                System.out.println("strBody:" + strBody);
            }
            ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
        }
        try {
            resp = mapper.readValue(strBody, WeatherResponse.class);
        } catch (Exception e) {
            logger.error("Error",e);
            e.printStackTrace();

        }
        return resp;
    }


    /**
     * 发送请求
     *
     * @param uri
     * @return
     */
    private WeatherResponse doGetWeather2(String uri) {

        RestTemplate restTemplate2 = new RestTemplate();
        ResponseEntity<String> respString = restTemplate2.getForEntity(uri, String.class);
        String strBody = null;
        try {
            if (respString.getStatusCodeValue() == 200) {
                strBody = StringUtil.conventFromGzip(respString.getBody());
                System.out.println("strBody:" + strBody);
            }
//            WeatherResponse weatherResponse = JSON.parseObject(strBody, WeatherResponse.class);
//            return  weatherResponse;
            ObjectMapper mapper = new ObjectMapper();
            WeatherResponse resp = null;
            resp = mapper.readValue(strBody, WeatherResponse.class);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
