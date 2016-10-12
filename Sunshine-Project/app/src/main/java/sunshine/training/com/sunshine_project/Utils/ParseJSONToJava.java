package sunshine.training.com.sunshine_project.Utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import sunshine.training.com.sunshine_project.model.Weather;

/**
 * Created by ederson.js on 10/10/2016.
 */

public class ParseJSONToJava {
    private ObjectMapper mapper;
    private QueryRequest queryRequest;

    public ParseJSONToJava(QueryRequest queryRequest){
        this.queryRequest = queryRequest;
    }

    /**
     *
     * This method parses from JSON format to Java Objects with the Jackson API
     *
     * @param json
     * @return
     * @Original_Base WerbSystique - http://websystique.com/java/json/jackson-convert-java-object-to-from-json/
     */
    public Object convertToJava(String json){
        mapper = new ObjectMapper();
        Weather weatherObject = null;

        try {
            weatherObject = mapper.readValue(json, Weather.class);

            for (int i = 0; i < weatherObject.getList().size(); i++){
                for (int k = 0; k < weatherObject.getList().get(i).getWeather().size(); k++){
                    byte[] imageIcon = queryRequest.doQueryImage(weatherObject.getList().get(i).getWeather().get(k).getIcon());
                    weatherObject.getList().get(i).getWeather().get(k).setIconByte(imageIcon);
                }
            }

        } catch (JsonGenerationException ex) {
            ex.printStackTrace();

        } catch (JsonMappingException ex) {
            ex.printStackTrace();

        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return weatherObject;
    }
}
