package com.example.beekeeping.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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
import com.example.beekeeping.Database.DenetimlerDAO;
import com.example.beekeeping.Database.KovanlarDAO;
import com.example.beekeeping.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KovanDetayFragment extends Fragment {

    @BindView(R.id.tvKovanEkleTarihDetay) TextView tvKovanEkleTarihDetay;
    @BindView(R.id.kdDenetimSayisi) TextView kdDenetimSayisi;
    @BindView(R.id.etKovanNoDetay) EditText etKovanNoDetay;
    @BindView(R.id.etKovanNotDetay) EditText etKovanNotDetay;
    @BindView(R.id.spinnerKovanKaynakDetay) Spinner spinnerKovanKaynakDetay;
    @BindView(R.id.spinnerDurumDetay) Spinner spinnerDurumDetay;
    @BindView(R.id.btnUpdateKovanDetay) Button btnUpdateKovanDetay;
    @BindView(R.id.btnDeleteKovanDetay) Button btnDeleteKovanDetay;
    @BindView(R.id.btnGor) Button btnGor;
    View view;
    Calendar cal = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private String kovanKaynak,kovanDurum;
    Database db;
    int kovanId;
    String kovanNo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_kovan_detay, container, false);

        db = new Database(getContext());

        ButterKnife.bind(this,view);

        kovanId = KovanDetayFragmentArgs.fromBundle(getArguments()).getKovan().getId();

        setKovanTarih();

        setKovanKaynak();

        setKovanDurum();

        getKovanKaynak();

        getKovanDurum();

        setData();

        clickedUpdateButton();

        clickedDeleteButton(view);

        clickedGorButton();

        return view;
    }

    private void setKovanTarih(){

        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(cal.getTime());
        tvKovanEkleTarihDetay.setText(currentDate);

        tvKovanEkleTarihDetay.setOnClickListener(new View.OnClickListener() {
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
                tvKovanEkleTarihDetay.setText(date);
            }
        };

    }

    private void setKovanKaynak(){

        ArrayAdapter<CharSequence> adapterKovanKaynak = ArrayAdapter.createFromResource(getContext(), R.array.kovan_kaynak, android.R.layout.simple_spinner_item);
        adapterKovanKaynak.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKovanKaynakDetay.setAdapter(adapterKovanKaynak);
    }

    private void setKovanDurum(){

        ArrayAdapter<CharSequence> adapterDurum = ArrayAdapter.createFromResource(getContext(), R.array.durum, android.R.layout.simple_spinner_item);
        adapterDurum.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDurumDetay.setAdapter(adapterDurum);
    }

    private void getKovanKaynak(){

        spinnerKovanKaynakDetay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        spinnerDurumDetay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kovanDurum = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setData(){

        if(getArguments() != null){

            String tarih = KovanDetayFragmentArgs.fromBundle(getArguments()).getKovan().getKovanTarih();
            kovanNo = KovanDetayFragmentArgs.fromBundle(getArguments()).getKovan().getKovanNo();
            String kovanNot = KovanDetayFragmentArgs.fromBundle(getArguments()).getKovan().getKovanNot();

            tvKovanEkleTarihDetay.setText(tarih);
            etKovanNoDetay.setText(kovanNo);
            etKovanNotDetay.setText(kovanNot);
            setCountDenetim();

            ArrayAdapter adapterKaynak = (ArrayAdapter) spinnerKovanKaynakDetay.getAdapter();
            int spinnerPositionKaynak = adapterKaynak.getPosition(KovanDetayFragmentArgs.fromBundle(getArguments()).getKovan().getKovanKaynak());
            spinnerKovanKaynakDetay.setSelection(spinnerPositionKaynak);

            ArrayAdapter adapterDurum = (ArrayAdapter) spinnerDurumDetay.getAdapter();
            int spinnerPositionDurum = adapterDurum.getPosition(KovanDetayFragmentArgs.fromBundle(getArguments()).getKovan().getKovanDurum());
            spinnerDurumDetay.setSelection(spinnerPositionDurum);

        }

    }

    private void clickedUpdateButton(){

        btnUpdateKovanDetay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tarih = tvKovanEkleTarihDetay.getText().toString().trim();
                String kovanNo = etKovanNoDetay.getText().toString().trim();
                String kovanNot = etKovanNotDetay.getText().toString().trim();

                if (TextUtils.isEmpty(kovanNo)){

                    Snackbar.make(v,"Kovan numarasını giriniz",Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if(TextUtils.isEmpty(kovanNot)){
                    kovanNot = null;
                }


                new KovanlarDAO().updateKovan(db,kovanId,tarih,kovanNo,kovanKaynak,kovanNot,kovanDurum,v);

            }
        });
    }

    private void clickedDeleteButton(View view){

        btnDeleteKovanDetay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v,"Silinsin mi?",Snackbar.LENGTH_LONG).setAction("Evet", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new KovanlarDAO().deleteKovan(db,kovanId,v);

                        new DenetimlerDAO().deleteDenetimForKovan(db,kovanNo);

                        Navigation.findNavController(view).popBackStack();

                    }
                }).show();

            }

        });



    }

    private void setCountDenetim(){

        int count = new DenetimlerDAO().getCountDenetim(db,kovanNo);

        kdDenetimSayisi.setText(String.valueOf(count));
    }

    private void clickedGorButton(){

        btnGor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                KovanDetayFragmentDirections.ActionKovanDetayFragmentToDenetimler action = KovanDetayFragmentDirections.actionKovanDetayFragmentToDenetimler();
                action.setKovanNo(kovanNo);

                Navigation.findNavController(v).navigate(action);
            }
        });


    }


}