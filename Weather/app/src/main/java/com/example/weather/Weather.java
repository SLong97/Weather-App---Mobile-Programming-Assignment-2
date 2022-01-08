package com.example.weather;

public class Weather {

    String weather_state_name;
    String applicable_date;
    float min_temp;
    float max_temp;
    float the_temp;

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public void setWeather_state_name(String weather_state_name) {
        this.weather_state_name = weather_state_name;
    }

    public String getApplicable_date() {return applicable_date;}
    public void setApplicable_date(String created) {this.applicable_date = applicable_date;}
    public float getMin_temp() {return min_temp;}
    public void setMin_temp(float min_temp) {this.min_temp = min_temp; }
    public float getMax_temp() {return max_temp;}
    public void setMax_temp(float max_temp) {this.max_temp = max_temp;}
    public float getThe_temp() {return the_temp;}
    public void setThe_temp(float the_temp) {this.the_temp = the_temp;}
}
