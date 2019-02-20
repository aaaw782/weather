package com.yishuiwangu.spring.cloud.weather.job;

import com.alibaba.fastjson.JSON;
import com.yishuiwangu.spring.cloud.weather.service.CityDataService;
import com.yishuiwangu.spring.cloud.weather.service.WeatherDataService;
import com.yishuiwangu.spring.cloud.weather.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 定时任务
 */
@Component
public class WeatherDataSyncJob extends QuartzJobBean{

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private WeatherDataService weatherDataService;

    @Autowired
    private CityDataService cityDataService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("定时开始:"+ LocalDateTime.now().toString());
        List<City> cities = null;
        try {
            cities = cityDataService.listCity();
        } catch (Exception e) {
            logger.error("定时报错,{}",e);
            e.printStackTrace();
        }
        //便利
        cities.forEach(x->{
            String cityId = x.getCityId();
            logger.info("Sync Job cityId is " + cityId);
            weatherDataService.syncDateByCityId(cityId);
        });
        logger.info("定时结束:"+ LocalDateTime.now().toString());
    }
}
