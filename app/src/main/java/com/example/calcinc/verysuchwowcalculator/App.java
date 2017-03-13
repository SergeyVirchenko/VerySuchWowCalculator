package com.example.calcinc.verysuchwowcalculator;

import android.app.Application;

import com.example.calcinc.verysuchwowcalculator.util.HistoryHelper;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HistoryHelper.init(this);
    }
}
