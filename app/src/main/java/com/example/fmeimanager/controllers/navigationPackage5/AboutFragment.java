package com.example.fmeimanager.controllers.navigationPackage5;


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
public class AboutFragment extends Fragment {

    private View mView;

    public AboutFragment() {}

    public static AboutFragment newInstance(){
        return new AboutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

}
