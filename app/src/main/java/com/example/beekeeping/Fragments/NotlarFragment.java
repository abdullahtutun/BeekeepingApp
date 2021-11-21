package com.example.beekeeping.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beekeeping.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotlarFragment extends Fragment {

    @BindView(R.id.rvNotlar) RecyclerView rvNotlar;
    @BindView(R.id.fabNot) FloatingActionButton fabNot;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notlar, container, false);

        ButterKnife.bind(this,view);

        clickedFab();

        return view;
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