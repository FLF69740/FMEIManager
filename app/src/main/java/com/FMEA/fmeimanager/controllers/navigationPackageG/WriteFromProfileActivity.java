package com.FMEA.fmeimanager.controllers.navigationPackageG;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WriteFromProfileActivity extends AppCompatActivity {

    @BindView(R.id.profile_write_activity_cancel_btn) Button mCancelBtn;
    @BindView(R.id.profile_write_activity_ok_btn) Button mValidateBtn;
    @BindView(R.id.profile_write_activity_initial_processus_name) TextView mTextViewInitialName;
    @BindView(R.id.profile_write_activity_editTextNewName) EditText mEditText;

    public static final String BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION = "BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_write_from_profile);
        ButterKnife.bind(this);
        mTextViewInitialName.setText(getIntent().getStringExtra(ProfileFragment.BUNDLE_KEY_DEFINITION));
        if (mTextViewInitialName.getText().toString().equals("")){
            mTextViewInitialName.setText(Utils.EMPTY);
        }
    }

    @OnClick(R.id.profile_write_activity_ok_btn)
    public void validateNewName(){
        if (!mEditText.getText().toString().equals("")) {
            this.closeActivity(true);
        }else {
            this.closeActivity(false);
        }
    }

    @OnClick(R.id.profile_write_activity_cancel_btn)
    public void cancelNewName(){
        this.closeActivity(false);
    }

    private void closeActivity(boolean decision){
        String name = mTextViewInitialName.getText().toString();
        if (decision){
            name = mEditText.getText().toString();
        }
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_PROFILE_WRITE_ACTIVITY_NEW_DEFINITION, name);
        if (decision) {
            setResult(RESULT_OK, intent);
        }else {
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }
}
