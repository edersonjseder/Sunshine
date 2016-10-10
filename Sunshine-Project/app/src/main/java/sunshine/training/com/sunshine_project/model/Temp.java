package sunshine.training.com.sunshine_project.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ederson.js on 10/10/2016.
 */

public class Temp implements Serializable {
    private String dt;
    private InfoTemp temp;
    private Double pressure;
    private Long humidity;
    private List<InfoWeather> weather;
    private Double speed;
    private Long deg;
    private Long clouds;
    private Double rain;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public InfoTemp getTemp() {
        return temp;
    }

    public void setTemp(InfoTemp temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    public List<InfoWeather> getWeather() {
        return weather;
    }

    public void setWeather(List<InfoWeather> weather) {
        this.weather = weather;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Long getDeg() {
        return deg;
    }

    public void setDeg(Long deg) {
        this.deg = deg;
    }

    public Long getClouds() {
        return clouds;
    }

    public void setClouds(Long clouds) {
        this.clouds = clouds;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }
}
