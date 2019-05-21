package com.example.fmeimanager.controllers.navigationPackageB;


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
import com.example.fmeimanager.database.Fmei;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExportViewPagerFragment extends Fragment implements View.OnClickListener {

    public static final String BUNDLE_FMEA_TITLE = "BUNDLE_FMEA_TITLE";
    public static final String BUNDLE_FMEA_ID = "BUNDLE_FMEA_ID";
    public static final String BUNDLE_EXPORT_SIZE = "BUNDLE_EXPORT_SIZE";
    public static final String BUNDLE_EXPORT_POS = "BUNDLE_EXPORT_POS";
    private String mTitle;
    private long mFmeaId;

    public ExportViewPagerFragment() {}

    public static ExportViewPagerFragment newInstance(Fmei fmei,int position, int size){
        ExportViewPagerFragment exportViewPagerFragment = new ExportViewPagerFragment();
        Bundle bundle = new Bundle(4);
        bundle.putString(BUNDLE_FMEA_TITLE, fmei.getName());
        bundle.putLong(BUNDLE_FMEA_ID, fmei.getId());
        bundle.putInt(BUNDLE_EXPORT_POS, position);
        bundle.putInt(BUNDLE_EXPORT_SIZE, size);
        exportViewPagerFragment.setArguments(bundle);

        return exportViewPagerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_export_view_pager, container, false);

        TextView textView = view.findViewById(R.id.export_viewpager_fmei_title);
        InsertRiskCustomPos marker = view.findViewById(R.id.export_viewpager_custom_marker);

        mTitle = getArguments().getString(BUNDLE_FMEA_TITLE);
        mFmeaId = getArguments().getLong(BUNDLE_FMEA_ID);
        int position = getArguments().getInt(BUNDLE_EXPORT_POS);
        int size = getArguments().getInt(BUNDLE_EXPORT_SIZE);

        if (position == 0 && size > 1){
            marker.hideFirst();
        }else if ((position + 1) == size && size > 1){
            marker.hideLast();
        }else if ((position + 1) == size && size < 2){
            marker.onlyOne();
        }

        textView.setText(mTitle);

        ImageView imageView = view.findViewById(R.id.clickable_export_viewpager);
        imageView.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View v) {
        mCallback.LoadingFmea(mTitle, mFmeaId);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface ExportViewPagerListener{
        void LoadingFmea(String title, long id);
    }

    //callback for button clicked
    private ExportViewPagerListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (ExportViewPagerListener) getParentFragment();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }

}
