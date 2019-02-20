package com.yishuiwangu.spring.cloud.weather.controller;

import com.yishuiwangu.spring.cloud.weather.service.CityDataService;
import com.yishuiwangu.spring.cloud.weather.service.WeatherReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 天气报告
 */
@RestController
@RequestMapping("/report")
public class WeatherReportController {

    @Autowired
    private WeatherReportService reportService;

    @Autowired
    private CityDataService cityDataService;

    /**
     * 获取某个城市的天气报告
     * @param cityId
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception{
        model.addAttribute("title", "一睡万古的天气播报");
        model.addAttribute("cityId", cityId);
        model.addAttribute("cityList", cityDataService.listCity());
        model.addAttribute("report", reportService.getDataByCityId(cityId));
        return new ModelAndView("weather/report", "reportModel", model);

    }
}
