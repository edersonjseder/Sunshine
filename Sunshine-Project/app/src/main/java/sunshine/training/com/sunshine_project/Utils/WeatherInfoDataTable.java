package sunshine.training.com.sunshine_project.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import sunshine.training.com.sunshine_project.R;
import sunshine.training.com.sunshine_project.model.Weather;

/**
 * Created by ederson.js on 10/10/2016.
 */
public class WeatherInfoDataTable {

    private View tableRow;
    private TableLayout layoutList;
    private TextView textviewHumidity;
    private TextView textviewMaxTemp;
    private TextView textviewMinTemp;

    public void getTableWeatherInfo(Weather weather, Context context, TableLayout tableList){

        layoutList = tableList;

        for (int i = 0; i < weather.getList().size(); i++){

            tableRow = LayoutInflater.from(context).inflate(R.layout.fragment_response_tablerow_list, null, false);

            textviewHumidity = (TextView) tableRow.findViewById(R.id.textview_date);
            textviewMaxTemp = (TextView) tableRow.findViewById(R.id.textview_max_temp);
            textviewMinTemp = (TextView) tableRow.findViewById(R.id.textview_min_temp);

            textviewHumidity.setText(weather.getList().get(i).getHumidity().toString());
            textviewMaxTemp.setText(weather.getList().get(i).getTemp().getMax().toString());
            textviewMinTemp.setText(weather.getList().get(i).getTemp().getMin().toString());

            layoutList.addView(tableRow);
        }

    }
}
