package com.example.androiddevicesensors;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class SensorInfoActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager mySensorManager;

    Sensor sensor;

    TextView sensorValsTextView;
    TextView specificSensorNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_info);

        sensorValsTextView = findViewById(R.id.sensorValsTextView);

        int i = getIntent().getIntExtra("POSITION", -1);
//        Toast.makeText(this, "index position: " + i, Toast.LENGTH_SHORT).show();

        specificSensorNameTextView = findViewById(R.id.specificSensorNameTextView);
        String sensorName = getIntent().getStringExtra("SENSORNAME");
//        Toast.makeText(this, "specific sensor name: " + sensorName, Toast.LENGTH_SHORT).show();
        specificSensorNameTextView.setText(sensorName);

        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> mList = mySensorManager.getSensorList(Sensor.TYPE_ALL);

        sensor = mList.get(i);
    }
    @Override
    protected void onResume(){
        super.onResume();
        mySensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] vals = event.values;
        String s = "";
        for(int i = 0; i < vals.length; i++){
            s += ("" + vals[i] + "\n");
        }
        sensorValsTextView.setText(s);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}