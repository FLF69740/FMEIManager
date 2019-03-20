package com.example.fmeimanager.controllers.navigationPackage1.processusTheme;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fmeimanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsertRiskPageFragment extends Fragment implements View.OnClickListener {

    private static final String KEY_POSITION = "KEY_POSITION";
    private static final String KEY_PROCESSUS_ID_LIST  = "KEY_PROCESSUS_ID_LIST";

    public InsertRiskPageFragment() {}

    public static InsertRiskPageFragment newInstance(String stringList, int position){
        InsertRiskPageFragment processusIdPageFragment = new InsertRiskPageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PROCESSUS_ID_LIST, stringList);
        bundle.putInt(KEY_POSITION, position);
        processusIdPageFragment.setArguments(bundle);

        return processusIdPageFragment;
    }

    private String mProcessusIdTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_processus_id_page, container, false);

        TextView textView = view.findViewById(R.id.risk_viewpager_title_process_id);

        assert getArguments() != null;
        int position = getArguments().getInt(KEY_POSITION, 0);
        String oneStringList = getArguments().getString(KEY_PROCESSUS_ID_LIST, null);

        String[] parts = oneStringList.split("/");

        mProcessusIdTitle = parts[position];
        String title = "PROCESSUS " + mProcessusIdTitle;
        textView.setText(title);

        ImageView imageView = view.findViewById(R.id.risk_insert_pager_click);
        imageView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        mCallback.processsusIdPageFragment_To_ViewPagerInsertRiskActivity(v, Long.valueOf(mProcessusIdTitle));
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface RiskViewPagerItemClickedListener{
        void processsusIdPageFragment_To_ViewPagerInsertRiskActivity(View view, long processusId);
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