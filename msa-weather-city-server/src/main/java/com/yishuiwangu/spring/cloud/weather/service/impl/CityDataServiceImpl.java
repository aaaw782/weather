package com.yishuiwangu.spring.cloud.weather.service.impl;

import com.sun.org.apache.bcel.internal.util.ClassPath;
import com.yishuiwangu.spring.cloud.weather.service.CityDataService;
import com.yishuiwangu.spring.cloud.weather.util.XmlBuilderUtil;
import com.yishuiwangu.spring.cloud.weather.vo.City;
import com.yishuiwangu.spring.cloud.weather.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CityDataServiceImpl implements CityDataService {

    @Override
    public List<City> listCity() throws IOException,JAXBException {
        //1.读取citylist.xml XML文件
        String xmlFileName = "citylist.xml";
        ClassPathResource resource = new ClassPathResource(xmlFileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream(),"utf-8"));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = bufferedReader.readLine()) != null){
            buffer.append(line);
        }
        bufferedReader.close();
        //将XML转换为java对象
        CityList cityList = (CityList) XmlBuilderUtil.xmlStrToObject(CityList.class, buffer.toString());
        return cityList.getCityList();
    }
}
