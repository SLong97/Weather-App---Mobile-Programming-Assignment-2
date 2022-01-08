package com.example.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<Weather> mList = new ArrayList<>();

    public MyRecyclerViewAdapter(ArrayList<Weather> mList){
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_data_row, parent, false);
        MyRecyclerViewAdapter.MyViewHolder myViewHolder = new MyRecyclerViewAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView date,weather,maximum,minimum,temp;
        public MyViewHolder(View view)
        {
            super(view);
            date= view.findViewById(R.id.tv1);
            weather= view.findViewById(R.id.tv2);
            maximum= view.findViewById(R.id.tv5);
            minimum= view.findViewById(R.id.tv4);
            temp= view.findViewById(R.id.tv3);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position) {
        String date = "Date: " + mList.get(position).getApplicable_date();
        holder.date.setText(date);
        String weather = "Weather: " + mList.get(position).getWeather_state_name();
        holder.weather.setText(weather);
        String temp = "Average Temp: " + (mList.get(position).getThe_temp());
        holder.temp.setText(temp);
        String maxi = "Max: " + (mList.get(position).getMax_temp());
        holder.maximum.setText(maxi);
        String mini = "Min: " + (mList.get(position).getMin_temp());
        holder.minimum.setText(mini);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    @Override
    public int getItemViewType(int position)
    {
        return position;
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }


}