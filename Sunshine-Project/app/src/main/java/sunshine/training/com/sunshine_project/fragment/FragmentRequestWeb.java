package sunshine.training.com.sunshine_project.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import sunshine.training.com.sunshine_project.R;
import sunshine.training.com.sunshine_project.Utils.GetData;
import sunshine.training.com.sunshine_project.Utils.ParseJSONToJava;
import sunshine.training.com.sunshine_project.Utils.WeatherInfoDataTable;
import sunshine.training.com.sunshine_project.adapter.WeatherListAdapter;
import sunshine.training.com.sunshine_project.interfaces.OnPostTaskInterface;
import sunshine.training.com.sunshine_project.model.Weather;


public class FragmentRequestWeb extends Fragment implements OnPostTaskInterface {
    private static final String TAG = "FragmentRequestWeb";

    private FragmentRequestWeb.OnFragmentInteractionListener mListener;
    private EditText editTextCityName;
    private Button btnByCityName;
    private TextView textviewCity;
    private TextView textviewCountry;
    private TextView textviewEmptyTable;
    private LinearLayout tableLayoutHeader;
    private ListView listviewWeather;
    private WeatherListAdapter weatherListAdapter;
    private GetData getWeatherInfo;
    private OnPostTaskInterface mOnPostTaskInterface;
    private LayoutInflater layoutInflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "FragmentRequestWeb.onCreateView() - inside method");

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_requests, container, false);

        editTextCityName = (EditText) v.findViewById(R.id.edTextCityName);
        btnByCityName = (Button) v.findViewById(R.id.btnCityName);
        textviewCity = (TextView) v.findViewById(R.id.textView_city);
        textviewCountry = (TextView) v.findViewById(R.id.textView_country);
        textviewEmptyTable = (TextView) v.findViewById(R.id.textview_empty_table);
        tableLayoutHeader = (LinearLayout) v.findViewById(R.id.response_table_list_header);
        listviewWeather = (ListView) v.findViewById(R.id.listview_weather);

        btnByCityName.setOnClickListener(onClickListenerGetWeather);
        instantiateObjects();

        return v;
    }

    private void instantiateObjects(){
        mOnPostTaskInterface = this;
        layoutInflater = LayoutInflater.from(getContext());

    }

    private View.OnClickListener onClickListenerGetWeather = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getWeatherInfo = (GetData) new GetData(editTextCityName.getText().toString(), mOnPostTaskInterface);
            getWeatherInfo.execute();
        }
    };


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onTaskCompleted(Weather weather) {
        Log.i(TAG, "FragmentRequestWeb.onTaskCompleted() inside method - param value: " + weather);
        try {
            if (weather != null){
                weatherListAdapter = new WeatherListAdapter(getContext(), weather);
                tableLayoutHeader.setVisibility(View.VISIBLE);
                listviewWeather.setAdapter(weatherListAdapter);
                showOtherWeatherInfo(weather);

            }else{
                throw new Exception();
            }

        } catch (Exception e){
            Log.i(TAG, "FragmentRequestWeb.onTaskCompleted() inside catch block - message: " + e.getMessage());
            textviewEmptyTable.setVisibility(View.VISIBLE);
        }

    }

    // Fill the information of City and Country
    public void showOtherWeatherInfo(Weather weather){
        textviewCity.setText(weather.getCity().getName());
        textviewCountry.setText(weather.getCity().getCountry());

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
