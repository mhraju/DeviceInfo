package com.mhraju.deviceinfo.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.mhraju.deviceinfo.Adapter.RecyclerAdapter;
import com.mhraju.deviceinfo.Model.DeviceInfo;
import com.mhraju.deviceinfo.R;

import java.util.ArrayList;
import java.util.List;

import static com.mhraju.deviceinfo.R.id.fab;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeviceFragment extends Fragment {

    private List<DeviceInfo> deviceInfoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private AdView mAdView;
    private String mReport = "Write your Report...";
    private String mAppName = "Device info";
    private InterstitialAd mInterstitialAd;
    private FloatingActionButton fab;


    private static final String[] SEND_TO_EMAIL = new String[]{
            "report@google.com"
    };


    public DeviceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_device, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        /*mAdView = (AdView)view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("ca-app-pub-6008099628983320~4249007494")
                .build();
        mAdView.loadAd(adRequest);*/

         fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showIntAdd();

            }
        });


        mInterstitialAd = createNewIntAd();
        loadIntAdd();

        mRecyclerAdapter = new RecyclerAdapter(deviceInfoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mRecyclerAdapter);

        getDeviceData();

        return view;

    }


    private InterstitialAd createNewIntAd() {
        InterstitialAd intAd = new InterstitialAd(getActivity());
        // set the adUnitId (defined in values/strings.xml)
        intAd.setAdUnitId(getString(R.string.ad_id_interstitial));
        intAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                fab.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                fab.setEnabled(true);
            }

            @Override
            public void onAdClosed() {

                fabClicked();
            }
        });
        return intAd;
    }

    private void showIntAdd() {

// Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            levelTwo();
        }
    }
    private void loadIntAdd() {
        // Disable the  level two button and load the ad.
//        fab.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("ca-app-pub-6008099628983320~4249007494")
                .build();
        mInterstitialAd.loadAd(adRequest);
    }


    private void levelTwo() {
        // Show the next level
        fab.setVisibility(View.INVISIBLE);
        /*mLevelTextView.setText("Level Two");*/
        Toast t = Toast.makeText(getActivity(),"Thank you",Toast.LENGTH_LONG);
        t.show();
    }



    private void fabClicked(){

        /*FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Try your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, SEND_TO_EMAIL);
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, mAppName);
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, mReport);
                //emailIntent.putExtra(Intent.EXTRA_STREAM, u);
                // Send it off to the Activity-Chooser
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });
    }

    private void getDeviceData() {




        DeviceInfo deviceInfo = new DeviceInfo("Brand", Build.BRAND);
        deviceInfoList.add(deviceInfo);

        /*deviceInfo = new DeviceInfo("IMEI", Context.BATTERY_SERVICE);
        deviceInfoList.add(deviceInfo);*/

        deviceInfo = new DeviceInfo("Model", Build.MODEL);
        deviceInfoList.add(deviceInfo);


        deviceInfo = new DeviceInfo("Manufacturer", Build.MANUFACTURER);
        deviceInfoList.add(deviceInfo);


        deviceInfo = new DeviceInfo("Device", Build.DEVICE);
        deviceInfoList.add(deviceInfo);


        deviceInfo = new DeviceInfo("Serial", Build.SERIAL);
        deviceInfoList.add(deviceInfo);


        deviceInfo = new DeviceInfo("Board", Build.BOARD);
        deviceInfoList.add(deviceInfo);

        deviceInfo = new DeviceInfo("Hardware", Build.HARDWARE);
        deviceInfoList.add(deviceInfo);


        deviceInfo = new DeviceInfo("Product", Build.PRODUCT);
        deviceInfoList.add(deviceInfo);

        deviceInfo = new DeviceInfo("Bootloader", Build.BOOTLOADER);
        deviceInfoList.add(deviceInfo);


        deviceInfo = new DeviceInfo("Tags", Build.TAGS);
        deviceInfoList.add(deviceInfo);


        deviceInfo = new DeviceInfo("Radio Version", Build.getRadioVersion());
        deviceInfoList.add(deviceInfo);


        deviceInfo = new DeviceInfo("Host", Build.HOST);
        deviceInfoList.add(deviceInfo);

    }

}