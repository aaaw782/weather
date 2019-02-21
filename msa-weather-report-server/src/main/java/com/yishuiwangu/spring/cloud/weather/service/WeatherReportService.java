package com.yishuiwangu.spring.cloud.weather.service;

import com.yishuiwangu.spring.cloud.weather.vo.Weather;

/**
 * 天气报告
 */
public interface WeatherReportService {

    /**
     * 根据城市ID查询天气信息
     * @param cityId
     * @return
     */
    Weather getDataByCityId(String cityId);
}
