package com.mhraju.deviceinfo.Fragment;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mhraju.deviceinfo.Adapter.MemoryAdapter;
import com.mhraju.deviceinfo.Model.Memory;
import com.mhraju.deviceinfo.R;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class MemoryFragment extends Fragment {


    private List<Memory> memoryList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MemoryAdapter mAdapter;
    private AdView mAdView;

    public MemoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memory, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdView = (AdView)view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("ca-app-pub-6008099628983320~4249007494")
                .build();
        mAdView.loadAd(adRequest);

        mAdapter = new MemoryAdapter(memoryList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        getMemoryInfo();

        return  view;
    }


    private void getMemoryInfo(){

        String heading = "RAM Information";
        long totalRamValue = totalRamMemorySize();
        long freeRamValue = freeRamMemorySize();
        long usedRamValue = totalRamValue - freeRamValue;
        int imageIcon = R.drawable.piechart;

        Memory mMemory = new Memory(heading, formatSize(usedRamValue) + " MB", formatSize(freeRamValue) + " MB", formatSize(totalRamValue) + " MB", imageIcon);
        memoryList.add(mMemory);

        String internalMemoryTitle = "Internal Memory Information";
        long totalInternalValue = getTotalInternalMemorySize();
        long freeInternalValue = getAvailableInternalMemorySize();
        long usedInternalValue = totalInternalValue - freeInternalValue;
        int internalIcon = R.drawable.piechartone;

        Memory internalMemory = new Memory(internalMemoryTitle, formatSize(usedInternalValue), formatSize(freeInternalValue), formatSize(totalInternalValue), internalIcon);
        memoryList.add(internalMemory);

        String externalMemoryTitle = "External Memory Information";
        long totalExternalValue = getTotalExternalMemorySize();
        long freeExternalValue = getAvailableExternalMemorySize();
        long usedExternalValue = totalExternalValue - freeExternalValue;
        int externalIcon = R.drawable.piecharttwo;

        Memory externalMemory = new Memory(externalMemoryTitle, formatSize(usedExternalValue), formatSize(freeExternalValue), formatSize(totalExternalValue), externalIcon);
        memoryList.add(externalMemory);

//        MemoryAdapter memoryAdapter = new MemoryAdapter(this, memoryDataSource);
//        memoryList.setAdapter(memoryAdapter);

    }

    private long freeRamMemorySize() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long availableMegs = mi.availMem / 1048576L;

        return availableMegs;
    }

    private long totalRamMemorySize() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager)getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long availableMegs = mi.totalMem / 1048576L;
        return availableMegs;
    }

    public static boolean externalMemoryAvailable() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    public static long getAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } else {
            return 0;
        }
    }

    public static long getTotalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        } else {
            return 0;
        }
    }

    public static String formatSize(long size) {
        String suffix = null;

        if (size >= 1024) {
            suffix = " KB";
            size /= 1024;
            if (size >= 1024) {
                suffix = " MB";
                size /= 1024;
            }
        }
        StringBuilder resultBuffer = new StringBuilder(Long.toString(size));

        int commaOffset = resultBuffer.length() - 3;
        while (commaOffset > 0) {
            resultBuffer.insert(commaOffset, ',');
            commaOffset -= 3;
        }
        if (suffix != null) resultBuffer.append(suffix);
        return resultBuffer.toString();
    }

    private String returnToDecimalPlaces(long values){
        DecimalFormat df = new DecimalFormat("#.00");
        String angleFormated = df.format(values);
        return angleFormated;
    }


}
