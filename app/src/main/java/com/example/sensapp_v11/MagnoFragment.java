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

public class MagnoFragment extends Fragment implements SensorEventListener {

    TextView xMagnoTV, yMagnoTV, zMagnoTV;
    SensorManager sensorManager;
    Sensor magnetometer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_magno, container, false);

        xMagnoTV = (TextView) v.findViewById(R.id.MagnoValueX);
        yMagnoTV = (TextView) v.findViewById(R.id.MagnoValueY);
        zMagnoTV = (TextView) v.findViewById(R.id.MagnoValueZ);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        sensorManager.registerListener(MagnoFragment.this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {

            xMagnoTV.setText("X: " + String.format("%.4f", +sensorEvent.values[0]) + "\t\t\t" + "[µT]");
            yMagnoTV.setText("Y: " + String.format("%.4f", +sensorEvent.values[1]) + "\t\t\t" + "[µT]");
            zMagnoTV.setText("Z: " + String.format("%.4f", +sensorEvent.values[2]) + "\t\t\t" + "[µT]");

        } else {

            xMagnoTV.setText("Magnetometer not supported on this device");
            yMagnoTV.setText("Magnetometer not supported on this device");
            zMagnoTV.setText("Magnetometer not supported on this device");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
