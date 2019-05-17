package com.example.fmeimanager.controllers.navigationPackageA.processusTheme;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.navigationPackageA.processusTheme.drawing.InsertRiskCustomPos;

public class InsertRiskPageFragment extends Fragment implements View.OnClickListener {

    private static final String KEY_POSITION = "KEY_POSITION";
    private static final String KEY_SIZE = "KEY_SIZE";
    private static final String KEY_PROCESSUS_ID_LIST  = "KEY_PROCESSUS_ID_LIST";

    private int mPosition;

    public InsertRiskPageFragment() {}

    public static InsertRiskPageFragment newInstance(String processusStep, int position, int size){
        InsertRiskPageFragment processusIdPageFragment = new InsertRiskPageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PROCESSUS_ID_LIST, processusStep);
        bundle.putInt(KEY_POSITION, position);
        bundle.putInt(KEY_SIZE, size);
        processusIdPageFragment.setArguments(bundle);

        return processusIdPageFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_processus_id_page, container, false);

        TextView textView = view.findViewById(R.id.risk_viewpager_title_process_id);
        InsertRiskCustomPos marker = view.findViewById(R.id.risk_viewpager_custom_marker);

        assert getArguments() != null;
        int size = getArguments().getInt(KEY_SIZE);
        String processusIdTitle = getArguments().getString(KEY_PROCESSUS_ID_LIST, null);
        String title = getString(R.string.Process_dashboard_processus) + " " + processusIdTitle;
        mPosition = getArguments().getInt(KEY_POSITION, 0);
        textView.setText(title);

        if (mPosition == 0 && size > 1){
            marker.hideFirst();
        }else if ((mPosition + 1) == size && size > 1){
            marker.hideLast();
        }else if ((mPosition + 1) == size && size < 2){
            marker.onlyOne();
        }

        ImageView imageView = view.findViewById(R.id.risk_insert_pager_click);
        imageView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        mCallback.processsusIdPageFragment_To_ViewPagerInsertRiskActivity(v, mPosition);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface RiskViewPagerItemClickedListener{
        void processsusIdPageFragment_To_ViewPagerInsertRiskActivity(View view, int processusId);
    }

    //callback for button clicked
    private RiskViewPagerItemClickedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (RiskViewPagerItemClickedListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }
}
