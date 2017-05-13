package com.mhraju.deviceinfo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhraju.deviceinfo.Model.Memory;
import com.mhraju.deviceinfo.R;

import java.util.List;

/**
 * Created by Maksudul Hasan Raju on 11/6/2016.
 */
public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.MyViewHolder> {

    private List<Memory> memoryList;

    public MemoryAdapter(List<Memory> DsList) {
        this.memoryList = DsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.memory_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Memory memory = memoryList.get(position);
        holder.title.setText(memory.getTitle());
        holder.usedSpace.setText(memory.getUsedSpace());
        holder.freeSpace.setText(memory.getFreeSpace());
        holder.totalSpace.setText(memory.getTotalSpace());
        holder.imageView.setImageResource(memory.getImage());
    }

    @Override
    public int getItemCount() {
        return memoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, usedSpace, freeSpace, totalSpace;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.memory_title);
            usedSpace = (TextView) view.findViewById(R.id.used_space);
            freeSpace = (TextView) view.findViewById(R.id.free_space);
            totalSpace = (TextView) view.findViewById(R.id.total_space);
            imageView = (ImageView) view.findViewById(R.id.pie_image);
        }
    }
}
