package com.example.calcinc.verysuchwowcalculator.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.calcinc.verysuchwowcalculator.R;
import com.example.calcinc.verysuchwowcalculator.calcutils.Parser;
import com.example.calcinc.verysuchwowcalculator.util.HistoryHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalcFragment extends Fragment {

    @BindView(R.id.input)
    EditText input;

    public CalcFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calc, container, false);
        ButterKnife.bind(this, v);

        input.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public void onDestroyActionMode(ActionMode mode) {
            }

            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }
        });

        return v;
    }

    @OnClick({R.id.plus, R.id.minus, R.id.div, R.id.mult, R.id.left_scope, R.id.right_scope, R.id.equals, R.id.fav, R.id.erase})
    void onClick(View v) {
        String text = input.getText().toString();
        switch (v.getId()) {
            case R.id.plus:
                input.setText(text + "+");
                input.setSelection(input.getText().length());
                break;
            case R.id.minus:
                input.setText(text + "-");
                input.setSelection(input.getText().length());
                break;
            case R.id.mult:
                input.setText(text + "*");
                input.setSelection(input.getText().length());
                break;
            case R.id.div:
                input.setText(text + "/");
                input.setSelection(input.getText().length());
                break;
            case R.id.left_scope:
                input.setText(text + "(");
                input.setSelection(input.getText().length());
                break;
            case R.id.right_scope:
                input.setText(text + ")");
                input.setSelection(input.getText().length());
                break;
            case R.id.equals:
                HistoryHelper.getInstance().addHistoryItem(input.getText().toString());
                input.setText(Parser.parse(input.getText().toString()).toString());
                break;
            case R.id.fav:
                HistoryHelper.getInstance().addFavorite(input.getText().toString());
                break;
            case R.id.erase:
                input.setText("");
                break;
        }
    }
}
