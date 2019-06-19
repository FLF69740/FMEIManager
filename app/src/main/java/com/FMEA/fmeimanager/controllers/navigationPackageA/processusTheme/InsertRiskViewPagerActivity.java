package com.FMEA.fmeimanager.controllers.navigationPackageA.processusTheme;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.controllers.navigationPackageA.processusTheme.adapters.InsertRiskPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class InsertRiskViewPagerActivity extends AppCompatActivity implements InsertRiskPageFragment.RiskViewPagerItemClickedListener{

    public static final String BUNDLE_RISK_PROCESSUS_ID = "BUNDLE_RISK_PROCESSUS_ID";

    private List<String> mListProcessusId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_insert_risk);

        Intent intent = getIntent();
        mListProcessusId = intent.getStringArrayListExtra(ProcessDashboardFragment.BUNDLE_KEY_LIST_PROCESSUS_ID);
        List<String> listProcessusStep = intent.getStringArrayListExtra(ProcessDashboardFragment.BUNDLE_KEY_LIST_PROCESSUS_STEP);
        int size = mListProcessusId.size();

        ViewPager viewPager = findViewById(R.id.viewPagerProcessusId);
        viewPager.setAdapter(new InsertRiskPageAdapter(getSupportFragmentManager(), size, listProcessusStep));

        TextView textView = findViewById(R.id.closeViewPager);
        textView.setOnClickListener(v -> finish());

    }

    @Override
    public void processsusIdPageFragment_To_ViewPagerInsertRiskActivity(View view, int position) {
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_RISK_PROCESSUS_ID, Integer.valueOf(mListProcessusId.get(position)));
        setResult(RESULT_OK, intent);
        finish();
    }
}
