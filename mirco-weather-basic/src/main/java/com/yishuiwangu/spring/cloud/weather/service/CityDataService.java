package com.yishuiwangu.spring.cloud.weather.service;

import com.yishuiwangu.spring.cloud.weather.vo.City;

import java.util.List;

/**
 * 城市数据
 */
public interface CityDataService {

    /**
     * 获取City列表
     *
     * @return
     * @throws Exception
     */
    List<City> listCity() throws Exception;
}
