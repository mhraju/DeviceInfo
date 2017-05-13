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


public class OSFragment extends Fragment {
    private List<DeviceInfo> deviceInfoList = new ArrayList<>();
        private RecyclerView recyclerView;
        private RecyclerAdapter mRecyclerAdapter;
    private AdView mAdView;


    public OSFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_os, container, false);

//            ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
//            ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//            activityManager.getMemoryInfo(mi);
//            double availableMegs = mi.availMem / 1048576L;

//Percentage can be calculated for API 16+
           // double percentAvail = mi.availMem / mi.totalMem;

            mAdView = (AdView)view.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice("ca-app-pub-6008099628983320~4249007494")
                    .build();
            mAdView.loadAd(adRequest);

            recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

            mRecyclerAdapter = new RecyclerAdapter(deviceInfoList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            //recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mRecyclerAdapter);
         //   getMemInfo();
            getOsData();

            return view;

        }


    /*private String getMemInfo() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager =
                (ActivityManager)getActivity().getSystemService(Context.ACTIVITY_SERVICE);   // solution from stackoverflow
        activityManager.getMemoryInfo(memoryInfo);

        double availableMegs = memoryInfo.availMem / 1048576L;

//Percentage can be calculated for API 16+
        double percentAvail = memoryInfo.availMem / memoryInfo.totalMem;



        Runtime runtime = Runtime.getRuntime();

        double maxmemory = runtime.maxMemory()/1048576L;

        double totalmem = runtime.totalMemory()/1048576L;

        double freem = runtime.freeMemory()/1048576L;

        String strMemInfo =
                "MemoryInfo.availMem = " + availableMegs  + "\n" + "percent" +percentAvail
                        + "MemoryInfo.totalMem = " + memoryInfo.totalMem + "\n" // API 16
                        + "\n"
                        + "Runtime.maxMemory() = " + maxmemory + "\n"
                        + "Runtime.totalMemory() = " + totalmem + "\n"
                        + "Runtime.freeMemory() = " + freem + "\n";

        return strMemInfo;
    }*/

        private void getOsData() {


            DeviceInfo deviceInfo  = new DeviceInfo("Build Version", Build.VERSION.RELEASE);
            deviceInfoList.add(deviceInfo);

            deviceInfo = new DeviceInfo("Android SDK version", Integer.toString(Build.VERSION.SDK_INT));
            deviceInfoList.add(deviceInfo);

            /*deviceInfo = new DeviceInfo("Base OS", Build.VERSION.BASE_OS);
            deviceInfoList.add(deviceInfo);
*/
            deviceInfo = new DeviceInfo("Version Increment", Build.VERSION.INCREMENTAL);
            deviceInfoList.add(deviceInfo);

            deviceInfo = new DeviceInfo("User", Build.USER);
            deviceInfoList.add(deviceInfo);

            deviceInfo = new DeviceInfo("Cpu", Build.CPU_ABI);
            deviceInfoList.add(deviceInfo);

            deviceInfo = new DeviceInfo("Id", Build.ID);
            deviceInfoList.add(deviceInfo);


            deviceInfo = new DeviceInfo("Type", Build.TYPE);
            deviceInfoList.add(deviceInfo);


            deviceInfo = new DeviceInfo("Time", Long.toString(Build.TIME));
            deviceInfoList.add(deviceInfo);

            deviceInfo = new DeviceInfo("User", Build.USER);
            deviceInfoList.add(deviceInfo);


            deviceInfo = new DeviceInfo("FingerPrint", Build.FINGERPRINT);
            deviceInfoList.add(deviceInfo);

            /*deviceInfo = new DeviceInfo("FingerPrint", Build.VERSION.SECURITY_PATCH);
            deviceInfoList.add(deviceInfo);*/


        }


}
