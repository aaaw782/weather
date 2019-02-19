package com.yishuiwangu.spring.cloud.weather.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yishuiwangu.spring.cloud.weather.service.WeatherDataService;
import com.yishuiwangu.spring.cloud.weather.util.StringUtil;
import com.yishuiwangu.spring.cloud.weather.vo.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private static final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini?";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URL+"citykey="+cityId;
        return this.doGetWeather(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URL+"city="+cityName;
        return this.doGetWeather(uri);
    }

    /**
     * 发送请求
     * @param uri
     * @return
     */
    private WeatherResponse doGetWeather(String uri){

        RestTemplate restTemplate2 = new RestTemplate();
        ResponseEntity<String> respString = restTemplate2.getForEntity(uri, String.class);

        ResponseEntity<String> respString2 = restTemplate.getForEntity(uri, String.class);
        System.out.println("respString2:"+respString2.getBody());
        String strBody = null;
        try{
            if(respString.getStatusCodeValue() == 200){
                strBody = StringUtil.conventFromGzip(respString.getBody());
                System.out.println("strBody:"+strBody);
            }
//            WeatherResponse weatherResponse = JSON.parseObject(strBody, WeatherResponse.class);
//            return  weatherResponse;
            ObjectMapper mapper = new ObjectMapper();
            WeatherResponse resp = null;
            resp = mapper.readValue(strBody,WeatherResponse.class);
            return resp;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
