package com.example.androiddevicesensors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MovementActivity extends AppCompatActivity implements View.OnClickListener {

    Button movementStatusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement);

        movementStatusButton = findViewById(R.id.movementStatusButton);
        movementStatusButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "moving? button clicked", Toast.LENGTH_SHORT).show();
    }
}