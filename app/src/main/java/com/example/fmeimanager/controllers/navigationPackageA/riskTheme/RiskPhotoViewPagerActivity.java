package com.example.fmeimanager.controllers.navigationPackageA.riskTheme;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.navigationPackageA.riskTheme.adapters.RiskPhotoPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class RiskPhotoViewPagerActivity extends AppCompatActivity implements RiskPhotoViewPagerFragment.PhotoViewPagerPositionListener{

    public static final String BUNDLE_KEY_PHOTO_LIST = "BUNDLE_KEY_PHOTO_LIST";
    public static final String BUNDLE_KEY_VISIBILITY_BTN = "BUNDLE_KEY_VISIBILITY_BTN";
    public static final String BUNDLE_LIST_TRANSFERT = "BUNDLE_LIST_TRANSFERT";

    private ArrayList<String> mPhotoList;
    private TextView mSaveAndQuit;
    private RiskPhotoPageAdapter mAdapter;
    private int mVisibilityStateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_photo_view_pager);

        if (savedInstanceState != null){
            mPhotoList = savedInstanceState.getStringArrayList(BUNDLE_KEY_PHOTO_LIST);
            mVisibilityStateBtn = savedInstanceState.getInt(BUNDLE_KEY_VISIBILITY_BTN);
        }else {
            mPhotoList = BusinessRiskTheme.getPhotoList(getIntent().getStringExtra(RiskFileDescriptionFragment.BUNDLE_PHOTO_LIST_RISK));
            mVisibilityStateBtn = View.INVISIBLE;
        }

        ViewPager viewPager = findViewById(R.id.viewPagerRiskPhoto);
        mAdapter = new RiskPhotoPageAdapter(getSupportFragmentManager(), mPhotoList);
        viewPager.setAdapter(mAdapter);

        TextView close = findViewById(R.id.closePhotoViewPager);
        close.setOnClickListener(v -> {
            Toast.makeText(getApplication(), "GET list transfert", Toast.LENGTH_SHORT).show();
            finish();
        });

        mSaveAndQuit = findViewById(R.id.photoViewPagerSaveAndQuit);
        mSaveAndQuit.setVisibility(mVisibilityStateBtn);
        mSaveAndQuit.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra(BUNDLE_LIST_TRANSFERT, BusinessRiskTheme.getListPhotoToOriginalFormat(mPhotoList));
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(BUNDLE_KEY_PHOTO_LIST, mPhotoList);
        outState.putInt(BUNDLE_KEY_VISIBILITY_BTN, mVisibilityStateBtn);
    }

    @Override
    public void sendViewPagerPositionToDelete(View view, String photoName, int position) {
        Snackbar.make(view, photoName + " : " + getString(R.string.photo_viewpager_send_to_the_trash), Snackbar.LENGTH_SHORT).show();
        mVisibilityStateBtn = View.VISIBLE;
        mSaveAndQuit.setVisibility(mVisibilityStateBtn);
        mPhotoList.remove(position);
        mAdapter.reloadList(mPhotoList);
        mAdapter.notifyDataSetChanged();
    }
}
