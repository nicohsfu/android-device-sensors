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

        // retrieving the data we passed along with the intent from the Custom Adapter class
        int i = getIntent().getIntExtra("POSITION", -1);
        // Toast.makeText(this, "index position: " + i, Toast.LENGTH_SHORT).show();

        specificSensorNameTextView = findViewById(R.id.specificSensorNameTextView);

        // retrieving the data we passed along with the intent from the Custom Adapter class
        String sensorName = getIntent().getStringExtra("SENSORNAME");
        // Toast.makeText(this, "specific sensor name: " + sensorName, Toast.LENGTH_SHORT).show();

        specificSensorNameTextView.setText(sensorName);

        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> mList = mySensorManager.getSensorList(Sensor.TYPE_ALL);

        sensor = mList.get(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // the respective values for a given sensor is stred in the vals variable, and these values are then appended to the initially-empty string variable "s"
        float[] vals = event.values;
        String s = "";
        for (int i = 0; i < vals.length; i++) {
            s += ("" + vals[i] + "\n");
        }
        // we then update the textview to display these sensor values
        sensorValsTextView.setText(s);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}