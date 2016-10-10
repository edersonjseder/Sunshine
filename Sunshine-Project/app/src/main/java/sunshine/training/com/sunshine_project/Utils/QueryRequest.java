package sunshine.training.com.sunshine_project.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by root on 09/10/16.
 */

public class QueryRequest {

    HttpURLConnection connection = null;

    /**
     * This method is the responsible for make the connection with the API from the
     * Company OpenWeatherMap, available on the web site http://openweathermap.org/ and
     * from there we get the JSON data and use it to be shown on the screen
     *
     * @param query
     * @return
     * @throws IOException
     * @Author edersonjseder
     * @Original_Base Android-er - http://android-er.blogspot.com.br/2015/10/android-query-current-weather-using.html
     * @Original_Base Java Code Geeks - https://www.javacodegeeks.com/2013/06/android-build-real-weather-app-json-http-and-openweathermap.html
     *
     */

    public String doQuery(String query) throws IOException {
        String result = "";
        URL url = new URL(query);
        connection = (HttpURLConnection) url.openConnection();

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8192);
            String line = null;

            while ((line = bufferedReader.readLine()) != null){
                result += line;
            }

            bufferedReader.close();
        }

        return result;
    }

}
