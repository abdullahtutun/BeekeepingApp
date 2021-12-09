package com.example.beekeeping.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beekeeping.R;
import com.github.barteksc.pdfviewer.PDFView;

public class YonetmelikFragment extends Fragment {
View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_yonetmelik, container, false);

        PDFView pdfView = view.findViewById(R.id.pdfviewYonetmelik);

        pdfView.fromAsset("yonetmelik.pdf").load();

        return view;
    }
}