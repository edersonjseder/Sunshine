package sunshine.training.com.sunshine_project.Utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import sunshine.training.com.sunshine_project.path.Path;

/**
 * Created by root on 09/10/16.
 */

public class QueryRequest {


    /**
     * This method is the responsible for make the connection with the API from the
     * Company OpenWeatherMap, available on the web site http://openweathermap.org/ and
     * from there we get the JSON data and use it to be shown on the screen
     *
     * @param cityName
     * @return
     * @throws IOException
     * @Author edersonjseder
     * @Original_Base Android-er - http://android-er.blogspot.com.br/2015/10/android-query-current-weather-using.html
     * @Original_Base Java Code Geeks - https://www.javacodegeeks.com/2013/06/android-build-real-weather-app-json-http-and-openweathermap.html
     *
     */

    public String doQuery(String cityName) throws IOException {
        HttpURLConnection connection = null;
        String result = "";
        URL url = new URL(Path.URL_PATH + URLEncoder.encode(cityName, "UTF-8") + Path.metricKey + Path.cnt + Path.dummyKey + Path.KEY);
        connection = (HttpURLConnection) url.openConnection();

        try{

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8192);
                String line = null;

                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }

                bufferedReader.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
            result = e.getMessage();

        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }

        return result;
    }

    public byte[] doQueryImage(String code) throws IOException {
        HttpURLConnection connection = null;
        ByteArrayOutputStream byteArray = null;
        InputStream inputStream = null;

        URL url = new URL(Path.IMG_URL + code + Path.IMG_URL_EXTENTION);
        connection = (HttpURLConnection) url.openConnection();

        try{
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK){

                inputStream = connection.getInputStream();
                byte[] buffer = new byte[1024];
                byteArray = new ByteArrayOutputStream();

                while (inputStream.read(buffer) != -1){
                    byteArray.write(buffer);
                }

            }

            inputStream.close();



        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }

        return byteArray.toByteArray();
    }

}
