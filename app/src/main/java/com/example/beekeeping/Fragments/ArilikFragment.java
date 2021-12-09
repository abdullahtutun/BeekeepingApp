package com.example.beekeeping.Fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.beekeeping.Database.Database;
import com.example.beekeeping.Database.GorevlerDAO;
import com.example.beekeeping.Database.KovanlarDAO;
import com.example.beekeeping.Database.NotlarDAO;
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
    @BindView(R.id.tvGorevToplam) TextView tvGorevToplam;
    @BindView(R.id.tvNotToplam) TextView tvNotToplam;
    @BindView(R.id.btnMGorevEkle) Button btnMGorevEkle;
    @BindView(R.id.btnMNotEkle) Button btnMNotEkle;
    View view;
    Database db;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_arilik, container, false);

        ButterKnife.bind(this,view);

        db = new Database(getContext());

        setInfo();

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

    private void setInfo(){

        setInfoKovanCard();

        setInfoGorevCard();

        setInfoNotCard();
    }
    private void setInfoKovanCard(){

        String countKovan = String.valueOf(new KovanlarDAO().getCountKovan(db));
        String countActiveKovan = String.valueOf(new KovanlarDAO().getCountActiveKovan(db));
        String countPassiveKovan = String.valueOf(new KovanlarDAO().getCountPassiveKovan(db));

        tvKovanSayi.setText(countKovan);
        tvAktifKovan.setText(countActiveKovan);
        tvPasifKovan.setText(countPassiveKovan);
    }

    private void setInfoGorevCard(){

        String countGorev = String.valueOf(new GorevlerDAO().getCountGorev(db));

        tvGorevToplam.setText(countGorev);
    }

    private void setInfoNotCard(){

        String countGorev = String.valueOf(new NotlarDAO().getCountNot(db));

        tvNotToplam.setText(countGorev);
    }

}