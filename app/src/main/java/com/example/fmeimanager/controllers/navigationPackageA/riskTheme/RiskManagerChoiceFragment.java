package com.example.fmeimanager.controllers.navigationPackageA.riskTheme;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fmeimanager.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RiskManagerChoiceFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private static final String BUNDLE_CURRENT_ID = "BUNDLE_CURRENT_ID";
    private static final String BUNDLE_LIST_ID = "BUNDLE_LIST_ID";
    private static final String BUNDLE_LIST_NAME = "BUNDLE_LIST_NAME";
    private static final String BUNDLE_LIST_FORNAME = "BUNDLE_LIST_FORNAME";

    private View mView;
    private long mStartUserId;
    private List<String> mUsersCompleteName;
    private List<Long> mUsersIdLong;
    private long mActiveTag;
    private String mUserSelected;
    private String mUserNameResult;
    private String mUserFornameResult;
    List<String> mUsersName;
    List<String> mUsersForname;

    @BindView(R.id.fragment_risk_manager_choice_radiogroup) RadioGroup mRadioGroup;

    public RiskManagerChoiceFragment() {}

    public static RiskManagerChoiceFragment newInstance(long id, ArrayList<String> arrayListStringId, ArrayList<String> arrayListStringForname, ArrayList<String> arrayListStringName){
        RiskManagerChoiceFragment riskManagerChoiceFragment = new RiskManagerChoiceFragment();
        Bundle bundle = new Bundle(4);
        bundle.putLong(BUNDLE_CURRENT_ID, id);
        bundle.putStringArrayList(BUNDLE_LIST_ID, arrayListStringId);
        bundle.putStringArrayList(BUNDLE_LIST_NAME, arrayListStringName);
        bundle.putStringArrayList(BUNDLE_LIST_FORNAME, arrayListStringForname);
        riskManagerChoiceFragment.setArguments(bundle);

        return riskManagerChoiceFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_risk_manager_choice, container, false);
        ButterKnife.bind(this, mView);
        mStartUserId = getArguments().getLong(BUNDLE_CURRENT_ID);
        mUsersName = getArguments().getStringArrayList(BUNDLE_LIST_NAME);
        mUsersForname = getArguments().getStringArrayList(BUNDLE_LIST_FORNAME);
        List<String> usersIdString = getArguments().getStringArrayList(BUNDLE_LIST_ID);
        mUsersIdLong = new ArrayList<>();
        mUsersCompleteName = new ArrayList<>();

        for (int i = 0 ; i < usersIdString.size() ; i++){
            mUsersIdLong.add(Long.valueOf(usersIdString.get(i)));
            mUsersCompleteName.add(mUsersForname.get(i) + " " + mUsersName.get(i));
        }

        this.createRadioGroup();

        return mView;
    }

    private void createRadioGroup(){
        mRadioGroup.setOnCheckedChangeListener(null);
        mRadioGroup.clearCheck();

        for (int i = 0 ; i < mUsersCompleteName.size(); i++) {
            mRadioGroup.addView(new RadioButton(getContext()),i);
            ((RadioButton) mRadioGroup.getChildAt(i)).setText(mUsersCompleteName.get(i));
            mRadioGroup.getChildAt(i).setTag(i);

            if (mUsersIdLong.get(i) == mStartUserId){
                ((RadioButton) mRadioGroup.getChildAt(i)).setChecked(true);
                mUserSelected = mUsersCompleteName.get(i);
             //   mActiveTag = i + 1;
                mActiveTag = mUsersIdLong.get(i);
                mUserNameResult = mUsersName.get(i);
                mUserFornameResult = mUsersForname.get(i);
            }
        }

        // -------------- TEMP ------------------------
    /*    mRadioGroup.addView(new RadioButton(getContext()),1);
        ((RadioButton) mRadioGroup.getChildAt(1)).setText("USER_2");
        mRadioGroup.getChildAt(1).setTag(1);
        mRadioGroup.addView(new RadioButton(getContext()),2);
        ((RadioButton) mRadioGroup.getChildAt(2)).setText("USER_3");
        mRadioGroup.getChildAt(2).setTag(2);
        mUsersForname.add("USER");
        mUsersForname.add("USER");
        mUsersName.add("_2");
        mUsersName.add("_3");
        mUsersCompleteName.add("USER_2");
        mUsersCompleteName.add("USER_3");
        mUsersIdLong.add((long) 2);
        mUsersIdLong.add((long) 3);*/
        // --------------------------------------------

        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton = getActivity().findViewById(checkedId);
        mUserSelected = radioButton.getText().toString();
        Toast.makeText(getActivity(), mUserSelected, Toast.LENGTH_SHORT).show();

        for (int i = 0; i < mUsersCompleteName.size(); i++){
            if (mUsersCompleteName.get(i).equals(mUserSelected)){
             //   mActiveTag = i +1;
                mActiveTag = mUsersIdLong.get(i);
                mUserNameResult = mUsersName.get(i);
                mUserFornameResult = mUsersForname.get(i);
            }
        }
    }

    @OnClick(R.id.fragment_risk_manager_choice_cancel_button)
    public void cancelClick(){
        mCallback.riskManagerChoiceCancel(mView);
    }

    @OnClick(R.id.fragment_risk_manager_choice_ok_button)
    public void validateClick(){
        mCallback.riskManagerChoiceValidate(mView, mActiveTag, mUserNameResult, mUserFornameResult);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface RiskManagerItemClickedListener{
        void riskManagerChoiceCancel(View view);
        void riskManagerChoiceValidate(View view, long resultId, String name, String forname);
    }

    //callback for button clicked
    private RiskManagerItemClickedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (RiskManagerItemClickedListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }


}
