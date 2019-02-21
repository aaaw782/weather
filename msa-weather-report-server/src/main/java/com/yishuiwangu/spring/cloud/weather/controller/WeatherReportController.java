package com.yishuiwangu.spring.cloud.weather.controller;

import com.yishuiwangu.spring.cloud.weather.service.WeatherReportService;
import com.yishuiwangu.spring.cloud.weather.vo.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 天气报告
 */
@RestController
@RequestMapping("/report")
public class WeatherReportController {

    private final static Logger logger = LoggerFactory.getLogger(WeatherReportController.class);

    @Autowired
    private WeatherReportService reportService;


    /**
     * 获取某个城市的天气报告
     *
     * @param cityId
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {

        // 获取城市ID列表
        // TODO 改为由城市数据API微服务来提供数据
        List<City> cityList = null;
        try {
            // TODO 改为由城市数据API微服务提供数据
            cityList = new ArrayList<>();
            City city = new City();
            city.setCityId("101280601");
            city.setCityName("深圳");
            cityList.add(city);
        } catch (Exception e) {
            logger.error("Exception!", e);
        }
        model.addAttribute("title", "一睡万古的天气播报");
        model.addAttribute("cityId", cityId);
        model.addAttribute("cityList",cityList);
        model.addAttribute("report", reportService.getDataByCityId(cityId));
        return new ModelAndView("weather/report", "reportModel", model);

    }
}
