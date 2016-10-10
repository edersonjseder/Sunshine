package sunshine.training.com.sunshine_project.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ederson.js on 10/10/2016.
 */

public class Weather  implements Serializable {
    private City city;
    private String cod;
    private Double message;
    private Double cnt;
    private List<Temp> list;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Double getCnt() {
        return cnt;
    }

    public void setCnt(Double cnt) {
        this.cnt = cnt;
    }

    public List<Temp> getList() {
        return list;
    }

    public void setList(List<Temp> list) {
        this.list = list;
    }
}
