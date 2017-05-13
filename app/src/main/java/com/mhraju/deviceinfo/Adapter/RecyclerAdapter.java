package com.mhraju.deviceinfo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mhraju.deviceinfo.Model.DeviceInfo;
import com.mhraju.deviceinfo.R;

import java.util.List;

/**
 * Created by Maksudul Hasan Raju on 11/5/2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<DeviceInfo> deviceInfoList;


    public RecyclerAdapter(List<DeviceInfo> DsList) {
        this.deviceInfoList = DsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.device_card_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DeviceInfo deviceInfo = deviceInfoList.get(position);
        holder.title.setText(deviceInfo.getmTitle());
        holder.info.setText(deviceInfo.getmInfo());
    }

    @Override
    public int getItemCount() {
        return deviceInfoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, info;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            info = (TextView) view.findViewById(R.id.info);
        }
    }
}
