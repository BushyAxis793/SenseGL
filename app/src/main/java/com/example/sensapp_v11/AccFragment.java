package com.example.sensapp_v11;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import static androidx.core.content.ContextCompat.getSystemService;

public class AccFragment extends Fragment implements SensorEventListener {

    TextView xAccTV, yAccTV, zAccTV;
    SensorManager sensorManager;
    Sensor accelerometer;
    boolean hasAcc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_acc, container, false);

        xAccTV = (TextView) v.findViewById(R.id.AccValueX);
        yAccTV = (TextView) v.findViewById(R.id.AccValueY);
        zAccTV = (TextView) v.findViewById(R.id.AccValueZ);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(AccFragment.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        PackageManager manager = getActivity().getPackageManager();

        hasAcc = manager.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER);

        if (!hasAcc) {

            xAccTV.setText("Accelerometer not supported on this device");

        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            xAccTV.setText("X: " + String.format("%.4f", +sensorEvent.values[0]) + "\t\t\t" + "[m/s^2]");
            yAccTV.setText("Y: " + String.format("%.4f", +sensorEvent.values[1]) + "\t\t\t" + "[m/s^2]");
            zAccTV.setText("Z: " + String.format("%.4f", +sensorEvent.values[2]) + "\t\t\t" + "[m/s^2]");

        } else {

            xAccTV.setText("Accelerometer not supported on this device");
            yAccTV.setText("");
            zAccTV.setText("");
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
