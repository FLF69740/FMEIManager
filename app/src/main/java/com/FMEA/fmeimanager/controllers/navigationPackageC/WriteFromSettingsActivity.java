package com.FMEA.fmeimanager.controllers.navigationPackageC;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WriteFromSettingsActivity extends AppCompatActivity {

    public static final String BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION = "BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION";

    @BindView(R.id.settings_write_activity_cancel_btn) Button mCancelBtn;
    @BindView(R.id.settings_write_activity_ok_btn) Button mValidateBtn;
    @BindView(R.id.settings_write_activity_old_information) TextView mTextViewInitialName;
    @BindView(R.id.settings_write_activity_new_information) EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_write_from_settings);
        ButterKnife.bind(this);
        mTextViewInitialName.setText(getIntent().getStringExtra(SettingsFragment.BUNDLE_KEY_SETTINGS_DEFINITION));
        if (isInteger(mTextViewInitialName.getText().toString())){
            mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
            this.configureTextWatcher();
        }
        if (mTextViewInitialName.getText().toString().equals("")){
            mTextViewInitialName.setText(Utils.EMPTY);
        }
    }

    //CONFIGURE max length if editText talk about score
    private void configureTextWatcher(){
        InputFilter[] inputFilters = new InputFilter[1];
        inputFilters[0] = new InputFilter.LengthFilter(3);
        mEditText.setFilters(inputFilters);
    }

    //verify if getIntent is an integer value
    private boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     *  ACTION
     */

    @OnClick(R.id.settings_write_activity_ok_btn)
    public void validateNewName(){
        if (!mEditText.getText().toString().equals("")) {
            this.closeActivity(true);
        }else {
            this.closeActivity(false);
        }
    }

    @OnClick(R.id.settings_write_activity_cancel_btn)
    public void cancelNewName(){
        this.closeActivity(false);
    }

    //CLOSE activity
    private void closeActivity(boolean decision){
        String name = mTextViewInitialName.getText().toString();
        if (decision){
            name = mEditText.getText().toString();
        }
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_SETTINGS_WRITE_ACTIVITY_NEW_DEFINITION, name);
        if (decision) {
            setResult(RESULT_OK, intent);
        }else {
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }
}
