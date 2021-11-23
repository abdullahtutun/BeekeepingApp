package com.example.beekeeping.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beekeeping.Adapters.GorevAdapter;
import com.example.beekeeping.Adapters.KovanAdapter;
import com.example.beekeeping.Database.Database;
import com.example.beekeeping.Database.GorevlerDAO;
import com.example.beekeeping.Database.KovanlarDAO;
import com.example.beekeeping.Models.GorevModel;
import com.example.beekeeping.Models.KovanModel;
import com.example.beekeeping.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GorevlerFragment extends Fragment {

    @BindView(R.id.rvGorevler) RecyclerView rvGorevler;
    @BindView(R.id.fabGorev) FloatingActionButton fabGorev;
    @BindView(R.id.tvGorevSayisi) TextView tvGorevSayisi;
    private ArrayList<GorevModel> gorevlerList,gorevlerListAll;
    Database db;
    private GorevAdapter adapter;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_gorevler, container, false);

        ButterKnife.bind(this,view);

        db = new Database(getContext());

        rvGorevler.setHasFixedSize(true);
        rvGorevler.setLayoutManager(new LinearLayoutManager(getContext()));

        gorevlerList = new GorevlerDAO().getGorevler(db);
        gorevlerListAll = new GorevlerDAO().getAllGorevler(db);

        adapter = new GorevAdapter(getContext(),gorevlerList,gorevlerListAll);
        rvGorevler.setAdapter(adapter);

        setCountGorev();

        clickedFab();

        return view;
    }

    private void setCountGorev(){

        long count = new GorevlerDAO().getCountGorev(db);

        tvGorevSayisi.setText(String.valueOf(count));
    }

    private void clickedFab(){
        fabGorev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               NavDirections action = GorevlerFragmentDirections.actionGorevlerToGorevEkleFragment();

               Navigation.findNavController(view).navigate(action);
            }
        });
    }
}