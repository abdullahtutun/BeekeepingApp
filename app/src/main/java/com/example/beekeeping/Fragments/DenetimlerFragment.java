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

import com.example.beekeeping.Adapters.DenetimAdapter;
import com.example.beekeeping.Database.Database;
import com.example.beekeeping.Database.DenetimlerDAO;
import com.example.beekeeping.Database.KovanlarDAO;
import com.example.beekeeping.Models.DenetimModel;
import com.example.beekeeping.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DenetimlerFragment extends Fragment {

    @BindView(R.id.rvDenetimler) RecyclerView rvDenetimler;
    @BindView(R.id.fabDenetim) FloatingActionButton fabDenetim;
    private ArrayList<DenetimModel> denetimlerList,denetimlerArrayListAll;
    private DenetimAdapter adapter;
    private Database db;
    View view;
    String kovanNo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_denetimler, container, false);

        ButterKnife.bind(this,view);

        db = new Database(getContext());

        rvDenetimler.setHasFixedSize(true);
        rvDenetimler.setLayoutManager(new LinearLayoutManager(getContext()));

        kovanNo = DenetimlerFragmentArgs.fromBundle(getArguments()).getKovanNo();

        denetimlerList = new DenetimlerDAO().getDenetimler(db, kovanNo);
        denetimlerArrayListAll = new DenetimlerDAO().getAllDenetimler(db, kovanNo);

        adapter = new DenetimAdapter(getContext(),denetimlerList,denetimlerArrayListAll);
        rvDenetimler.setAdapter(adapter);

        clickedFab();

        return view;
    }

    private void clickedFab(){
        fabDenetim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DenetimlerFragmentDirections.ActionDenetimlerToDenetimEkleFragment action = DenetimlerFragmentDirections.actionDenetimlerToDenetimEkleFragment();
                action.setKovanNo(kovanNo);

                Navigation.findNavController(view).navigate(action);
            }
        });
    }

}