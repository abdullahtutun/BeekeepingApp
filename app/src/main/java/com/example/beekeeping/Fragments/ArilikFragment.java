package com.example.beekeeping.Fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.beekeeping.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArilikFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.cardKovanlar) CardView cardKovanlar;
    @BindView(R.id.cardGorevler) CardView cardGorevler;
    @BindView(R.id.cardNotlar) CardView cardNotlar;
    @BindView(R.id.tvKovanSayi) TextView tvKovanSayi;
    @BindView(R.id.tvAktifKovan) TextView tvAktifKovan;
    @BindView(R.id.tvPasifKovan) TextView tvPasifKovan;
    @BindView(R.id.tvBGorevSayisi) TextView tvBGorevSayisi;
    @BindView(R.id.tvSGGorevSayisi) TextView tvSGGorevSayisi;
    @BindView(R.id.tvSNotBasligi) TextView tvSNotBasligi;
    @BindView(R.id.tvNotEklenmeTarihi) TextView tvNotEklenmeTarihi;
    @BindView(R.id.btnMGorevEkle) Button btnMGorevEkle;
    @BindView(R.id.btnMNotEkle) Button btnMNotEkle;
    View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_arilik, container, false);

        ButterKnife.bind(this,view);

        setListeners();

        return view;
    }

    public void setListeners(){
        cardKovanlar.setOnClickListener(this);
        cardGorevler.setOnClickListener(this);
        cardNotlar.setOnClickListener(this);
        btnMGorevEkle.setOnClickListener(this);
        btnMNotEkle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardKovanlar:
                goToKovanlar(v);
                break;
            case R.id.cardGorevler:
                goToGorevler(v);
                break;
            case R.id.cardNotlar:
                goToNotlar(v);
                break;
            case R.id.btnMGorevEkle:
                goToGorevEkle(v);
                break;
            case R.id.btnMNotEkle:
                goToNotEkle(v);
                break;
        }
    }

    private void goToKovanlar(View view){
        NavDirections action = ArilikFragmentDirections.actionArilikToKovanlar();
        Navigation.findNavController(view).navigate(action);
    }

    private void goToGorevler(View view){
        NavDirections action = ArilikFragmentDirections.actionArilikToGorevler();
        Navigation.findNavController(view).navigate(action);
    }

    private void goToNotlar(View view){
        NavDirections action = ArilikFragmentDirections.actionArilikToNotlar();
        Navigation.findNavController(view).navigate(action);
    }

    private void goToGorevEkle(View view){
        NavDirections action = ArilikFragmentDirections.actionArilikToGorevEkleFragment();
        Navigation.findNavController(view).navigate(action);
    }

    private void goToNotEkle(View view){
        NavDirections action = ArilikFragmentDirections.actionArilikToNotEkleFragment();
        Navigation.findNavController(view).navigate(action);
    }
}