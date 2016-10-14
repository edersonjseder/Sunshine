package sunshine.training.com.sunshine_project.Utils;

import android.graphics.Bitmap;
import android.util.Log;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import sunshine.training.com.sunshine_project.model.Weather;

/**
 * Created by ederson.js on 10/10/2016.
 */

public class ParseJSONToJava {
    private static final String TAG = "ParseJSONToJava";

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
        Log.i(TAG, "ParseJSONToJava.convertToJava() inside method - param value: " + json);
        mapper = new ObjectMapper();
        Weather weatherObject = null;

        try {
            Log.i(TAG, "ParseJSONToJava.convertToJava() inside try/catch block - param value: " + json);
            weatherObject = mapper.readValue(json, Weather.class);

            for (int i = 0; i < weatherObject.getList().size(); i++){
                Log.i(TAG, "ParseJSONToJava.convertToJava() inside for loop - object size: " + weatherObject.getList().size() + " - count: " + i);
                for (int k = 0; k < weatherObject.getList().get(i).getWeather().size(); k++){
                    Log.i(TAG, "ParseJSONToJava.convertToJava() inside other for loop - object size: " + weatherObject.getList().get(i).getWeather().size());
                    Bitmap imageIcon = queryRequest.getImageWithQuery(weatherObject.getList().get(i).getWeather().get(k).getIcon());
                    weatherObject.getList().get(i).getWeather().get(k).setIconByte(imageIcon);
                }
            }

        } catch (JsonGenerationException ex) {
            Log.i(TAG, "ParseJSONToJava.convertToJava() inside catch block: Exception - JsonGenerationException - message: " + ex.getMessage());
            ex.printStackTrace();

        } catch (JsonMappingException ex) {
            Log.i(TAG, "ParseJSONToJava.convertToJava() inside catch block: Exception - JsonMappingException - message: " + ex.getMessage());
            ex.printStackTrace();

        } catch (IOException ex) {
            Log.i(TAG, "ParseJSONToJava.convertToJava() inside catch block: Exception - IOException - message: " + ex.getMessage());
            ex.printStackTrace();

        }
        return weatherObject;
    }
}
