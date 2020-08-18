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

import androidx.fragment.app.Fragment;

import java.util.EventListener;

public class GyroFragment extends Fragment implements SensorEventListener {

    TextView xGyroTV, yGyroTV, zGyroTV;
    SensorManager sensorManager;
    Sensor gyroscope;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gyro, container, false);

        xGyroTV = (TextView) v.findViewById(R.id.GyroValueX);
        yGyroTV = (TextView) v.findViewById(R.id.GyroValueY);
        zGyroTV = (TextView) v.findViewById(R.id.GyroValueZ);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        sensorManager.registerListener(GyroFragment.this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {

            xGyroTV.setText("X: " + String.format("%.4f", +sensorEvent.values[0]) + "\t\t\t" + "[rad/s]");
            yGyroTV.setText("Y: " + String.format("%.4f", +sensorEvent.values[1]) + "\t\t\t" + "[rad/s]");
            zGyroTV.setText("Z: " + String.format("%.4f", +sensorEvent.values[2]) + "\t\t\t" + "[rad/s]");

        } else {

            xGyroTV.setText("Gyroscope not supported on this device");
            yGyroTV.setText("Gyroscope not supported on this device");
            zGyroTV.setText("Gyroscope not supported on this device");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
