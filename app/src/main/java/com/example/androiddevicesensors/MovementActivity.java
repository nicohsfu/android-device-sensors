package com.example.androiddevicesensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MovementActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener {

    Button movementStatusButton;

    TextView movementStatusTextView;

    private SensorManager sensorManager;

    private Sensor accelerometer;

    private boolean isMoving = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement);

        movementStatusButton = findViewById(R.id.movementStatusButton);
        movementStatusButton.setOnClickListener(this);

        movementStatusTextView = findViewById(R.id.movementStatusTextView);
        // initially set the text view to invisible since the status has not been determined
        movementStatusTextView.setVisibility(View.INVISIBLE);

        // Get the sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Get the accelerometer sensor
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Register the sensor listener
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onClick(View v) {
//        Toast.makeText(this, "moving? button clicked", Toast.LENGTH_SHORT).show();
//        movementStatusTextView.setText("Stationary"); // placeholder

        movementStatusTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // Calculate the acceleration to check if device is moving
        double acceleration = Math.sqrt(x * x + y * y + z * z);

        if (acceleration > 0.1) {
            // The device is moving
            if (!isMoving) {
                isMoving = true;
                movementStatusTextView.setText("The device is moving. \n\nPlease do note that the Earth's gravity technically counts as acceleration, so this could be taken into account");
            }
        } else {
            // The device is stationary
            if (isMoving) {
                isMoving = false;
                movementStatusTextView.setText("The device is stationary");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}