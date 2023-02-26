package com.example.androiddevicesensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    private RecyclerView myRecycler;

    SensorManager mySensorManager;

    TextView sensorsTextView;

    private CustomAdapter customAdapter;

    private LinearLayoutManager mLayoutManager;

    Button statusButton;

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

//        Toast.makeText(this, "Array Size: " + mArrayList.size(), Toast.LENGTH_SHORT).show();

        customAdapter = new com.example.androiddevicesensors.CustomAdapter(mArrayList);
        myRecycler.setAdapter(customAdapter);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(mLayoutManager);

        statusButton = findViewById(R.id.statusButton);
        statusButton.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, MovementActivity.class);
        startActivity(i);
    }
}