package com.example.calcinc.verysuchwowcalculator.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.calcinc.verysuchwowcalculator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalcFragment extends Fragment {

    @BindView(R.id.input)
    EditText input;


    public CalcFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calc, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

}
