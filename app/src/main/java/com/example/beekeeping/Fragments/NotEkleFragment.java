package com.example.beekeeping.Fragments;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.DatePickerDialog;
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
import com.example.beekeeping.Database.NotlarDAO;
import com.example.beekeeping.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotEkleFragment extends Fragment {

    @BindView(R.id.tvNotTarih) TextView tvNotTarih;
    @BindView(R.id.etNotBaslik) EditText etNotBaslik;
    @BindView(R.id.etNot) EditText etNot;
    @BindView(R.id.btnNotEkle) Button btnNotEkle;
    private Database db;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    Calendar cal = Calendar.getInstance();
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_not_ekle, container, false);

        ButterKnife.bind(this,view);

        db = new Database(getContext());

        setNotTarih();

        clickedButton();

        return view;
    }

    private void setNotTarih(){

        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(cal.getTime());
        tvNotTarih.setText(currentDate);

        tvNotTarih.setOnClickListener(new View.OnClickListener() {
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
                tvNotTarih.setText(date);
            }
        };
    }

    private void clickedButton(){
        btnNotEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String notBaslik = etNotBaslik.getText().toString().trim();
                String not = etNot.getText().toString().trim();
                String tarih= tvNotTarih.getText().toString().trim();

                if (TextUtils.isEmpty(notBaslik)){
                    Snackbar.make(v,"Not başlığını giriniz",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(not)){
                    Snackbar.make(v,"Notunuzu giriniz",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                new NotlarDAO().addNot(db,tarih,notBaslik,not,v);
            }
        });
    }
}