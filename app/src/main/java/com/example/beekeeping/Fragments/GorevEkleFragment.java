package com.example.beekeeping.Fragments;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.beekeeping.Database.Database;
import com.example.beekeeping.Database.GorevlerDAO;
import com.example.beekeeping.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GorevEkleFragment extends Fragment {

    @BindView(R.id.tvGorevEkleETarih) TextView tvGorevEkleETarih;
    @BindView(R.id.tvGorevEkleTTarih) TextView tvGorevEkleTTarih;
    @BindView(R.id.etGorevBaslik) EditText etGorevBaslik;
    @BindView(R.id.etGorev) EditText etGorev;
    @BindView(R.id.btnGorevEkle) Button btnGorevEkle;
    View view;
    private Database db;
    private DatePickerDialog.OnDateSetListener dateSetListenerEkleme;
    private DatePickerDialog.OnDateSetListener dateSetListenerTamamlama;
    private String gorevBaslik,gorev;
    Calendar cal = Calendar.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gorev_ekle, container, false);

        ButterKnife.bind(this,view);

        db = new Database(getContext());

        clickedButton();

        setGorevEklemeTarih();

        setGorevTamamlamaTarih();

        clickedButton();

        return view;
    }

    private void setGorevEklemeTarih(){

        String currentDateEkleme = DateFormat.getDateInstance(DateFormat.SHORT).format(cal.getTime());
        tvGorevEkleETarih.setText(currentDateEkleme);

        tvGorevEkleETarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog,dateSetListenerEkleme,year,month,day);

                dialog.show();

            }
        });

        dateSetListenerEkleme = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date = dayOfMonth + "." + month + "." + year;
                tvGorevEkleETarih.setText(date);
            }
        };
    }

    private void setGorevTamamlamaTarih(){

        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(cal.getTime());
        tvGorevEkleTTarih.setText(currentDate);

        tvGorevEkleTTarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog,dateSetListenerTamamlama,year,month,day);

                dialog.show();

            }
        });

        dateSetListenerTamamlama = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date = dayOfMonth + "." + month + "." + year;
                tvGorevEkleTTarih.setText(date);
            }
        };
    }

    private void clickedButton(){
        btnGorevEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eTarih = tvGorevEkleETarih.getText().toString().trim();
                String tTarih = tvGorevEkleTTarih.getText().toString().trim();
                gorevBaslik= etGorevBaslik.getText().toString().trim();
                gorev = etGorev.getText().toString().trim();

                if (TextUtils.isEmpty(gorevBaslik)){
                    Snackbar.make(v,"Görev başlığı giriniz",Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (TextUtils.isEmpty(gorev)){
                    Snackbar.make(v,"Görevi giriniz",Snackbar.LENGTH_LONG).show();
                    return;
                }

                new GorevlerDAO().addGorev(db,eTarih,tTarih,gorevBaslik,gorev,v);
            }
        });
    }
}