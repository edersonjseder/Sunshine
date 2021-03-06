package sunshine.training.com.sunshine_project.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
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
 * Abstract AsyncTask class to connect to the Weather Website
 * and get the JSON weather info
 *
 * Created by root on 08/10/16.
 */

public class GetData extends AsyncTask<String, Void, Weather> {
    private static final String TAG = "GetData";

    private String cityName;
    private TextView tvResult;
    private HttpURLConnection connection = null;
    private QueryRequest queryRequest;
    private OnPostTaskInterface mOnPostTaskInterface;
    private Weather weather;
    private ParseJSONToJava parseJSONToJava;
    private Context context;
    private ProgressDialog progress;

    public GetData (Context context, String cityName, OnPostTaskInterface mOnPostTaskInterface){
        Log.i(TAG, "GetData inside constructor - params - (context): " + context + "String: " + cityName);
        this.context = context;
        this.cityName = cityName;
        this.mOnPostTaskInterface = mOnPostTaskInterface;
    }

    /**
     * This method execute the progress bar on layout while process the user request
     */
    @Override
    protected void onPreExecute() {
        Log.i(TAG, "GetData.onPreExecute() inside method - starting progress bar...");
        progress = new ProgressDialog(context);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.show();

    }

    /**
     * Main method of the class where is executed the background connection task
     *
     * @param strings
     * @return
     */
    @Override
    protected Weather doInBackground(String... strings) {
        Log.i(TAG, "GetData.doInBackground() inside main method - param: " + strings);
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

    /**
     * Method that gets the result of processed data by the doInBackground method
     *
     * @param weather
     */
    @Override
    protected void onPostExecute(Weather weather) {
        Log.i(TAG, "GetData.onPostExecute() inside method - param: " + weather.getCod());
        // Stops the progress bar
        if (progress.isShowing()){
            progress.dismiss();
        }

        // The data is passed to the Fragment class through here
        mOnPostTaskInterface.onTaskCompleted(weather);
    }
}
