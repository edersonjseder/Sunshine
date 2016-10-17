package sunshine.training.com.sunshine_project.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import sunshine.training.com.sunshine_project.R;
import sunshine.training.com.sunshine_project.model.Temp;
import sunshine.training.com.sunshine_project.model.Weather;

/**
 * Created by root on 08/10/16.
 */

public class WeatherListAdapter extends BaseAdapter {
    private static final String TAG = "WeatherListAdapter";

    private List<Temp> listWeather;
    private Weather weather;
    private LayoutInflater layoutInflater;
    private SimpleDateFormat simpleDateFormat;
    private TextView textviewDate;
    private TextView textviewMaxTemp;
    private TextView textviewMinTemp;
    private ImageView imageView;

    public WeatherListAdapter(Context context, Weather weather){
        this.listWeather = weather.getList();
        this.weather = weather;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listWeather != null ? listWeather.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return listWeather != null ? listWeather.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "WeatherListAdapter.getView() inside method - position value: " + position);

        View view = layoutInflater.inflate(R.layout.fragment_response_tablerow_list, parent, false);

        textviewDate = (TextView) view.findViewById(R.id.textview_date);
        textviewMaxTemp = (TextView) view.findViewById(R.id.textview_max_temp);
        textviewMinTemp = (TextView) view.findViewById(R.id.textview_min_temp);
        imageView = (ImageView) view.findViewById(R.id.weatherIcon);

        // Block to define one row coloured and the other don't
        if(position % 2 == 0){
            Log.i(TAG, "WeatherListAdapter.getView() inside if - position value: " + position);
            view.setBackgroundResource(R.drawable.list_selector_even);
            textviewDate.setTextColor(Color.BLUE);
            textviewMaxTemp.setTextColor(Color.BLUE);
            textviewMinTemp.setTextColor(Color.BLUE);
        } else {
            Log.i(TAG, "WeatherListAdapter.getView() inside else - position value: " + position);
            view.setBackgroundResource(R.drawable.list_selector_odd);
        }

        Temp forecast = listWeather.get(position);
        Log.i(TAG, "WeatherListAdapter.getView() inside method - listWeather.get(" + position + ") - forecast: " + forecast);
        String date = formatDate(forecast.getDt());

        if(forecast != null){
            Log.i(TAG, "WeatherListAdapter.getView() inside if - forecast: " + forecast);
            textviewDate.setText(date);
            textviewMaxTemp.setText(roundValues(forecast.getTemp().getMax().toString()));
            textviewMinTemp.setText(roundValues(forecast.getTemp().getMin().toString()));

            for (int i = 0; i < forecast.getWeather().size(); i++){
                Log.i(TAG, "WeatherListAdapter.getView() inside for loop - object size: " + forecast.getWeather().size() + " - count: " + i);
                if (forecast.getWeather().get(i).getIconByte() != null) {
//                    Bitmap img = BitmapFactory.decodeByteArray(forecast.getWeather().get(i).getIconByte(), 0, forecast.getWeather().get(i).getIconByte().length);
                    Bitmap img = forecast.getWeather().get(i).getIconByte();
                    imageView.setImageBitmap(img);
                }
            }

        }

        return view;
    }

    // Method to round the double values of temperature
    private String roundValues(String value){
        long rounded = 0;
        String formatted = "";

        try {
            rounded = Math.round(Double.parseDouble(value));

        } catch (NumberFormatException e){
            e.printStackTrace();
        }

        formatted = String.valueOf(rounded) + "° C";

        return formatted;
    }

    // Method to format date to dd/MM/yyyy format
    private String formatDate(String dateInfo){
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = "";

        try {
            date = simpleDateFormat.format(new Date(Long.parseLong(dateInfo) * 1000 ));

        } catch (NumberFormatException ex){
            ex.printStackTrace();

        } catch (Exception e){
            e.printStackTrace();
        }


        return date;
    }
}
