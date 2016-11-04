package sunshine.training.com.sunshine_project.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import sunshine.training.com.sunshine_project.path.Path;

/**
 * Created by root on 09/10/16.
 */

public class QueryRequest {

    private static final String TAG = "QueryRequest";

    /**
     * This method is responsible for make the connection with the API from the
     * Company OpenWeatherMap, available on the web site http://openweathermap.org/ and
     * from there we get the JSON data and use it to be shown on the screen
     *
     * @param cityName This param comes from the user as an info requirement
     * @return The String that contains the JSON info if any connection problem occur
     * @throws IOException - if a network connection problem or any other problem occur during the request
     * @Author edersonjseder
     * @Original_Base Android-er - http://android-er.blogspot.com.br/2015/10/android-query-current-weather-using.html
     * @Original_Base Java Code Geeks - https://www.javacodegeeks.com/2013/06/android-build-real-weather-app-json-http-and-openweathermap.html
     *
     */

    public String doQuery(String cityName) throws IOException {
        Log.i(TAG, "QueryRequest.doQuery() inside method - param value: " + cityName);
        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        String result = "";

        try{
            Log.i(TAG, "QueryRequest.doQuery() inside try/catch block - param value: " +cityName);
            // Gets the URL with the param cityName, and with it concatenated on the url gets the JSON weather info
            URL url = new URL(Path.URL_PATH + URLEncoder.encode(cityName, "UTF-8") + Path.metricKey + Path.cnt + Path.dummyKey + Path.KEY);
            Log.i(TAG, "QueryRequest.doQuery() inside try/catch block - url value: " +url);
            // Open the connection with the URL here
            connection = (HttpURLConnection) url.openConnection();

            // Check if the open connection is returned
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                Log.i(TAG, "QueryRequest.doQuery() inside try/catch block - inside if (" + connection.getResponseCode() + ") == (" + HttpURLConnection.HTTP_OK + ")");
                // Gets the JSON info
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader, 8192);
                String line = null;

                // Read the info here
                while ((line = bufferedReader.readLine()) != null){
                    Log.i(TAG, "QueryRequest.doQuery() inside try/catch block - inside if - while (" + bufferedReader.readLine() + ") != " + null + ": " + line);
                    result += line;
                }
            }

        } catch (IOException e) {
            Log.i(TAG, "QueryRequest.doQuery() inside catch block - message: " + e.getMessage());
            e.printStackTrace();
            result = e.getMessage();

        } finally {
            Log.i(TAG, "QueryRequest.doQuery() inside finally block");
            if (connection != null){
                bufferedReader.close();
                connection.disconnect();
            }
        }

        return result;
    }

    public byte[] doQueryImage(String code) throws IOException {
        HttpURLConnection connection = null;
        ByteArrayOutputStream byteArray = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try{

            URL url = new URL(Path.IMG_URL + code + Path.IMG_URL_EXTENTION);
            connection = (HttpURLConnection) url.openConnection();

            Log.v("Debug", "URL: " + url);

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

    /**
     *
     * This method is responsible for make the connection with the API from the
     * Company OpenWeatherMap, available on the web site http://openweathermap.org/ and
     * from there we get the Image data through the String containing the code of image
     * and use it to be shown on the screen
     *
     * @param code
     * @return
     * @throws IOException
     * @Original_Base StackOverflow - http://stackoverflow.com/questions/3090650/android-loading-an-image-from-the-web-with-asynctask
     */
    public Bitmap getImageWithQuery(String code) throws IOException {
        Log.i(TAG, "QueryRequest.getImageWithQuery() inside method - param value: " + code);
        HttpURLConnection connection = null;
        Bitmap imageBitmap = null;
        InputStream inputStream = null;

        try{
            Log.i(TAG, "QueryRequest.getImageWithQuery() inside try/catch block - param value: " + code);

            // Make the image URL through the code number from the JSON file
            URL url = new URL(Path.IMG_URL + code + Path.IMG_URL_EXTENTION);
            Log.i(TAG, "QueryRequest.getImageWithQuery() inside try/catch block - url value: " + url);

            // Open the connection with the URL here
            connection = (HttpURLConnection) url.openConnection();

            // Get the input stream from the web
            inputStream = connection.getInputStream();
            // Decode the input stream to a bitmap image
            imageBitmap = BitmapFactory.decodeStream(inputStream);
            Log.i(TAG, "QueryRequest.getImageWithQuery() inside try/catch block - imageBitmap value: " + imageBitmap);

            if (imageBitmap == null){
                imageBitmap = null;
            }

        } catch (IOException e) {
            Log.i(TAG, "QueryRequest.getImageWithQuery() inside catch block: " + e.getMessage());
            e.printStackTrace();

        } finally {
            Log.i(TAG, "QueryRequest.getImageWithQuery() inside finally block");
            if (connection != null){
                inputStream.close();
                connection.disconnect();
            }
        }

        return imageBitmap;
    }
}
