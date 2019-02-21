package com.yishuiwangu.spring.cloud.weather.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * 解析XML文件工具类
 */
public class XmlBuilderUtil {

    /**
     * 将XML转换为制定的对象
     * @param clazz
     * @param xmlStr
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    public static Object xmlStrToObject(Class<?> clazz,String xmlStr) throws JAXBException,IOException{
        Object object = null;
        Reader reader = null;
        JAXBContext context = JAXBContext.newInstance(clazz);
        //XML转为对象的接口
        Unmarshaller unmarshaller = context.createUnmarshaller();
        reader = new StringReader(xmlStr);
        object = unmarshaller.unmarshal(reader);
        if(null != reader){
            reader.close();
        }
        return object;
    }
}











