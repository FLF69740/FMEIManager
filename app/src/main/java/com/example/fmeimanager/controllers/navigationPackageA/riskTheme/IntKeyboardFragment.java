package com.example.fmeimanager.controllers.navigationPackageA.riskTheme;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fmeimanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class IntKeyboardFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.one_btn) TextView mOne;
    @BindView(R.id.two_btn) TextView mTwo;
    @BindView(R.id.three_btn) TextView mThree;
    @BindView(R.id.four_btn) TextView mFour;
    @BindView(R.id.five_btn) TextView mFive;
    @BindView(R.id.six_btn) TextView mSix;
    @BindView(R.id.seven_btn) TextView mSeven;
    @BindView(R.id.height_btn) TextView mHeight;
    @BindView(R.id.nine_btn) TextView mNine;
    @BindView(R.id.zero_btn) TextView mTen;

    public IntKeyboardFragment() {}

    public static IntKeyboardFragment newInstance(){
        return new IntKeyboardFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_int_keyboard, container, false);
        ButterKnife.bind(this, view);

        mOne.setOnClickListener(this);
        mTwo.setOnClickListener(this);
        mThree.setOnClickListener(this);
        mFour.setOnClickListener(this);
        mFive.setOnClickListener(this);
        mSix.setOnClickListener(this);
        mSeven.setOnClickListener(this);
        mHeight.setOnClickListener(this);
        mNine.setOnClickListener(this);
        mTen.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        mCallback.keyboardIntegerResult(view, Integer.valueOf(view.getTag().toString()));
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface KeyboardIntegerItemClickedListener{
        void keyboardIntegerResult(View view, int result);
    }

    //callback for button clicked
    private KeyboardIntegerItemClickedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (KeyboardIntegerItemClickedListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }
}
