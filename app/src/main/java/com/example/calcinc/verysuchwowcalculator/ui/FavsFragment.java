package com.example.calcinc.verysuchwowcalculator.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.calcinc.verysuchwowcalculator.R;
import com.example.calcinc.verysuchwowcalculator.util.HistoryHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavsFragment extends Fragment {

    public FavsFragment() {
    }

    private FavsAdapter adapter;

    @BindView(R.id.favs_list)
    ListView favsList;

    @BindView(R.id.favs_refresh)
    SwipeRefreshLayout refreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favs, container, false);
        ButterKnife.bind(this, v);

        adapter = new FavsAdapter(HistoryHelper.getInstance().getFavs());

        refreshLayout.setOnRefreshListener(() -> {
            HistoryHelper.getInstance().refreshFavs(adapter.deleteMarked());
            refreshLayout.setRefreshing(false);
        });

        favsList.setAdapter(adapter);
        return v;
    }

}
