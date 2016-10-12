package sunshine.training.com.sunshine_project.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

        View view = layoutInflater.inflate(R.layout.fragment_response_tablerow_list, parent, false);

        textviewDate = (TextView) view.findViewById(R.id.textview_date);
        textviewMaxTemp = (TextView) view.findViewById(R.id.textview_max_temp);
        textviewMinTemp = (TextView) view.findViewById(R.id.textview_min_temp);
        imageView = (ImageView) view.findViewById(R.id.weatherIcon);

        Temp forecast = listWeather.get(position);
        String date = simpleDateFormat.format(new Date(Long.parseLong(forecast.getDt()) * 1000 ));

        if(forecast != null){
            textviewDate.setText(date);
            textviewMaxTemp.setText(forecast.getTemp().getMax().toString());
            textviewMinTemp.setText(forecast.getTemp().getMin().toString());

            for (int i = 0; i < forecast.getWeather().size(); i++){
                if (forecast.getWeather().get(i).getIconByte() != null && forecast.getWeather().get(i).getIconByte().length > 0) {
                    Bitmap img = BitmapFactory.decodeByteArray(forecast.getWeather().get(i).getIconByte(), 0, forecast.getWeather().get(i).getIconByte().length);
                    imageView.setImageBitmap(img);
                }
            }

        }

        return view;
    }
}
