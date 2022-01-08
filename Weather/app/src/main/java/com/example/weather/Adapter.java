package com.example.weather;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    ArrayList location, woeid;

    Adapter(Context context, ArrayList location, ArrayList woeid){

        this.context = context;
        this.location = location;
        this.woeid = woeid;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.locationText.setText(String.valueOf(location.get(position)));
        holder.woeidText.setText(String.valueOf(woeid.get(position)));
        holder.mainLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(woeid.get(position)));
                intent.putExtra("location", String.valueOf(location.get(position)));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return location.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView locationText, woeidText;
        LinearLayout mainLay;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            locationText = itemView.findViewById(R.id.locationText);
            woeidText = itemView.findViewById(R.id.woeidText);
            mainLay = itemView.findViewById(R.id.mainLay);

        }
    }

}
