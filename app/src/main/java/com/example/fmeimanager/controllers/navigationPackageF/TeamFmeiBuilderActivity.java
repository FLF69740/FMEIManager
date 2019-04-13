package com.example.fmeimanager.controllers.navigationPackageF;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import java.util.ArrayList;

public class TeamFmeiBuilderActivity extends BaseActivity implements TeamFmeiBuilderFragment.TeamFmeiBuilderListener{

    public static final String BUNDLE_LONG_LIST_NEW_FMEI_ID = "BUNDLE_LONG_LIST_NEW_FMEI_ID";
    public static final String BUNDLE_LONG_LIST_NEW_PARTICIPANT_ID = "BUNDLE_LONG_LIST_NEW_PARTICIPANT_ID";
    public static final String BUNDLE_LONG_LIST_TEAM_FMEI_ID_TO_DELETE = "BUNDLE_LONG_LIST_TEAM_FMEI_ID_TO_DELETE";

    @Override
    protected Fragment getFirstFragment() {
        return TeamFmeiBuilderFragment.newInstance(getIntent().getIntExtra(TeamFmeiDashBoardActivity.BUNDLE_TEAM_FMEI_FMEA_ID, 1),
                getIntent().getStringArrayListExtra(TeamFmeiDashBoardActivity.BUNDLE_TEAM_FMEI_PARTICIPANT_TEAM_ID),
                getIntent().getLongExtra(TeamFmeiDashBoardActivity.BUNDLE_TEAM_FMEI_TEAM_LEADER_ID, 1));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_team_fmei_builder;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_team_fmei_builder;
    }

    @Override
    protected Fragment getSecondFragment() {
        return null;
    }

    @Override
    protected int getSecondFragmentLayout() {
        return 0;
    }

    @Override
    protected boolean isAChildActivity() {
        return true;
    }

    //TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_save_single, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.toolbar_save :
                ((TeamFmeiBuilderFragment) getSupportFragmentManager().findFragmentById(getFragmentLayout())).saveNewParticipant();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void updateTeamFmeiDatas(ArrayList<String> packageNewFmeiId, ArrayList<String> packageNewParticipantId, ArrayList<String> teamFmeiIdToDelete) {
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_LONG_LIST_NEW_FMEI_ID, packageNewFmeiId);
        intent.putExtra(BUNDLE_LONG_LIST_NEW_PARTICIPANT_ID, packageNewParticipantId);
        intent.putExtra(BUNDLE_LONG_LIST_TEAM_FMEI_ID_TO_DELETE, teamFmeiIdToDelete);
        setResult(RESULT_OK, intent);
        finish();
    }
}
