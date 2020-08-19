package com.example.sensapp_v11;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
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

public class PressureFragment extends Fragment implements SensorEventListener {

    TextView pressureTV;
    SensorManager sensorManager;
    Sensor pressureSensor;
    Boolean hasPressure;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pressure, container, false);

        pressureTV = (TextView) v.findViewById(R.id.PressureValue);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        sensorManager.registerListener(PressureFragment.this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);

        PackageManager manager = getActivity().getPackageManager();

        hasPressure = manager.hasSystemFeature(PackageManager.FEATURE_SENSOR_BAROMETER);

        if (!hasPressure) {

            pressureTV.setText("Pressure Sensor not supported on this device");
        }
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_PRESSURE) {

            pressureTV.setText(String.format("%.2f", +sensorEvent.values[0]) + "\t\t\t" + "[hPa]");

        } else {

            pressureTV.setText("Pressure Sensor not supported on this device");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
