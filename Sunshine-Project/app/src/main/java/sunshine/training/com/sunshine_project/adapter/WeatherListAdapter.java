package sunshine.training.com.sunshine_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import sunshine.training.com.sunshine_project.R;
import sunshine.training.com.sunshine_project.model.InfoTemp;
import sunshine.training.com.sunshine_project.model.Temp;
import sunshine.training.com.sunshine_project.model.Weather;

/**
 * Created by root on 08/10/16.
 */

public class WeatherListAdapter extends BaseAdapter {

    private List<Temp> listWeather;
    private Weather weather;
    private LayoutInflater layoutInflater;
    private SimpleDateFormat simpleDateFormat;

    public WeatherListAdapter(Context context, Weather weather){
        this.listWeather = weather.getList();
        this.weather = weather;
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
        String date = simpleDateFormat.format(new Date(Long.parseLong(forecast.getDt()) * 1000 ));

        if(forecast != null){
            txtName.setText( date + " - " + weather.getCity().getName() + ", " + weather.getCity().getCountry() + " - " + forecast.getTemp().getMin() + "/" + forecast.getTemp().getMax());
        }

        return view;
    }
}
