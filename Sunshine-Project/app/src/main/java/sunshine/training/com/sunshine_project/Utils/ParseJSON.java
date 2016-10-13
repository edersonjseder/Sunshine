package sunshine.training.com.sunshine_project.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 09/10/16.
 */

public class ParseJSON {

    /**
     /**
     * This method is meant to parse from String to JSON Object and show them on the screen
     *
     * @param json
     * @return
     * @throws IOException
     * @Author edersonjseder
     * @Original_Base Android-er - http://android-er.blogspot.com.br/2015/10/android-query-current-weather-using.html
     * @Original_Base Java Code Geeks - https://www.javacodegeeks.com/2013/06/android-build-real-weather-app-json-http-and-openweathermap.html
     *
     */
    public String parseJSON(String json){

        StringBuffer resultJSON = new StringBuffer();

        try {
            //Create the JSON Object here from the String value: json
            JSONObject jsonObject = new JSONObject(json);

            //Use the method to get the String value from the JSON Object
            String cod = getStringFromJSONObject(jsonObject, "cod");

            if (cod != null){
                if (cod.equals("200")){
                    resultJSON.append("Name: ");
                    resultJSON.append(getStringFromJSONObject(jsonObject, "name")).append("\n");

                    //Use the method to get the objects values in the JSON object
                    JSONObject sys = getObjectFromJSONObject(jsonObject, "sys");
                    resultJSON.append("Country: ");

                    if (sys != null){
                        resultJSON.append(getStringFromJSONObject(sys, "country")).append("\n\n");
                    }

                    JSONObject coord = getObjectFromJSONObject(jsonObject, "coord");
                    resultJSON.append("Coordinates: \n");

                    if (coord != null){
                        resultJSON.append("Longitud: ");
                        resultJSON.append(getStringFromJSONObject(coord, "lon")).append("\n");
                        resultJSON.append("Latitud: ");
                        resultJSON.append(getStringFromJSONObject(coord, "lat")).append("\n\n");

                    }

                    JSONArray weather = getArrayObectFromJSONObject(jsonObject, "weather");
                    resultJSON.append("Weather: \n");

                    if(weather != null){
                        for(int i = 0; i < weather.length(); i++){
                            JSONObject thisWeather = weather.getJSONObject(i);

                            resultJSON.append((i + 1) + "° weather: \n");
                            resultJSON.append("Id: ");
                            resultJSON.append(getStringFromJSONObject(thisWeather, "id")).append("\n");
                            resultJSON.append("Situation: ");
                            resultJSON.append(getStringFromJSONObject(thisWeather, "main")).append("\n");
                            resultJSON.append("Description: ");
                            resultJSON.append(getStringFromJSONObject(thisWeather, "description")).append("\n\n");

                        }
                    }

                    JSONObject main = getObjectFromJSONObject(jsonObject, "main");
                    resultJSON.append("Temperature: \n");

                    if(main != null){
                        resultJSON.append("Temperature °F: ");
                        resultJSON.append(getStringFromJSONObject(main, "temp")).append("\n");
                        resultJSON.append("Pressure: ");
                        resultJSON.append(getStringFromJSONObject(main, "pressure")).append("\n");
                        resultJSON.append("Humidity: ");
                        resultJSON.append(getStringFromJSONObject(main, "humidity")).append("\n");
                        resultJSON.append("Min Temperature °F: ");
                        resultJSON.append(getStringFromJSONObject(main, "temp_min")).append("\n");
                        resultJSON.append("Max Temperature °F: ");
                        resultJSON.append(getStringFromJSONObject(main, "temp_max")).append("\n");
                        resultJSON.append("Sea Level: ");
                        resultJSON.append(getStringFromJSONObject(main, "sea_level")).append("\n");
                        resultJSON.append("Ground Level: ");
                        resultJSON.append(getStringFromJSONObject(main, "grnd_level")).append("\n\n");

                    }

                    JSONObject wind = getObjectFromJSONObject(jsonObject, "wind");
                    resultJSON.append("Wind: \n");

                    if(wind != null){
                        resultJSON.append("Speed: ");
                        resultJSON.append(getStringFromJSONObject(wind, "speed")).append("\n");
                        resultJSON.append("Deg: ");
                        resultJSON.append(getStringFromJSONObject(wind, "deg")).append("\n\n");

                    }

                    JSONObject clouds = getObjectFromJSONObject(jsonObject, "clouds");
                    resultJSON.append("Clouds: \n");

                    if(clouds != null){
                        resultJSON.append("All: ");
                        resultJSON.append(getStringFromJSONObject(clouds, "all")).append("\n\n");

                    }
                } else if (cod.equals("404")){
                    String message = getStringFromJSONObject(jsonObject, "message");

                    resultJSON.append("Cod 404: ").append(message);

                }
            } else {

                resultJSON.append("Cod == null: ").append("\n");

            }

        } catch (JSONException e) {
            e.printStackTrace();
            resultJSON.append(e.getMessage());
        }

        return resultJSON.toString();
    }

    /**
     *
     * his method is meant to parse from a City Pstal Code String to JSON Object
     * based on the request link with metrics and cnt = 7, and show them on the screen
     *
     * @param json
     * @return
     * @Author edersonjseder
     * @Original_Base Android-er - http://android-er.blogspot.com.br/2015/10/android-query-current-weather-using.html
     * @Original_Base Java Code Geeks - https://www.javacodegeeks.com/2013/06/android-build-real-weather-app-json-http-and-openweathermap.html
     */
    public String parseAcurateJSON(String json){

        StringBuffer resultJSON = new StringBuffer();

        try {
        //Create the JSON Object here from the String value: json
            JSONObject jsonObject = new JSONObject(json);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            //Use the method to get the String value from the JSON Object
            String cod = getStringFromJSONObject(jsonObject, "cod");

            if (cod != null) {
                if (cod.equals("200")) {

                    //Use the method to get the objects values in the JSON object
                    JSONObject city = getObjectFromJSONObject(jsonObject, "city");

                    if (city != null){
                        resultJSON.append("City: ");
                        resultJSON.append(getStringFromJSONObject(city, "name")).append("\n");
                        resultJSON.append("Country: ");
                        resultJSON.append(getStringFromJSONObject(city, "country")).append("\n");

                    }

                    resultJSON.append("Qtde. Days per week: ");
                    resultJSON.append(getStringFromJSONObject(jsonObject, "cnt")).append("\n\n");

                    JSONArray list = getArrayObectFromJSONObject(jsonObject, "list");
                    resultJSON.append("List: \n");

                    if(list != null){
                        for(int i = 0; i < list.length(); i++){
                            JSONObject thisWeather = list.getJSONObject(i);

                            resultJSON.append((i + 1) + "° list: \n");
                            resultJSON.append("Date: ");
                            String date = simpleDateFormat.format(new Date(Long.parseLong(getStringFromJSONObject(thisWeather, "dt")) * 1000 ));
                            resultJSON.append(date).append("\n");

                            JSONObject temp = getObjectFromJSONObject(thisWeather, "temp");
                            resultJSON.append("Temperature: \n");

                            if(temp != null){

                                resultJSON.append("Day: ");
                                resultJSON.append(getStringFromJSONObject(temp, "day")).append("\n");
                                resultJSON.append("InfoTemp min: ");
                                resultJSON.append(getStringFromJSONObject(temp, "min")).append("\n");
                                resultJSON.append("InfoTemp max: ");
                                resultJSON.append(getStringFromJSONObject(temp, "max")).append("\n");
                                resultJSON.append("Night: ");
                                resultJSON.append(getStringFromJSONObject(temp, "night")).append("\n");
                                resultJSON.append("Morning: ");
                                resultJSON.append(getStringFromJSONObject(temp, "morn")).append("\n");

                            }

                            resultJSON.append("Humidity: ");
                            resultJSON.append(getStringFromJSONObject(thisWeather, "humidity")).append("\n");

                            JSONArray weather = getArrayObectFromJSONObject(thisWeather, "weather");
                            resultJSON.append("Weather: \n");

                            if(weather != null){
                                for(int k = 0; k < weather.length(); k++){
                                    JSONObject inWeather = weather.getJSONObject(k);

                                    resultJSON.append((k + 1) + "° weather: \n");
                                    resultJSON.append("Situation: ");
                                    resultJSON.append(getStringFromJSONObject(inWeather, "main")).append("\n");
                                    resultJSON.append("Description: ");
                                    resultJSON.append(getStringFromJSONObject(inWeather, "description")).append("\n");

                                }
                            }

                            resultJSON.append("Speed: ");
                            resultJSON.append(getStringFromJSONObject(thisWeather, "speed")).append("\n");
                            resultJSON.append("Clouds: ");
                            resultJSON.append(getStringFromJSONObject(thisWeather, "clouds")).append("\n\n");

                        }
                    }

                } else if (cod.equals("404")){
                    String message = getStringFromJSONObject(jsonObject, "message");

                    resultJSON.append("Cod 404: ").append(message);

                }
            } else {

                resultJSON.append("Cod == null: ").append("\n");

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultJSON.toString();
    }

    /**
     * This method gets the values from JSON Object based on the key name passed by param
     *
     * @name getStringFromJSONObject()
     *
     * @method getStringFromJSONObject()
     *
     * @param obj
     * @param key
     * @return
     * @Original_Base Android-er - http://android-er.blogspot.com.br/2015/10/android-query-current-weather-using.html
     */
    private String getStringFromJSONObject(JSONObject obj, String key){
        String value = null;

        try {
            value = obj.getString(key);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return value;
    }

    /**
     *
     * This method gets the Object in the JSON Object based on the Object key name pssed by param
     *
     * @param obj
     * @param key
     * @return
     * @Original_Base Android-er - http://android-er.blogspot.com.br/2015/10/android-query-current-weather-using.html
     */
    private JSONObject getObjectFromJSONObject(JSONObject obj, String key){
        JSONObject object = null;

        try {
            object = obj.getJSONObject(key);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }

    /**
     *
     * This method gets the array of Objects in the JSON object based on the key name passed by param
     *
     * @param object
     * @param key
     * @return
     * @Original_Base Android-er - http://android-er.blogspot.com.br/2015/10/android-query-current-weather-using.html
     */
    private JSONArray getArrayObectFromJSONObject(JSONObject object, String key){
        JSONArray array = null;

        try {
            array = object.getJSONArray(key);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array;
    }

}
