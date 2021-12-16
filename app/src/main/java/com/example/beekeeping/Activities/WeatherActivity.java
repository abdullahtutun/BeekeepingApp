package com.example.beekeeping.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beekeeping.Adapters.DailyWeatherAdapter;
import com.example.beekeeping.Models.Weather;
import com.example.beekeeping.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity {

    @BindView(R.id.etCity)
    EditText etCity;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.iconWeather)
    ImageView iconWeather;
    @BindView(R.id.tvTemp)
    TextView tvTemp;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.lvDailyWeather)
    ListView lvDailyWeather;

    private static final String API_KEY = "0d733006947239eb215b69bc094bdb45";
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ButterKnife.bind(this);

        clickedBtnSearch();

    }

    private void clickedBtnSearch(){

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String city = etCity.getText().toString();

                if(city.isEmpty()){
                    Snackbar.make(v,"Bir şehir girin!",Snackbar.LENGTH_LONG).show();
                }else{
                    loadWeatherByCityName(city);
                }

            }
        });
    }

    private void loadWeatherByCityName(String city) {

        Ion.with(this)
                .load("http://api.openweathermap.org/data/2.5/weather?q="+city+"&&units=metric&appid="+API_KEY)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        if(e != null){
                            e.printStackTrace();
                            Snackbar.make(view,"Server error!",Snackbar.LENGTH_LONG).show();
                        }
                        else{

                            JsonObject main = result.get("main").getAsJsonObject();
                            double temp = main.get("temp").getAsDouble();
                            tvTemp.setText(temp+"°C");

                            JsonObject sys = result.get("sys").getAsJsonObject();
                            String country = sys.get("country").getAsString();
                            tvCity.setText(city+", "+country);

                            JsonArray weather = result.get("weather").getAsJsonArray();
                            String icon = weather.get(0).getAsJsonObject().get("icon").getAsString();
                            loadIcon(icon);

                            JsonObject coord = result.get("coord").getAsJsonObject();
                            double lon = coord.get("lon").getAsDouble();
                            double lat = coord.get("lat").getAsDouble();
                            loadDailyForecast(lon, lat);


                        }
                    }
                });
    }

    private void loadDailyForecast(double lon, double lat) {

        String apiUrl = "https://api.openweathermap.org/data/2.5/onecall?lat="+lat+"&lon="+lon+"&exclude=hourly,minutely,current&units=metric&appid=" + API_KEY;
        Ion.with(this)
                .load(apiUrl)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        if (e != null) {

                            e.printStackTrace();
                            Toast.makeText(WeatherActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                        } else {

                            List<Weather> weatherList = new ArrayList<>();
                            String timeZone = result.get("timezone").getAsString();
                            JsonArray daily = result.get("daily").getAsJsonArray();

                            for(int i=1;i<daily.size();i++) {

                                Long date = daily.get(i).getAsJsonObject().get("dt").getAsLong();
                                Double temp = daily.get(i).getAsJsonObject().get("temp").getAsJsonObject().get("day").getAsDouble();
                                String icon = daily.get(i).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("icon").getAsString();
                                weatherList.add(new Weather(date, timeZone, temp, icon));
                            }

                            // attach adapter to listview
                            DailyWeatherAdapter dailyWeatherAdapter = new DailyWeatherAdapter(WeatherActivity.this, weatherList);
                            lvDailyWeather.setAdapter(dailyWeatherAdapter);
                        }
                    }
                });
    }

    private void loadIcon(String icon) {

        Ion.with(this)
                .load("http://openweathermap.org/img/w/"+icon+".png")
                .intoImageView(iconWeather);

    }

}