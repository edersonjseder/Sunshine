package sunshine.training.com.sunshine_project.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONObject;

import sunshine.training.com.sunshine_project.R;
import sunshine.training.com.sunshine_project.fragment.FragmentRequestWeb;

public class MainActivity extends AppCompatActivity implements FragmentRequestWeb.OnFragmentInteractionListener {

    private FragmentTransaction transaction;
    private FragmentRequestWeb mFragmentRequestWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_main);

        if (savedInstanceState == null) {
            startFragmentForecast();
        }
    }

    /**
     * This method start the fragment request web
     *
     **/
    private void startFragmentForecast() {
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
