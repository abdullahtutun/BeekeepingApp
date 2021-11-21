package com.example.beekeeping.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.beekeeping.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GorevDetayFragment extends Fragment {


    @BindView(R.id.tvGorevEkleETarihDetay) TextView tvGorevEkleETarihDetay;
    @BindView(R.id.tvGorevEkleTTarihDetay) TextView tvGorevEkleTTarihDetay;
    @BindView(R.id.etGorevBaslikDetay) EditText etGorevBaslikDetay;
    @BindView(R.id.etGorevDetay) EditText etGorevDetay;
    @BindView(R.id.btnGorevDetay) Button btnGorevDetay;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_gorev_detay, container, false);

        ButterKnife.bind(this,view);

        clickedButton();

        return view;
    }

    private void clickedButton(){

        btnGorevDetay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}