package com.example.calcinc.verysuchwowcalculator.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calcinc.verysuchwowcalculator.R;

public class FavsFragment extends Fragment {

    public FavsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favs, container, false);
    }

}
