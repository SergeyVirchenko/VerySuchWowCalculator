package com.example.calcinc.verysuchwowcalculator.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calcinc.verysuchwowcalculator.R;
import com.example.calcinc.verysuchwowcalculator.util.HistoryHelper;

import java.util.ArrayList;

public class FavsAdapter extends BaseAdapter {

    private ArrayList<String> data = new ArrayList<>();

    private ArrayList<Integer> toDelete = new ArrayList<>();

    public FavsAdapter(ArrayList<String> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);

        ((TextView) view.findViewById(R.id.text)).setText(data.get(position));

        if (toDelete.contains(position)) {
            ((ImageView) view.findViewById(R.id.delete)).setColorFilter(Color.parseColor("#000000"));
        }

        view.findViewById(R.id.delete).setOnClickListener(v -> {
            toDelete.add(position);
            ((ImageView) view.findViewById(R.id.delete)).setColorFilter(Color.parseColor("#000000"));
        });

        return view;
    }

    public ArrayList<String> deleteMarked() {
        ArrayList<String> originalList = HistoryHelper.getInstance().getFavs();

        int diff = originalList.size() - data.size();

        ArrayList<String> newList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (!toDelete.contains(i)) {
                newList.add(data.get(i));
                toDelete.remove((Integer) i);
            }
        }

        data.clear();
        toDelete.clear();
        for (int i = 0; i < diff; i++) {
            data.add(i, originalList.get(i));
        }
        data.addAll(newList);
        notifyDataSetChanged();
        return data;
    }
}
