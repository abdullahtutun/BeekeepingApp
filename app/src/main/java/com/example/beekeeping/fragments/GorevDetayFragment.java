package com.example.beekeeping.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class GorevDetayFragment extends Fragment {


    @BindView(R.id.tvGorevEkleETarihDetay) TextView tvGorevEkleETarihDetay;
    @BindView(R.id.tvGorevEkleTTarihDetay) TextView tvGorevEkleTTarihDetay;
    @BindView(R.id.etGorevBaslikDetay) EditText etGorevBaslikDetay;
    @BindView(R.id.etGorevDetay) EditText etGorevDetay;
    @BindView(R.id.btnUpdateGorev) Button btnUpdateGorev;
    @BindView(R.id.btnDeleteGorev) Button btnDeleteGorev;
    View view;
    private Database db;
    private DatePickerDialog.OnDateSetListener dateSetListenerEkleme;
    private DatePickerDialog.OnDateSetListener dateSetListenerTamamlama;
    Calendar cal = Calendar.getInstance();
    int gorevId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_gorev_detay, container, false);

        ButterKnife.bind(this,view);

        db = new Database(getContext());

        gorevId = GorevDetayFragmentArgs.fromBundle(getArguments()).getGorev().getId();

        setGorevEklemeTarih();

        setGorevTamamlamaTarih();

        setData();

        clickedUpdateButton();

        clickedDeleteButton();

        return view;
    }

    private void setGorevEklemeTarih(){

        String currentDateEkleme = DateFormat.getDateInstance(DateFormat.SHORT).format(cal.getTime());
        tvGorevEkleETarihDetay.setText(currentDateEkleme);

        tvGorevEkleETarihDetay.setOnClickListener(new View.OnClickListener() {
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

                String date = dayOfMonth + "/" + month + "/" + year;
                tvGorevEkleETarihDetay.setText(date);
            }
        };
    }

    private void setGorevTamamlamaTarih(){

        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(cal.getTime());
        tvGorevEkleTTarihDetay.setText(currentDate);

        tvGorevEkleTTarihDetay.setOnClickListener(new View.OnClickListener() {
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

                String date = dayOfMonth + "/" + month + "/" + year;
                tvGorevEkleTTarihDetay.setText(date);
            }
        };
    }

    private void setData(){

        if(getArguments() != null){

            String eklemeTarihi = GorevDetayFragmentArgs.fromBundle(getArguments()).getGorev().getEklenme_tarih();
            String tamamlanmaTarihi = GorevDetayFragmentArgs.fromBundle(getArguments()).getGorev().getTamamlanma_tarih();
            String gorevBaslik = GorevDetayFragmentArgs.fromBundle(getArguments()).getGorev().getGorev_baslik();
            String gorev = GorevDetayFragmentArgs.fromBundle(getArguments()).getGorev().getGorev();

            tvGorevEkleETarihDetay.setText(eklemeTarihi);
            tvGorevEkleTTarihDetay.setText(tamamlanmaTarihi);
            etGorevBaslikDetay.setText(gorevBaslik);
            etGorevDetay.setText(gorev);


        }
    }

    private void clickedUpdateButton(){

        btnUpdateGorev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eTarih = tvGorevEkleETarihDetay.getText().toString().trim();
                String tTarih = tvGorevEkleTTarihDetay.getText().toString().trim();
                String gorevBaslik= etGorevBaslikDetay.getText().toString().trim();
                String gorev = etGorevDetay.getText().toString().trim();

                if (TextUtils.isEmpty(gorevBaslik)){
                    Snackbar.make(v,"Görev başlığı giriniz!",Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (TextUtils.isEmpty(gorev)){
                    Snackbar.make(v,"Görevi giriniz!",Snackbar.LENGTH_LONG).show();
                    return;
                }

                new GorevlerDAO().updateGorev(db,gorevId,eTarih,tTarih,gorevBaslik,gorev,v);
            }
        });
    }

    private void clickedDeleteButton(){

        btnDeleteGorev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v,"Silinsin mi?",Snackbar.LENGTH_LONG).setAction("Evet", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new GorevlerDAO().deleteGorev(db,gorevId,v);

                        Navigation.findNavController(view).popBackStack();

                    }
                }).show();

            }
        });
    }
}