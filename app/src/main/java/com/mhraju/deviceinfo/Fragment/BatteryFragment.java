package com.mhraju.deviceinfo.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mhraju.deviceinfo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BatteryFragment extends Fragment {

    private TextView batteryLevel;
    private TextView voltageLevel;
    private TextView temperatureLevel;
    private AdView mAdView;


    public BatteryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_battery, container, false);
        batteryLevel = (TextView)view.findViewById(R.id.battery_level);
        voltageLevel = (TextView)view.findViewById(R.id.voltage_level);
        temperatureLevel = (TextView)view.findViewById(R.id.temperature_level);

        mAdView = (AdView)view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice("ca-app-pub-6008099628983320~4249007494")
                .build();
        mAdView.loadAd(adRequest);

        getActivity().registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        return view;

    }


    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            int voltage = intent.getIntExtra("voltage", 0);
            int temperature = intent.getIntExtra("temperature", 0);
            batteryLevel.setText("Battery Status: " + String.valueOf(level) + "%");
            voltageLevel.setText("Battery Voltage: " + String.valueOf(voltage));
            double temps = (double) temperature / 10;
            temperatureLevel.setText("Battery Temperature: " + String.valueOf(temps) + "F");
        }
    };

}