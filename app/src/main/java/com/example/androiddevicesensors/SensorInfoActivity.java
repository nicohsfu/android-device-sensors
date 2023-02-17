package com.example.androiddevicesensors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class SensorInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_info);

        int i = getIntent().getIntExtra("POSITION", -1);

        Toast.makeText(this, "" + i, Toast.LENGTH_SHORT).show();
    }
}