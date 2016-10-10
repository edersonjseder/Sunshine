package sunshine.training.com.sunshine_project.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sunshine.training.com.sunshine_project.R;
import sunshine.training.com.sunshine_project.adapter.WeatherListAdapter;
import sunshine.training.com.sunshine_project.Utils.GetData;
import sunshine.training.com.sunshine_project.lists.ListForecasts;
import sunshine.training.com.sunshine_project.model.InfoTemp;

public class FragmentListForecast extends Fragment {

    private OnFragmentInteractionListener mListener;
    private List<InfoTemp> listForecast;
    private ListView forecastListView;
    private List<String> forecastStringList;
    private String situationInSky[] = {"Today - Cloudy - 88/63", "Tomorrow - Rainy - 77/52", "Monday - Windy - 42/85", "Tuesday - Stormy - 63/74", "Wedneday - Foggy - 77/87",
            "Thursday - Cloudy - 87/97", "Friday - Rainy - 77/67", "Saturday - Windy - 27/47", "Sunday - Stormy - 27/37", "Monday - Cloudy - 37/47"};
    private ArrayAdapter<String> mArrayAdapter;
    private WeatherListAdapter weatherListAdapter;
    private ListForecasts listForecasts;
    private GetData getData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        listForecasts = new ListForecasts();
        listForecast = new ArrayList<InfoTemp>();
        listForecast = listForecasts.fillForecastList();

//        weatherListAdapter = new WeatherListAdapter(getContext(), listForecast);

//        forecastStringList = new ArrayList<String>(Arrays.asList(situationInSky));
//
//        mArrayAdapter = new ArrayAdapter<String>(
//                getActivity(),
//                R.layout.list_item_forecast,
//                R.id.list_item_forecast_textview,
//                forecastStringList
//        );

        forecastListView = (ListView) v.findViewById(R.id.listview_forecast);
        forecastListView.setAdapter(weatherListAdapter);

        return v;
    }

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
