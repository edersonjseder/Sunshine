package sunshine.training.com.sunshine_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import sunshine.training.com.sunshine_project.R;
import sunshine.training.com.sunshine_project.model.InfoTemp;
import sunshine.training.com.sunshine_project.model.Temp;
import sunshine.training.com.sunshine_project.model.Weather;

/**
 * Created by root on 08/10/16.
 */

public class WeatherListAdapter extends BaseAdapter {

    List<Temp> listWeather;
    Weather weather;
    LayoutInflater layoutInflater;

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

        View view = layoutInflater.inflate(R.layout.list_item_forecast, parent, false);
        TextView txtName = (TextView) view.findViewById(R.id.list_item_forecast_textview);
        Temp forecast = listWeather.get(position);

        if(forecast != null){
            txtName.setText(forecast.getDt() + " - " + weather.getCity().getName() + ", " + weather.getCity().getCountry() + " - " + forecast.getTemp().getMin() + "/" + forecast.getTemp().getMax());
        }

        return view;
    }
}
