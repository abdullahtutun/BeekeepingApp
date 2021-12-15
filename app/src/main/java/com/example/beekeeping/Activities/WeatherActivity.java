package com.example.beekeeping.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beekeeping.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

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

    private static final String API_KEY = "0d733006947239eb215b69bc094bdb45";

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
                .load("http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+API_KEY)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        Log.d("result", result.toString());
                    }
                });
    }
}