package com.example.sensapp_v11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.security.Provider;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Object savedInstanceState;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuHandler();

    }


    public void MenuHandler() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_view);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_acc:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccFragment()).commit();
                break;
            case R.id.nav_gyro:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GyroFragment()).commit();
                break;
            case R.id.nav_magno:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MagnoFragment()).commit();
                break;
            case R.id.nav_light:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LightFragment()).commit();
                break;
            case R.id.nav_pressure:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PressureFragment()).commit();
                break;
            case R.id.nav_temp:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TempFragment()).commit();
                break;
            case R.id.nav_humi:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HumiFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

}