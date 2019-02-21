package com.yishuiwangu.spring.cloud.weather.job;

import com.alibaba.fastjson.JSON;
import com.yishuiwangu.spring.cloud.weather.service.WeatherDataCollectionService;
import com.yishuiwangu.spring.cloud.weather.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务
 */
@Component
public class WeatherDataSyncJob extends QuartzJobBean{

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private WeatherDataCollectionService weatherDataCollectionService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("定时开始:"+ LocalDateTime.now().toString());
        //获取城市ID集合
        //TODO 改为由城市数据API微服务来提供城市ID数据
        List<City> cities = null;
        try {
            cities = new ArrayList<>();
            City city = new City();
            city.setCityId("101280601");
            cities.add(city);
        } catch (Exception e) {
            logger.error("定时报错,{}",e);
            e.printStackTrace();
        }
        //便利
        cities.forEach(x->{
            String cityId = x.getCityId();
            logger.info("Sync Job cityId is " + cityId);
            weatherDataCollectionService.syncDateByCityId(cityId);
        });
        logger.info("定时结束:"+ LocalDateTime.now().toString());
    }
}
