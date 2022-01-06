package com.example.beekeeping.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.beekeeping.Database.Database;
import com.example.beekeeping.Database.KovanlarDAO;
import com.example.beekeeping.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KovanEkleFragment extends Fragment {

    @BindView(R.id.tvKovanEkleTarih) TextView tvKovanEkleTarih;
    @BindView(R.id.etKovanNo) EditText etKovanNo;
    @BindView(R.id.etKovanNot) EditText etKovanNot;
    @BindView(R.id.spinnerKovanKaynak) Spinner spinnerKovanKaynak;
    @BindView(R.id.spinnerDurum) Spinner spinnerDurum;
    @BindView(R.id.btnKovanEkle) Button btnKovanEkle;
    View view;
    Calendar cal = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private String kovanKaynak,kovanDurum;
    Database db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_kovan_ekle, container, false);

        db = new Database(getContext());

        ButterKnife.bind(this,view);

        setKovanTarih();

        setKovanKaynak();

        setKovanDurum();

        clickedButton();

        return view;
    }

    private void setKovanTarih(){

        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(cal.getTime());
        tvKovanEkleTarih.setText(currentDate);

        tvKovanEkleTarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog,dateSetListener,year,month,day);

                dialog.show();

            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date = dayOfMonth + "." + month + "." + year;
                tvKovanEkleTarih.setText(date);
            }
        };

    }

    private void setKovanKaynak(){

        ArrayAdapter<CharSequence> adapterKovanKaynak = ArrayAdapter.createFromResource(getContext(), R.array.kovan_kaynak, android.R.layout.simple_spinner_item);
        adapterKovanKaynak.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKovanKaynak.setAdapter(adapterKovanKaynak);

        getKovanKaynak();
    }

    private void setKovanDurum(){

        ArrayAdapter<CharSequence> adapterDurum = ArrayAdapter.createFromResource(getContext(), R.array.durum, android.R.layout.simple_spinner_item);
        adapterDurum.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDurum.setAdapter(adapterDurum);

        getKovanDurum();
    }

    private void getKovanKaynak(){

        spinnerKovanKaynak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kovanKaynak =parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getKovanDurum(){

        spinnerDurum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kovanDurum = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void clickedButton(){

        btnKovanEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tarih = tvKovanEkleTarih.getText().toString().trim();
                String kovanNo = etKovanNo.getText().toString().trim();
                String kovanNot = etKovanNot.getText().toString().trim();

                if (TextUtils.isEmpty(kovanNo)){

                    Snackbar.make(v,"Kovan numarasını giriniz",Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if(TextUtils.isEmpty(kovanNot)){
                    kovanNot = null;
                }

                new KovanlarDAO().addKovan(db,tarih,kovanNo,kovanKaynak,kovanNot,kovanDurum,v);

            }
        });
    }

}