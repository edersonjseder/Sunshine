package sunshine.training.com.sunshine_project.app;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONObject;

import sunshine.training.com.sunshine_project.R;
import sunshine.training.com.sunshine_project.fragment.FragmentListForecast;
import sunshine.training.com.sunshine_project.fragment.FragmentRequestWeb;

public class MainActivity extends AppCompatActivity implements FragmentListForecast.OnFragmentInteractionListener {

    private FragmentTransaction transaction;
    private FragmentListForecast mFragmentListForecast;
    private FragmentRequestWeb mFragmentRequestWeb;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_main);
        jsonObject = null;

        if (savedInstanceState == null) {
            startFragmentForecast();
        }
    }

    /**
     * This method start a fragment from another package
     * **/
    private void startFragmentForecast() {
//        mFragmentListForecast = new FragmentListForecast();
        mFragmentRequestWeb = new FragmentRequestWeb();

        try {

            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, mFragmentRequestWeb);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
