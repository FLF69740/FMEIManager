package com.example.fmeimanager.controllers.navigationPackageA.riskTheme;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.database.TeamFmei;
import com.example.fmeimanager.utils.BitmapStorage;


public class RiskPhotoViewPagerFragment extends Fragment {

    private static final String KEY_POSITION = "KEY_POSITION";
    private static final String KEY_PHOTO_LIST_ID  = "KEY_PHOTO_LIST_ID";

    public RiskPhotoViewPagerFragment() {}

    public static RiskPhotoViewPagerFragment newInstance(String riskPhoto, int position){
        RiskPhotoViewPagerFragment riskPhotoViewPagerFragment = new RiskPhotoViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PHOTO_LIST_ID, riskPhoto);
        bundle.putInt(KEY_POSITION, position);
        riskPhotoViewPagerFragment.setArguments(bundle);

        return riskPhotoViewPagerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_risk_photo_view_pager, container, false);

        String photoname = getArguments().getString(KEY_PHOTO_LIST_ID, "");
        int position = getArguments().getInt(KEY_POSITION);

        ImageView imageView = view.findViewById(R.id.photo_viewpager_aera);
        imageView.setImageBitmap(BitmapStorage.loadImage(getContext(), photoname));

        ImageView trash = view.findViewById(R.id.photoViewPagerTrash);
        trash.setOnClickListener(v -> mCallback.sendViewPagerPositionToDelete(view, photoname, position));

        TextView textView = view.findViewById(R.id.photo_viewpager_title);
        textView.setText(BusinessRiskTheme.getPhotoTitle(photoname));

        return view;
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface PhotoViewPagerPositionListener{
        void sendViewPagerPositionToDelete(View view, String photoName, int position);
    }

    //callback for button clicked
    private PhotoViewPagerPositionListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (PhotoViewPagerPositionListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }

}
