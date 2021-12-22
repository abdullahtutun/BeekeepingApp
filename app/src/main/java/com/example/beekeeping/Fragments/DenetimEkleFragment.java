package com.example.beekeeping.Fragments;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.beekeeping.Database.Database;
import com.example.beekeeping.Database.DenetimlerDAO;
import com.example.beekeeping.R;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DenetimEkleFragment extends Fragment {

    @BindView(R.id.etKovan) EditText etKovan;
    @BindView(R.id.denetimEkleTarih) TextView denetimEkleTarih;
    @BindView(R.id.etCerceveSayisi) EditText etCerceveSayisi;
    @BindView(R.id.etAriliCerceve) EditText etAriliCerceve;
    @BindView(R.id.etBalliCerceve) EditText etBalliCerceve;
    @BindView(R.id.etKabarikCerceve) EditText etKabarikCerceve;
    @BindView(R.id.etHamCerceve) EditText etHamCerceve;
    @BindView(R.id.etGunlukCerceve) EditText etGunlukCerceve;
    @BindView(R.id.etLarvaCerceve) EditText etLarvaCerceve;
    @BindView(R.id.etKapaliCerceve) EditText etKapaliCerceve;
    @BindView(R.id.etGenelGozlem) EditText etGenelGozlem;
    @BindView(R.id.radioGrupAnaAri) RadioGroup radioGrupAnaAri;
    @BindView(R.id.radioGrupHastalik) RadioGroup radioGrupHastalik;
    @BindView(R.id.radioGrupIlaclama) RadioGroup radioGrupIlaclama;
    @BindView(R.id.rbGoruldu) RadioButton rbGoruldu;
    @BindView(R.id.rbGorulmedi) RadioButton rbGorulmedi;
    @BindView(R.id.rbVar) RadioButton rbVar;
    @BindView(R.id.rbYok) RadioButton rbYok;
    @BindView(R.id.rbYapildi) RadioButton rbYapildi;
    @BindView(R.id.rbYapilmadi) RadioButton rbYapilmadi;
    @BindView(R.id.checkBoxKek) CheckBox checkBoxKek;
    @BindView(R.id.checkBoxSurup) CheckBox checkBoxSurup;
    @BindView(R.id.checkBoxDiger) CheckBox checkBoxDiger;
    @BindView(R.id.btnDenetimEkle) Button btnDenetimEkle;
    private String anaAriDurum,hastalikDurum,ilaclamaDurum,kek,surup,diger;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    Calendar cal = Calendar.getInstance();
    private Database db;
    View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_denetim_ekle, container, false);

        ButterKnife.bind(this,view);

        db = new Database(getContext());

        String kovanNo = DenetimEkleFragmentArgs.fromBundle(getArguments()).getKovanNo();

        etKovan.setText(kovanNo);

        setDenetimTarih();

        getAnaAriDurum();

        getHastalikDurum();

        getIlaclamaDurum();

        clickedButton();

        return view;
    }

    private void setDenetimTarih(){

        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(cal.getTime());
        denetimEkleTarih.setText(currentDate);

        denetimEkleTarih.setOnClickListener(new View.OnClickListener() {
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
                denetimEkleTarih.setText(date);
            }
        };

    }

    private void getAnaAriDurum(){

        radioGrupAnaAri.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.rbGoruldu){
                    anaAriDurum = "goruldu";
                }
                if(checkedId == R.id.rbGorulmedi){
                    anaAriDurum = "gorulmedi";
                }
                if(checkedId != R.id.rbGoruldu && checkedId != R.id.rbGorulmedi){
                    anaAriDurum = "null";
                }

            }
        });
    }

    private void getHastalikDurum(){

        radioGrupHastalik.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbVar:
                        hastalikDurum = "var";
                        break;
                    case R.id.rbYok:
                        hastalikDurum = "yok";
                        break;
                }
            }
        });
    }

    private void getIlaclamaDurum(){

        radioGrupIlaclama.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbYapildi:
                        ilaclamaDurum = "yapildi";
                        break;
                    case R.id.rbYapilmadi:
                        ilaclamaDurum = "yapilmadi";
                        break;
                }
            }
        });
    }

    private void getBesleme(){

        if(checkBoxKek.isChecked()){
            kek = "true";
        }
        if(!checkBoxKek.isChecked()){
            kek = "false";
        }
        if(checkBoxSurup.isChecked()){
            surup = "true";
        }
        if(!checkBoxSurup.isChecked()){
            surup = "false";
        }
        if(checkBoxDiger.isChecked()){
            diger = "true";
        }
        if(!checkBoxDiger.isChecked()){
            diger = "false";
        }
    }

    private void clickedButton(){

        btnDenetimEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String denetimTarih = denetimEkleTarih.getText().toString().trim();
                String kovanNo = etKovan.getText().toString().trim();
                String cerceveSayi = etCerceveSayisi.getText().toString().trim();
                String ariliCerceveSayi = etAriliCerceve.getText().toString().trim();
                String balliCerceveSayi = etBalliCerceve.getText().toString().trim();
                String kabarikCerceveSayi = etKabarikCerceve.getText().toString().trim();
                String hamCerceveSayi = etHamCerceve.getText().toString().trim();
                String gunlukCerceveSayi = etGunlukCerceve.getText().toString().trim();
                String larvaCerceveSayi = etLarvaCerceve.getText().toString().trim();
                String kapaliCerceveSayi = etKapaliCerceve.getText().toString().trim();
                String genelGozlem = etGenelGozlem.getText().toString().trim();

                getBesleme();

                new DenetimlerDAO().addDenetim(db,kovanNo,denetimTarih,cerceveSayi,
                        ariliCerceveSayi,balliCerceveSayi,kabarikCerceveSayi,
                        hamCerceveSayi,anaAriDurum,gunlukCerceveSayi,larvaCerceveSayi,
                        kapaliCerceveSayi,kek,surup,diger,hastalikDurum,ilaclamaDurum,genelGozlem,v);

            }
        });
    }
}