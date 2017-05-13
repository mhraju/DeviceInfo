package com.mhraju.deviceinfo;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.mhraju.deviceinfo.Adapter.PagerAdapter;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import com.mhraju.deviceinfo.Adapter.ViewPagerAdapter;
import com.mhraju.deviceinfo.Fragment.DeviceFragment;
import com.mhraju.deviceinfo.Fragment.MemoryFragment;
import com.mhraju.deviceinfo.Fragment.OSFragment;
import com.mhraju.deviceinfo.Fragment.ScreenFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String mReport = "Write your Report...";
    private String mAppName = "Device info";
    private InterstitialAd mInterstitialAd;
    private FloatingActionButton fab;


    private static final String[] SEND_TO_EMAIL = new String[]{
            "report@google.com"
    };

    private ViewPager viewPager;
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private Menu menu;
    private String[] pageTitle = {"Device", "OS", "Screen", "Memory", "Battery"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);


        setSupportActionBar(toolbar);

        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (int i = 0; i < 5; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



        //handling navigation view item event
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);





        FragmentManager manager=getSupportFragmentManager();
     /*   PagerAdapter adapter=new PagerAdapter(manager);
        viewPager.setAdapter(adapter);
        */


     //   viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        //change Tab selection when swipe ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        //change ViewPager page when tab selected
       /* tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

        /*fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showIntAdd();

            }
        });

        mInterstitialAd = createNewIntAd();
        loadIntAdd();*/


    }



    private InterstitialAd createNewIntAd() {
        InterstitialAd intAd = new InterstitialAd(this);
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
        Toast t = Toast.makeText(this,"You have reached Level Two !!",Toast.LENGTH_LONG);
        t.show();
    }



    private void fabClicked(){

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
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



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Device) {
            viewPager.setCurrentItem(0);
        } else if (id == R.id.OS) {
            viewPager.setCurrentItem(1);
        } else if (id == R.id.Screen) {
            viewPager.setCurrentItem(2);
        }else if (id == R.id.Memory) {
            viewPager.setCurrentItem(3);
        }else if (id == R.id.Battery) {
            viewPager.setCurrentItem(4);
        }/*else if (id == R.id.go) {
            Intent intent = new Intent(this, DesActivity.class);
            intent.putExtra("string", "Go to other Activity by NavigationView item cliked!");
            startActivity(intent);
        } else if (id == R.id.close) {
            finish();
        }*/

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                new AlertDialog.Builder(this)
                        .setTitle("Powered by : Tech_Nerds")
                        .setMessage(
                                "Email : technerds1993@gmail.com\n\n" +
                                        "Blog : https://mhraju.github.io\n")
                        .setPositiveButton("OK", null)
                        .show();
                return true;
/*
            case R.id.create_new_pattern:
                Intent intent = new Intent(MainActivity.this, ChangeActivity.class);
                startActivity(intent);
                finish();
                return true;*/

            default:
                return super.onOptionsItemSelected(item);
        }
    }



   /* @SuppressWarnings("StatementWithEmptyBody")

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_device:
                viewPager.setCurrentItem(0);
                break;
            case R.id.nav_os:
                viewPager.setCurrentItem(1);
                break;
            case R.id.nav_screen:
                viewPager.setCurrentItem(2);
                break;
            case R.id.nav_memory:
                viewPager.setCurrentItem(3);
                break;
            case R.id.nav_battery:
                viewPager.setCurrentItem(4);
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }*/
}
