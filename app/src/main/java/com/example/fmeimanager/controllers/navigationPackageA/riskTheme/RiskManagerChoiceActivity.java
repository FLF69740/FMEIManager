package com.example.fmeimanager.controllers.navigationPackageA.riskTheme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fmeimanager.R;

public class RiskManagerChoiceActivity extends AppCompatActivity implements RiskManagerChoiceFragment.RiskManagerItemClickedListener{

    public static final String BUNDLE_NEW_MANAGER_ID = "BUNDLE_NEW_MANAGER_ID";
    public static final String BUNDLE_NEW_MANAGER_NAME = "BUNDLE_NEW_MANAGER_NAME";
    public static final String BUNDLE_NEW_MANAGER_FORNAME = "BUNDLE_NEW_MANAGER_FORNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_manager_choice);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout_risk_manager_choice, RiskManagerChoiceFragment.newInstance(
                        getIntent().getLongExtra(RiskFileDescriptionFragment.BUNDLE_KEY_RISK_MANAGER,1),
                        getIntent().getStringArrayListExtra(RiskFileDescriptionFragment.BUNDLE_KEY_RISK_LIST_MANAGER_ID),
                        getIntent().getStringArrayListExtra(RiskFileDescriptionFragment.BUNDLE_KEY_RISK_LIST_MANAGER_FORNAME),
                        getIntent().getStringArrayListExtra(RiskFileDescriptionFragment.BUNDLE_KEY_RISK_LIST_MANAGER_NAME)
                ))
                .commit();
    }

    @Override
    public void riskManagerChoiceCancel(View view) {
        finish();
    }

    @Override
    public void riskManagerChoiceValidate(View view, long resultId, String name, String forname) {
     //   Toast.makeText(this, ""+resultId + " : " + forname + " " + name, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_NEW_MANAGER_ID, resultId);
        intent.putExtra(BUNDLE_NEW_MANAGER_NAME, name);
        intent.putExtra(BUNDLE_NEW_MANAGER_FORNAME, forname);
        setResult(RESULT_OK, intent);
        finish();
    }
}
