package sunshine.training.com.sunshine_project.lists;

import java.util.ArrayList;
import java.util.List;

import sunshine.training.com.sunshine_project.model.InfoTemp;

/**
 * Created by ederson.js on 07/10/2016.
 */

public class ListForecasts {

    private String maxTemperature[] = {"21°", "23°", "25°", "27°", "29°", "31°", "33°", "35°", "37°",
            "39°", "41°", "21°", "23°", "25°", "27°", "29°", "31°", "33°", "35°", "37°", "39°", "41°",
            "21°", "23°", "25°", "27°", "29°"};

    private String minTemperature[] = {"6°", "8°", "10°", "12°", "14°", "16°", "18°", "20°", "22°", "24°",
            "26°", "6°", "8°", "10°", "12°", "14°", "16°", "18°", "20°", "22°", "24°", "26°",
            "6°", "8°", "10°", "12°", "14°"};

    private String situationInSky[] = {"Cloudy", "Rainy", "Windy", "Stormy", "Cold", "Dry Air",
            "Wet Air", "Cloudy", "Rainy", "Windy", "Stormy", "Cold", "Dry Air", "Wet Air", "Cloudy",
            "Rainy", "Windy", "Stormy", "Cold", "Dry Air", "Wet Air", "Cloudy", "Rainy", "Windy",
            "Stormy", "Cold", "Dry Air"};

    private String dateTemperature[] = {"07/10/2016", "08/10/2016", "09/10/2016", "10/10/2016", "11/10/2016", "12/10/2016",
            "13/10/2016", "14/10/2016", "15/10/2016", "16/10/2016", "17/10/2016", "18/10/2016", "19/10/2016", "20/10/2016",
            "21/10/2016", "22/10/2016", "23/10/2016", "24/10/2016", "25/10/2016", "26/10/2016", "27/10/2016", "28/10/2016",
            "29/10/2016", "30/10/2016", "31/10/2016", "01/11/2016", "02/11/2016"};

    private String cities[] = {"Rio Branco", "Manaus", "Boa Vista", "Porto Velho", "Belem", "Macapa", "Cuiaba", "Sao Luiz",
            "Palmas", "Campo Grande", "Goiania", "Brasilia", "Fortaleza", "Natal", "Joao Pessoa",
            "Terezina", "Salvador", "Recife", "Maceio", "Aracaju", "Belo Horizonte", "Vitoria",
            "Rio de Janeiro", "São Paulo", "Curitiba", "Florianopolis", "Porto Alegre"};


    public List<InfoTemp> fillForecastList(){
        List<InfoTemp> listForecast = new ArrayList<InfoTemp>();

        for (int i = 0; i < maxTemperature.length; i++){
            InfoTemp forecast = new InfoTemp();
            forecast.setMax(Double.parseDouble(maxTemperature[i]));
            forecast.setMin(Double.parseDouble(minTemperature[i]));
            listForecast.add(forecast);
        }

        return listForecast;

    }

}
