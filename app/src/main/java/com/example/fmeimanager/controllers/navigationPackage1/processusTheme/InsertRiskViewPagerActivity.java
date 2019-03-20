package com.example.fmeimanager.controllers.navigationPackage1.processusTheme;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.navigationPackage1.processusTheme.adapters.InsertRiskPageAdapter;

public class InsertRiskViewPagerActivity extends AppCompatActivity implements InsertRiskPageFragment.RiskViewPagerItemClickedListener{

    public static final String BUNDLE_RISK_PROCESSUS_ID = "BUNDLE_RISK_PROCESSUS_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_insert_risk);

        Intent intent = getIntent();
        String stringList = intent.getStringExtra(ProcessDashboardFragment.BUNDLE_KEY_LIST_PROCESSUS_ID);
        int size = stringList.split("/").length;

        ViewPager viewPager = findViewById(R.id.viewPagerProcessusId);
        viewPager.setAdapter(new InsertRiskPageAdapter(getSupportFragmentManager(), size, stringList));

        TextView textView = findViewById(R.id.closeViewPager);
        textView.setOnClickListener(v -> finish());

    }

    @Override
    public void processsusIdPageFragment_To_ViewPagerInsertRiskActivity(View view, long processusId) {
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_RISK_PROCESSUS_ID, processusId);
        setResult(RESULT_OK, intent);
        finish();
    }
}
