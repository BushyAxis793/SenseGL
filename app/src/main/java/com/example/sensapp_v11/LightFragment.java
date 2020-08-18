package com.example.sensapp_v11;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LightFragment extends Fragment implements SensorEventListener {

    TextView lightTV;
    SensorManager sensorManager;
    Sensor lightSensor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_light, container, false);

        lightTV = (TextView) v.findViewById(R.id.LightValue);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        sensorManager.registerListener(LightFragment.this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {

            lightTV.setText(String.format("%.2f", +(int) sensorEvent.values[0]) + "\t\t\t" + "[lux]");

        } else {

            lightTV.setText("Light Sensor not supported on this device");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
