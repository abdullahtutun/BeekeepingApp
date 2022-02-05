package com.example.beekeeping.fragments;

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

import com.example.beekeeping.adapters.NotAdapter;
import com.example.beekeeping.database.Database;
import com.example.beekeeping.database.NotlarDAO;
import com.example.beekeeping.models.NotModel;
import com.example.beekeeping.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotlarFragment extends Fragment {

    @BindView(R.id.rvNotlar) RecyclerView rvNotlar;
    @BindView(R.id.fabNot) FloatingActionButton fabNot;
    @BindView(R.id.tvNotSayisi) TextView tvNotSayisi;
    private ArrayList<NotModel> notlarArraylist,notlarArraylistAll;
    private NotAdapter adapter;
    Database db;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notlar, container, false);

        ButterKnife.bind(this,view);

        db = new Database(getContext());

        rvNotlar.setHasFixedSize(true);
        rvNotlar.setLayoutManager(new LinearLayoutManager(getContext()));

        notlarArraylist = new NotlarDAO().getNotlar(db);
        notlarArraylistAll = new NotlarDAO().getNotlarAll(db);

        adapter = new NotAdapter(getContext(),notlarArraylist, notlarArraylistAll);
        rvNotlar.setAdapter(adapter);

        setNotSayi();

        clickedFab();

        return view;
    }

    private void setNotSayi(){

        long count = new NotlarDAO().getCountNot(db);

        tvNotSayisi.setText(String.valueOf(count));
    }

    private void clickedFab(){
        fabNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = NotlarFragmentDirections.actionNotlarToNotEkleFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }
}