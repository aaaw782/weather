package com.yishuiwangu.spring.cloud.weather.service.impl;

import com.yishuiwangu.spring.cloud.weather.job.WeatherDataSyncJob;
import com.yishuiwangu.spring.cloud.weather.service.WeatherDataService;
import com.yishuiwangu.spring.cloud.weather.service.WeatherReportService;
import com.yishuiwangu.spring.cloud.weather.vo.Weather;
import com.yishuiwangu.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    public Weather getDataByCityId(String cityId) {
        WeatherResponse resp = weatherDataService.getDataByCityId(cityId);
        return resp.getData();
    }
}
