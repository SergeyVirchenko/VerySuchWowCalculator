package com.example.calcinc.verysuchwowcalculator.util;

import android.content.Context;

import java.util.ArrayList;

public class HistoryHelper {

    private static final String HISTORY = "HISTORY";
    private static final String FAVORITES = "FAVORITES";

    private Context context;
    private TinyDB db;

    public HistoryHelper(Context context) {
        db = new TinyDB(context);
    }

    public void addHistoryItem(String s) {
        ArrayList<String> history = db.getListString(HISTORY);
        if (history.size() == 10) history.remove(0);
        history.add(s);
        db.putListString(HISTORY, history);
    }

    public ArrayList<String> getHistory() {
        return db.getListString(HISTORY);
    }

    public void addFavorite(String s){
        ArrayList<String> history = db.getListString(FAVORITES);
        history.add(s);
        db.putListString(FAVORITES, history);
    }

    public ArrayList<String> getFavs() {
        return db.getListString(FAVORITES);
    }
}

