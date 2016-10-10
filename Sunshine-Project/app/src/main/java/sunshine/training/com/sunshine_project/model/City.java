package sunshine.training.com.sunshine_project.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by ederson.js on 10/10/2016.
 */

public class City implements Serializable {
    private Long id;
    private String name;
    private String country;
    private Map<String, Long> coord;
    private Long population;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Map<String, Long> getCoord() {
        return coord;
    }

    public void setCoord(Map<String, Long> coord) {
        this.coord = coord;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}
