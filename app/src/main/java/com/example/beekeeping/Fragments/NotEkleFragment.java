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

public class NotEkleFragment extends Fragment {

    @BindView(R.id.etNotTarih) TextView etNotTarih;
    @BindView(R.id.etNotBaslik) EditText etNotBaslik;
    @BindView(R.id.etNot) EditText etNot;
    @BindView(R.id.btnNotEkle) Button btnNotEkle;
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

        clickedButton();

        return view;
    }

    private void clickedButton(){
        btnNotEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}