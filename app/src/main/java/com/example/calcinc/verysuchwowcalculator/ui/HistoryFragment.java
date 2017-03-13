package com.example.calcinc.verysuchwowcalculator.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.calcinc.verysuchwowcalculator.R;
import com.example.calcinc.verysuchwowcalculator.util.HistoryHelper;
import com.example.calcinc.verysuchwowcalculator.util.TinyDB;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HistoryFragment extends Fragment {

    private ArrayAdapter adapter;
    private HistoryHelper db;

    public HistoryFragment() {
    }

    @BindView(R.id.history_list)
    ListView historyList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new HistoryHelper(getContext().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, v);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.activity_list_item);

        historyList.setAdapter(adapter);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(db.getHistory());
        adapter.notifyDataSetChanged();
    }
}
