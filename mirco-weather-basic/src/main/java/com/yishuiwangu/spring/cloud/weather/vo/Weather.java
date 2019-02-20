package com.yishuiwangu.spring.cloud.weather.vo;



import java.io.Serializable;
import java.util.List;

/**
 * 天气信息.
 */
public class Weather implements Serializable {

	private static final long serialVersionUID = 1L;

	private Yeaterday yesterday;
	private String city;
	private List<Forecast> forecast;
	private String ganmao;
	private String wendu;
	//private String aqi;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Yeaterday getYesterday() {
		return yesterday;
	}

	public void setYesterday(Yeaterday yesterday) {
		this.yesterday = yesterday;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Forecast> getForecast() {
		return forecast;
	}

	public void setForecast(List<Forecast> forecast) {
		this.forecast = forecast;
	}

	public String getGanmao() {
		return ganmao;
	}

	public void setGanmao(String ganmao) {
		this.ganmao = ganmao;
	}

	public String getWendu() {
		return wendu;
	}

	public void setWendu(String wendu) {
		this.wendu = wendu;
	}
}
