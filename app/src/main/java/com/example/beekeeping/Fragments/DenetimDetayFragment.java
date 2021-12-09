package com.example.beekeeping.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class DenetimDetayFragment extends Fragment {

    @BindView(R.id.etKovanDetay) EditText etKovanDetay;
    @BindView(R.id.denetimEkleTarihDetay) TextView denetimEkleTarihDetay;
    @BindView(R.id.etCerceveSayisiDetay) EditText etCerceveSayisiDetay;
    @BindView(R.id.etAriliCerceveDetay) EditText etAriliCerceveDetay;
    @BindView(R.id.etBalliCerceveDetay) EditText etBalliCerceveDetay;
    @BindView(R.id.etKabarikCerceveDetay) EditText etKabarikCerceveDetay;
    @BindView(R.id.etHamCerceveDetay) EditText etHamCerceveDetay;
    @BindView(R.id.etGunlukCerceveDetay) EditText etGunlukCerceveDetay;
    @BindView(R.id.etLarvaCerceveDetay) EditText etLarvaCerceveDetay;
    @BindView(R.id.etKapaliCerceveDetay) EditText etKapaliCerceveDetay;
    @BindView(R.id.radioGrupAnaAriDetay) RadioGroup radioGrupAnaAriDetay;
    @BindView(R.id.radioGrupHastalikDetay) RadioGroup radioGrupHastalikDetay;
    @BindView(R.id.radioGrupIlaclamaDetay) RadioGroup radioGrupIlaclamaDetay;
    @BindView(R.id.rbGorulduDetay) RadioButton rbGorulduDetay;
    @BindView(R.id.rbGorulmediDetay) RadioButton rbGorulmediDetay;
    @BindView(R.id.rbVarDetay) RadioButton rbVarDetay;
    @BindView(R.id.rbYokDetay) RadioButton rbYokDetay;
    @BindView(R.id.rbYapildiDetay) RadioButton rbYapildiDetay;
    @BindView(R.id.rbYapilmadiDetay) RadioButton rbYapilmadiDetay;
    @BindView(R.id.checkBoxKekDetay) CheckBox checkBoxKekDetay;
    @BindView(R.id.checkBoxSurupDetay) CheckBox checkBoxSurupDetay;
    @BindView(R.id.checkBoxDigerDetay) CheckBox checkBoxDigerDetay;
    @BindView(R.id.etGenelGozlemDetay) EditText etGenelGozlemDetay;
    @BindView(R.id.btnUpdateDenetimDetay) Button btnUpdateDenetimDetay;
    @BindView(R.id.btnDeleteDenetimDetay) Button btnDeleteDenetimDetay;
    private String anaAriDurum,hastalikDurum,ilaclamaDurum,kek,surup,diger;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    Calendar cal = Calendar.getInstance();
    private Database db;
    View view;
    int denetimId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_denetim_detay, container, false);

        ButterKnife.bind(this,view);

        db = new Database(getContext());

        denetimId = DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getId();

        setDenetimTarih();

        setData();

        getAnaAriDurum();

        getHastalikDurum();

        getIlaclamaDurum();



        clickedUpdateButton();

        return view;
    }



    private void setDenetimTarih(){

        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(cal.getTime());
        denetimEkleTarihDetay.setText(currentDate);

        denetimEkleTarihDetay.setOnClickListener(new View.OnClickListener() {
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
                denetimEkleTarihDetay.setText(date);
            }
        };

    }

    private void getAnaAriDurum(){

        radioGrupAnaAriDetay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.rbGorulduDetay){
                    anaAriDurum = "goruldu";
                }
                if(checkedId == R.id.rbGorulmediDetay){
                    anaAriDurum = "gorulmedi";
                }
                if(checkedId != R.id.rbGorulduDetay && checkedId != R.id.rbGorulmediDetay){
                    anaAriDurum = "null";
                }

            }
        });
    }

    private void getHastalikDurum(){

        radioGrupHastalikDetay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbVarDetay:
                        hastalikDurum = "var";
                        break;
                    case R.id.rbYokDetay:
                        hastalikDurum = "yok";
                        break;
                }
            }
        });
    }

    private void getIlaclamaDurum(){

        radioGrupIlaclamaDetay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbYapildiDetay:
                        ilaclamaDurum = "yapildi";
                        break;
                    case R.id.rbYapilmadiDetay:
                        ilaclamaDurum = "yapilmadi";
                        break;
                }
            }
        });
    }

    private void getBesleme(){

        if(checkBoxKekDetay.isChecked()){
            kek = "true";
        }
        if(!checkBoxKekDetay.isChecked()){
            kek = "false";
        }
        if(checkBoxSurupDetay.isChecked()){
            surup = "true";
        }
        if(!checkBoxSurupDetay.isChecked()){
            surup = "false";
        }
        if(checkBoxDigerDetay.isChecked()){
            diger = "true";
        }
        if(!checkBoxDigerDetay.isChecked()){
            diger = "false";
        }
    }

    private void setAnaAri(){

        switch( DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getAna_ari()){

            case "goruldu":
                rbGorulduDetay.setChecked(true);
                break;
            case "gorulmedi":
                rbGorulmediDetay.setChecked(true);
                break;
        }
    }

    private void setHastalik(){

            switch( DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getHastalik_belirtisi()){

                case "var":
                    rbVarDetay.setChecked(true);
                    break;
                case "yok":
                    rbYokDetay.setChecked(true);
                    break;
            }
    }

    private void setIlaclamaDurum(){

        switch( DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getIlaclama()){

            case "yapildi":
                rbYapildiDetay.setChecked(true);
                break;
            case "yapilmadi":
                rbYapilmadiDetay.setChecked(true);
                break;
        }
    }

    private void setKek(){

        switch( DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getKek()){

            case "true":
                checkBoxKekDetay.setChecked(true);
                break;
            case "false":
                checkBoxKekDetay.setChecked(false);
                break;
        }
    }

    private void setSurup(){

        switch( DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getSurup()){

            case "true":
                checkBoxSurupDetay.setChecked(true);
                break;
            case "false":
                checkBoxSurupDetay.setChecked(false);
                break;
        }
    }

    private void setDiger(){

        switch( DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getDiger()){

            case "true":
                checkBoxDigerDetay.setChecked(true);
                break;
            case "false":
                checkBoxDigerDetay.setChecked(false);
                break;
        }
    }

    private void setData(){

        if(getArguments() != null){

            String kovan = DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getKovan_no();
            String denetimEkleTarih = DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getDenetim_tarih();
            String CerceveSayisi = DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getCerceve_sayisi();
            String AriliCerceve = DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getArili_cerceve();
            String BalliCerceve = DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getBalli_cerceve();
            String KabarikCerceve = DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getKabarik_cerceve();
            String HamCerceve = DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getHam_cerceve();
            String GunlukCerceve = DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getGunluk_cerceve();
            String LarvaCerceve = DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getLarva_cerceve();
            String KapaliCerceve = DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getKapali_cerceve();
            String GenelGozlem = DenetimDetayFragmentArgs.fromBundle(getArguments()).getDenetim().getGenel_gozlem();

            etKovanDetay.setText(kovan);
            denetimEkleTarihDetay.setText(denetimEkleTarih);
            etCerceveSayisiDetay.setText(CerceveSayisi);
            etAriliCerceveDetay.setText(AriliCerceve);
            etBalliCerceveDetay.setText(BalliCerceve);
            etKabarikCerceveDetay.setText(KabarikCerceve);
            etHamCerceveDetay.setText(HamCerceve);
            etGunlukCerceveDetay.setText(GunlukCerceve);
            etLarvaCerceveDetay.setText(LarvaCerceve);
            etKapaliCerceveDetay.setText(KapaliCerceve);
            etGenelGozlemDetay.setText(GenelGozlem);

            //setAnaAri();

            //setHastalik();

            //setIlaclamaDurum();

            setKek();
            setSurup();
            setDiger();


        }
    }

    private void clickedUpdateButton(){

        btnUpdateDenetimDetay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String denetimTarih = denetimEkleTarihDetay.getText().toString().trim();
                String kovanNo = etKovanDetay.getText().toString().trim();
                String cerceveSayi = etCerceveSayisiDetay.getText().toString().trim();
                String ariliCerceveSayi = etAriliCerceveDetay.getText().toString().trim();
                String balliCerceveSayi = etBalliCerceveDetay.getText().toString().trim();
                String kabarikCerceveSayi = etKabarikCerceveDetay.getText().toString().trim();
                String hamCerceveSayi = etHamCerceveDetay.getText().toString().trim();
                String gunlukCerceveSayi = etGunlukCerceveDetay.getText().toString().trim();
                String larvaCerceveSayi = etLarvaCerceveDetay.getText().toString().trim();
                String kapaliCerceveSayi = etKapaliCerceveDetay.getText().toString().trim();
                String genelGozlem = etGenelGozlemDetay.getText().toString().trim();

                getBesleme();

                new DenetimlerDAO().updateDenetim(db,denetimId,kovanNo,denetimTarih,cerceveSayi,
                        ariliCerceveSayi,balliCerceveSayi,kabarikCerceveSayi,
                        hamCerceveSayi,anaAriDurum,gunlukCerceveSayi,larvaCerceveSayi,
                        kapaliCerceveSayi,kek,surup,diger,hastalikDurum,ilaclamaDurum,genelGozlem,v);

                Log.i("kek",kek);
                Log.i("surup",surup);
                Log.i("diger",diger);
            }
        });


    }

    /*private void clickedDeleteButton(){

        btnDeleteDenetimDetay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v,"Silinsin mi?",Snackbar.LENGTH_LONG).setAction("Evet", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new DenetimlerDAO().deleteDenetim(db,denetimId,v);

                        Navigation.findNavController(view).popBackStack();

                    }
                }).show();


            }
        });
    } */
}