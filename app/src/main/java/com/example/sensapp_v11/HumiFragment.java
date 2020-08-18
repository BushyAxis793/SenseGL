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

public class HumiFragment extends Fragment implements SensorEventListener {

    TextView humiTV;
    SensorManager sensorManager;
    Sensor humiditySensor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_humi, container, false);

        humiTV = (TextView) v.findViewById(R.id.HumiValue);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        sensorManager.registerListener(HumiFragment.this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {

            humiTV.setText("" + String.format("%.4f", +sensorEvent.values[0]) + "\t\t\t" + "[%]");

        } else {

            humiTV.setText("Humidity Sensor not supported on this device");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
