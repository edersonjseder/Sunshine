package sunshine.training.com.sunshine_project.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import sunshine.training.com.sunshine_project.R;
import sunshine.training.com.sunshine_project.Utils.GetData;
import sunshine.training.com.sunshine_project.Utils.ParseJSONToJava;
import sunshine.training.com.sunshine_project.Utils.WeatherInfoData;
import sunshine.training.com.sunshine_project.adapter.WeatherListAdapter;
import sunshine.training.com.sunshine_project.interfaces.OnPostTaskInterface;
import sunshine.training.com.sunshine_project.model.Weather;


public class FragmentRequestWeb extends Fragment implements OnPostTaskInterface {

    private FragmentListForecast.OnFragmentInteractionListener mListener;
    private EditText editTextCityName;
    private Button btnByCityName;
    private ListView listViewWeather;
    private WeatherListAdapter weatherListAdapter;
    private GetData getWeatherInfo;
    Weather weather;
    private ParseJSONToJava parseJSONToJava;
    OnPostTaskInterface mOnPostTaskInterface;
    private String rst;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_requests, container, false);

        editTextCityName = (EditText) v.findViewById(R.id.edTextCityName);
        btnByCityName = (Button) v.findViewById(R.id.btnCityName);
        listViewWeather = (ListView) v.findViewById(R.id.listview_weather);

        btnByCityName.setOnClickListener(onClickListenerGetWeather);

        mOnPostTaskInterface = this;
        parseJSONToJava = new ParseJSONToJava();

        return v;
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
    public void onTaskCompleted(String result) {
        convertJSON(result);
    }

    private void convertJSON(String json){

        weather = new Weather();
        weather = (Weather) parseJSONToJava.convertToJava(json);
        weatherListAdapter = new WeatherListAdapter(getContext(), weather);
        listViewWeather.setAdapter(weatherListAdapter);

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
