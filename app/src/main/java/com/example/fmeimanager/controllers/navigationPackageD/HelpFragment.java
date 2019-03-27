package com.example.fmeimanager.controllers.navigationPackageD;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fmeimanager.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {

    private View mView;

    public HelpFragment() {}

    public static HelpFragment newInstance(){
        return new HelpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_help, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

}
