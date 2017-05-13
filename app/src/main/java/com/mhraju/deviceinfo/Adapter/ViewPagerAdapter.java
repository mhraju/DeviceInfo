package com.mhraju.deviceinfo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mhraju.deviceinfo.Fragment.BatteryFragment;
import com.mhraju.deviceinfo.Fragment.DeviceFragment;
import com.mhraju.deviceinfo.Fragment.MemoryFragment;
import com.mhraju.deviceinfo.Fragment.OSFragment;
import com.mhraju.deviceinfo.Fragment.ScreenFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag=null;
        switch (position){
            case 0:
                frag=new DeviceFragment();
                break;
            case 1:
                frag=new OSFragment();
                break;
            case 2:
                frag=new ScreenFragment();
                break;
            case 3:
                frag=new MemoryFragment();
                break;
            case 4:
                frag=new BatteryFragment();
                break;
        }
        return frag;

    }

/*        if (position ==0) {
            return new DeviceFragment();
        }
        else if (position == 1) {
            return new OSFragment();
        }
        else if (position == 2) {
            return new ScreenFragment();
        }
        else if (position == 3) {
            return new MemoryFragment();
        }
        else return new BatteryFragment();*/

    @Override
    public int getCount() {
        return 5;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        String title=" ";
        switch (position){
            case 0:
                title="Device";
                break;
            case 1:
                title="OS";
                break;
            case 2:
                title="Screen";
                break;
            case 3:
                title="Memory";
                break;
            case 4:
                title="Battery";
                break;
        }

        return title;
    }
}
