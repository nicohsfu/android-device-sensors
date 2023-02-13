package com.example.androiddevicesensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private RecyclerView myRecycler;

    SensorManager mySensorManager;

    TextView sensorsTextView;

    private CustomAdapter customAdapter;

    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRecycler = (RecyclerView) findViewById(R.id.recyclerView);

        ArrayList<String> mArrayList = new ArrayList<String>();
        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> mList = mySensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor s : mList
        ) {
            mArrayList.add(s.getName());
        }

        Toast.makeText(this, " " + mArrayList.size(), Toast.LENGTH_SHORT).show();

        customAdapter = new com.example.androiddevicesensors.CustomAdapter(mArrayList);
        myRecycler.setAdapter(customAdapter);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(mLayoutManager);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        mySensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}