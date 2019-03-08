package com.example.fmeimanager.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.activities.ExportDatas;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExportDatasFragment extends Fragment {

    private View mView;

    public ExportDatasFragment() {}

    public static ExportDatasFragment newInstance(){
        return new ExportDatasFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_export_datas, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

}
