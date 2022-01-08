package com.example.weather;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class WeatherData {

    @SerializedName("consolidated_weather")

    private ArrayList<Weather> weathers = new ArrayList<>();

    public ArrayList<Weather> getWeathers()
    {
        return weathers;
    }

    public void setWeathers(ArrayList<Weather> weathers)
    {
        this.weathers = weathers;
    }
}