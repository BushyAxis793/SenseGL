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

public class TempFragment extends Fragment implements SensorEventListener {

    TextView tempTV;
    SensorManager sensorManager;
    Sensor tempSensor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_temp, container, false);

        tempTV = (TextView) v.findViewById(R.id.TempValue);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        sensorManager.registerListener(TempFragment.this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {

            tempTV.setText("" + String.format("%.4f", +sensorEvent.values[0]) + "\t\t\t" + "[°C]");

        } else {

            tempTV.setText("Temperature Sensor not supported on this device");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
