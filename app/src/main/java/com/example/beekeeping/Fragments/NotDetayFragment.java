package com.example.beekeeping.Fragments;

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
import com.example.beekeeping.Database.NotlarDAO;
import com.example.beekeeping.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotDetayFragment extends Fragment {

    @BindView(R.id.tvNotTarihDetay) TextView tvNotTarihDetay;
    @BindView(R.id.etNotBaslikDetay) EditText etNotBaslikDetay;
    @BindView(R.id.etNotDetay) EditText etNotDetay;
    @BindView(R.id.btnUpdateNot) Button btnUpdateNot;
    @BindView(R.id.btnDeleteNot) Button btnDeleteNot;
    private Database db;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    Calendar cal = Calendar.getInstance();
    View view;
    int notId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_not_detay, container, false);

        ButterKnife.bind(this,view);

        db = new Database(getContext());

        notId = NotDetayFragmentArgs.fromBundle(getArguments()).getNot().getId();

        setNotTarih();

        setData();

        clickedUpdateButton();

        clickedDeleteButton();

        return view;
    }

    private void setNotTarih(){

        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(cal.getTime());
        tvNotTarihDetay.setText(currentDate);

        tvNotTarihDetay.setOnClickListener(new View.OnClickListener() {
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

                String date = dayOfMonth + "/" + month + "/" + year;
                tvNotTarihDetay.setText(date);
            }
        };
    }

    private void setData(){

        if(getArguments() != null){

            String tarih = NotDetayFragmentArgs.fromBundle(getArguments()).getNot().getNot_tarih();
            String baslik = NotDetayFragmentArgs.fromBundle(getArguments()).getNot().getNot_baslik();
            String not = NotDetayFragmentArgs.fromBundle(getArguments()).getNot().getNot_icerik();

            tvNotTarihDetay.setText(tarih);
            etNotBaslikDetay.setText(baslik);
            etNotDetay.setText(not);
        }
    }

    private void clickedUpdateButton() {

        btnUpdateNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String notBaslik = etNotBaslikDetay.getText().toString().trim();
                String not = etNotDetay.getText().toString().trim();
                String tarih= tvNotTarihDetay.getText().toString().trim();

                if (TextUtils.isEmpty(notBaslik)){
                    Snackbar.make(v,"Not başlığını giriniz!",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(not)){
                    Snackbar.make(v,"Notunuzu giriniz!",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                new NotlarDAO().updateNot(db,notId,tarih,notBaslik,not,v);
            }
        });
    }

    private void clickedDeleteButton() {

        btnDeleteNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v,"Silinsin mi?",Snackbar.LENGTH_LONG).setAction("Evet", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new NotlarDAO().deleteNot(db,notId,v);

                        Navigation.findNavController(view).popBackStack();

                    }
                }).show();


            }
        });
    }
}