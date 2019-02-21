package com.yishuiwangu.spring.cloud.weather.service;

/**
 * 天气数据采集
 */
public interface WeatherDataCollectionService {

    /**
     * 根据城市ID同步天气
     * @param cityId
     */
    void syncDateByCityId(String cityId);
}
