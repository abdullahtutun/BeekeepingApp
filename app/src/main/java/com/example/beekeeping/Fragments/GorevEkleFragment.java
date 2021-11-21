package com.example.beekeeping.Fragments;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.beekeeping.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GorevEkleFragment extends Fragment {

    @BindView(R.id.tvGorevEkleETarih) TextView tvGorevEkleETarih;
    @BindView(R.id.tvGorevEkleTTarih) TextView tvGorevEkleTTarih;
    @BindView(R.id.etGorevBaslik) EditText etGorevBaslik;
    @BindView(R.id.etGorev) EditText etGorev;
    @BindView(R.id.btnGorevEkle) Button btnGorevEkle;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gorev_ekle, container, false);

        ButterKnife.bind(this,view);

        clickedButton();

        return view;
    }

    private void clickedButton(){
        btnGorevEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}