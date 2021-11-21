package com.example.beekeeping.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.beekeeping.Activities.MainActivity;
import com.example.beekeeping.Adapters.KovanAdapter;
import com.example.beekeeping.Database.Database;
import com.example.beekeeping.Database.KovanlarDAO;
import com.example.beekeeping.Models.KovanModel;
import com.example.beekeeping.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class KovanlarFragment extends Fragment {

    View view;
    @BindView(R.id.rvKovanlar) RecyclerView rvKovanlar;
    @BindView(R.id.fabKovan) FloatingActionButton fabKovan;
    @BindView(R.id.tvKovanSayisi) TextView tvKovanSayisi;
    private ArrayList<KovanModel> kovanlarList,kovanlarListAll;
    Database db;
    private KovanAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_kovanlar, container, false);

        ButterKnife.bind(this,view);

        db = new Database(getContext());

        rvKovanlar.setHasFixedSize(true);
        rvKovanlar.setLayoutManager(new LinearLayoutManager(getContext()));

        kovanlarList = new KovanlarDAO().getKovanlar(db);
        kovanlarListAll = new KovanlarDAO().getAllKovanlar(db);

        adapter = new KovanAdapter(getContext(),kovanlarList,kovanlarListAll);
        rvKovanlar.setAdapter(adapter);

        clickedFab();

        setKovanSayi();

        return view;
    }

    private void clickedFab(){
        fabKovan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavDirections action = KovanlarFragmentDirections.actionKovanlarToKovanEkleFragment();

                Navigation.findNavController(view).navigate(action);
            }
        });
    }
//kdsljfkldjlkgd
    private void setKovanSayi(){

        long count = new KovanlarDAO().getCountKovan(db);

        tvKovanSayisi.setText(String.valueOf(count));
    }



}