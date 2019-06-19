package com.FMEA.fmeimanager.controllers.navigationPackageA.riskTheme;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.FMEA.fmeimanager.R;

public class IntKeyboardActivity extends AppCompatActivity implements IntKeyboardFragment.KeyboardIntegerItemClickedListener {

    private String mSourceCriteria;
    public static final String BUNDLE_KEYBOARD_SCORE = "BUNDLE_KEYBOARD_SCORE";
    public static final String BUNDLE_KEYBOARD_SOURCE = "BUNDLE_KEYBOARD_SOURCE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_int_keyboard);
        mSourceCriteria = getIntent().getStringExtra(RiskFileDescriptionFragment.BUNDLE_KEY_CRITERIA_SCORE);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout_int_keyboard, IntKeyboardFragment.newInstance())
                .commit();

    }

    @Override
    public void keyboardIntegerResult(View view, int result) {
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_KEYBOARD_SCORE, result);
        intent.putExtra(BUNDLE_KEYBOARD_SOURCE, mSourceCriteria);
        if (result != 11) {
            setResult(RESULT_OK, intent);
        }else {
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }
}
