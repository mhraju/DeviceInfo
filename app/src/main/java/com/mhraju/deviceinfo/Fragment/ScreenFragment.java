package com.mhraju.deviceinfo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
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

public class ScreenFragment extends Fragment {

    private List<DeviceInfo> screenInfoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    DisplayMetrics metrics = new DisplayMetrics();
    private AdView mAdView;
    private String mReport = "Write your Report...";
    private String mAppName = "Device info";
    private InterstitialAd mInterstitialAd;
    private FloatingActionButton fab;


    private static final String[] SEND_TO_EMAIL = new String[]{
            "report@google.com"
    };

    public ScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen, container, false);

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        /*mAdView = (AdView)view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("ca-app-pub-6008099628983320~4249007494")
                .build();
        mAdView.loadAd(adRequest);


*/

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showIntAdd();

            }
        });


        mInterstitialAd = createNewIntAd();
        loadIntAdd();

        mRecyclerAdapter = new RecyclerAdapter(screenInfoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mRecyclerAdapter);

        getScreenInfo();

        return  view;
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

   /* setTextOfLabel(true, "** Density:");
    DisplayMetrics metrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(metrics);
    setTextOfLabel(false, "density: "+metrics.density);
    setTextOfLabel(false, "densityDpi: "+metrics.densityDpi);
    setTextOfLabel(false, "scaledDensity: "+metrics.scaledDensity);
    setTextOfLabel(false, "xdpi: "+metrics.xdpi);
    setTextOfLabel(false, "ydpi: "+metrics.ydpi);
    setTextOfLabel(true, "** Density reference:");
    setTextOfLabel(false, "DENSITY_DEFAULT: "+DisplayMetrics.DENSITY_DEFAULT);
    setTextOfLabel(false, "DENSITY_LOW: "+DisplayMetrics.DENSITY_LOW);
    setTextOfLabel(false, "DENSITY_MEDIUM: "+DisplayMetrics.DENSITY_MEDIUM);
    setTextOfLabel(false, "DENSITY_HIGH: "+DisplayMetrics.DENSITY_HIGH);

    setTextOfLabel(true, "** Screen:");
    setTextOfLabel(false, "heightPixels: "+metrics.heightPixels);
    setTextOfLabel(false, "widthPixels: "+metrics.widthPixels);*/


        private void getScreenInfo() {

           DeviceInfo deviceInfo = new DeviceInfo("Screen Resolution", Integer.toString(metrics.heightPixels) +" * "+ Integer.toString(metrics.widthPixels));
            screenInfoList.add(deviceInfo);

            deviceInfo = new DeviceInfo("Density", Float.toString(metrics.density));
            screenInfoList.add(deviceInfo);

            deviceInfo = new DeviceInfo("Density Dpi", Integer.toString(metrics.densityDpi));
            screenInfoList.add(deviceInfo);


            deviceInfo = new DeviceInfo("Scaled Density", Float.toString(metrics.scaledDensity));
            screenInfoList.add(deviceInfo);


            deviceInfo = new DeviceInfo("xdpi", Float.toString(metrics.xdpi));
            screenInfoList.add(deviceInfo);

            deviceInfo = new DeviceInfo("ydpi", Float.toString(metrics.ydpi));
            screenInfoList.add(deviceInfo);

            deviceInfo = new DeviceInfo("DENSITY_DEFAULT", Integer.toString(DisplayMetrics.DENSITY_DEFAULT));
            screenInfoList.add(deviceInfo);

            deviceInfo = new DeviceInfo("DENSITY_LOW", Integer.toString(DisplayMetrics.DENSITY_LOW));
            screenInfoList.add(deviceInfo);

            deviceInfo = new DeviceInfo("DENSITY_MEDIUM", Integer.toString(DisplayMetrics.DENSITY_MEDIUM));
            screenInfoList.add(deviceInfo);

            deviceInfo = new DeviceInfo("DENSITY_HIGH", Integer.toString(DisplayMetrics.DENSITY_HIGH));
            screenInfoList.add(deviceInfo);


        }


}
