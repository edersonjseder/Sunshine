package sunshine.training.com.sunshine_project.Utils;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import sunshine.training.com.sunshine_project.interfaces.OnPostTaskInterface;
import sunshine.training.com.sunshine_project.model.Weather;
import sunshine.training.com.sunshine_project.path.Path;

import static java.security.AccessController.getContext;

/**
 * Created by root on 08/10/16.
 */

public class GetData extends AsyncTask<String, Void, Weather> {

    private String cityName;
    private TextView tvResult;
    private HttpURLConnection connection = null;
    private QueryRequest queryRequest;
    private OnPostTaskInterface mOnPostTaskInterface;
    private Weather weather;
    private ParseJSONToJava parseJSONToJava;

    public GetData (String cityName, TextView textViewResut){
        this.cityName = cityName;
        this.tvResult = textViewResut;
    }

    public GetData (String cityName, OnPostTaskInterface mOnPostTaskInterface){
        this.cityName = cityName;
        this.mOnPostTaskInterface = mOnPostTaskInterface;
    }

    @Override
    protected Weather doInBackground(String... strings) {

        String queryReturn = "";
        queryRequest = new QueryRequest();
        weather = new Weather();
        parseJSONToJava = new ParseJSONToJava(queryRequest);

        try {
            //Execute connection to get the JSON Object
            queryReturn = queryRequest.doQuery(cityName);
            weather = (Weather) parseJSONToJava.convertToJava(queryReturn);


        } catch (Exception e) {
            e.printStackTrace();
            weather = null;

        }


        return weather;
    }



    @Override
    protected void onPostExecute(Weather weather) {
        mOnPostTaskInterface.onTaskCompleted(weather);
    }
}
