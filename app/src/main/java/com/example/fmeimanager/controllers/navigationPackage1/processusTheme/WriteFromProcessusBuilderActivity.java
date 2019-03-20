package com.example.fmeimanager.controllers.navigationPackage1.processusTheme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fmeimanager.R;
import com.example.fmeimanager.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WriteFromProcessusBuilderActivity extends AppCompatActivity {

    @BindView(R.id.write_activity_cancel_btn) Button mCancelBtn;
    @BindView(R.id.write_activity_ok_btn) Button mValidateBtn;
    @BindView(R.id.write_activity_initial_processus_name) TextView mTextViewInitialName;
    @BindView(R.id.write_activity_editTextNewName) EditText mEditText;

    public static final String BUNDLE_WRITE_ACTIVITY_PROCESSUS_NAME = "BUNDLE_WRITE_ACTIVITY_PROCESSUS_NAME";
    public static final String BUNDLE_WRITE_ACTIVITY_PROCESSUS_POSITION = "BUNDLE_WRITE_ACTIVITY_PROCESSUS_POSITION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_from_processus_builder);
        ButterKnife.bind(this);
        String name = getIntent().getStringExtra(ProcessusBuilderFragment.BUNDLE_KEY_PROCESSUS_NAME);
        mTextViewInitialName.setText(name);
        Log.i(Utils.INFORMATION_LOG, name);
    }

    @OnClick(R.id.write_activity_ok_btn)
    public void validateNewName(){
        if (!mEditText.getText().toString().equals("")) {
            this.closeActivity(true);
        }
    }

    @OnClick(R.id.write_activity_cancel_btn)
    public void cancelNewName(){
        this.closeActivity(false);
    }

    private void closeActivity(boolean decision){
        String name = mTextViewInitialName.getText().toString();
        if (decision){
            name = mEditText.getText().toString();
        }
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_WRITE_ACTIVITY_PROCESSUS_NAME, name);
        intent.putExtra(BUNDLE_WRITE_ACTIVITY_PROCESSUS_POSITION, getIntent().getIntExtra(ProcessusBuilderFragment.BUNDLE_KEY_PROCESSUS_POSITION, 100000));
        setResult(RESULT_OK, intent);
        finish();
    }


}
