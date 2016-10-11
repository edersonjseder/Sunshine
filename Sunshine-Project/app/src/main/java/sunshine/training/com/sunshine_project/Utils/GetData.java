package sunshine.training.com.sunshine_project.Utils;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import sunshine.training.com.sunshine_project.interfaces.OnPostTaskInterface;
import sunshine.training.com.sunshine_project.model.Weather;
import sunshine.training.com.sunshine_project.path.Path;

/**
 * Created by root on 08/10/16.
 */

public class GetData extends AsyncTask<String, Void, String> {

    String cityName;
    TextView tvResult;
    HttpURLConnection connection = null;
    QueryRequest queryRequest;
    OnPostTaskInterface mOnPostTaskInterface;

    public GetData (String cityName, TextView textViewResut){
        this.cityName = cityName;
        this.tvResult = textViewResut;
    }

    public GetData (String cityName, OnPostTaskInterface mOnPostTaskInterface){
        this.cityName = cityName;
        this.mOnPostTaskInterface = mOnPostTaskInterface;
    }

    @Override
    protected String doInBackground(String... strings) {

        String queryReturn = "";
        String query = null;
        queryRequest = new QueryRequest();

        try {

            query = Path.URL_PATH + URLEncoder.encode(cityName, "UTF-8") + Path.metricKey + Path.cnt + Path.dummyKey + Path.KEY;
            queryReturn = queryRequest.doQuery(query);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            queryReturn = e.getMessage();

        } catch (IOException e) {
            e.printStackTrace();
            queryReturn = e.getMessage();

        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }


        return queryReturn;
    }

    @Override
    protected void onPostExecute(String result) {
        mOnPostTaskInterface.onTaskCompleted(result);
    }
}
