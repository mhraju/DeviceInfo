package com.mhraju.deviceinfo.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mhraju.deviceinfo.Adapter.RecyclerAdapter;
import com.mhraju.deviceinfo.Model.DeviceInfo;
import com.mhraju.deviceinfo.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeviceFragment extends Fragment {

    private List<DeviceInfo> deviceInfoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private AdView mAdView;


    public DeviceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_device, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mAdView = (AdView)view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("ca-app-pub-6008099628983320~4249007494")
                .build();
        mAdView.loadAd(adRequest);

        mRecyclerAdapter = new RecyclerAdapter(deviceInfoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mRecyclerAdapter);

        getDeviceData();

        return view;

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