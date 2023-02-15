package com.example.androiddevicesensors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    public ArrayList<String> list;
    Context context;

    public CustomAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomAdapter.MyViewHolder holder, int position) {
        String results = (list.get(position));
        holder.sensorNameTextView.setText(results);
        holder.position = position;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public int position;

        public TextView sensorNameTextView;

        public LinearLayout myLayout;

        Context context;

        public MyViewHolder(View itemView) {
            super(itemView);
            myLayout = (LinearLayout) itemView;

            sensorNameTextView = (TextView) itemView.findViewById(R.id.sensorNameTextView);

            itemView.setOnClickListener(this);
            context = itemView.getContext();

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,
                    "You have clicked " + ((TextView) view.findViewById(R.id.sensorNameTextView)).getText().toString(),
                    Toast.LENGTH_SHORT).show();

            // next steps:
            // create intent - send to new Values activity
            // send value's / sensor's index - putExtras - position variable
//            Intent i = new Intent(context, SensorInfoActivity.class); // this is erroring out for now
//            i.putExtra("POSITION", position);
//            startActivity(i); // this is erroring out for now
        }
    }
}