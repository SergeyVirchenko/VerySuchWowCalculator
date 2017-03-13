package com.example.calcinc.verysuchwowcalculator.util;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class HistoryHelper {

    private static final String HISTORY = "HISTORY";
    private static final String FAVORITES = "FAVORITES";

    private TinyDB db;
    private static HistoryHelper instance;

    public static HistoryHelper init(Context context) {
        instance = new HistoryHelper(context);
        return instance;
    }

    public static HistoryHelper getInstance() {
        return instance;
    }

    private HistoryHelper(Context context) {
        db = new TinyDB(context);
    }

    public void addHistoryItem(String s) {
        ArrayList<String> history = db.getListString(HISTORY);
        if (history.size() == 10) history.remove(9);
        history.add(0, s);
        db.putListString(HISTORY, history);
    }

    public ArrayList<String> getHistory() {
        return db.getListString(HISTORY);
    }

    public void addFavorite(String s) {
        ArrayList<String> history = db.getListString(FAVORITES);
        history.add(0, s);
        db.putListString(FAVORITES, history);
    }

    public void refreshFavs(ArrayList<String> newFavs) {
        db.putListString(FAVORITES, newFavs);
    }

    public ArrayList<String> getFavs() {
        return db.getListString(FAVORITES);
    }
}

