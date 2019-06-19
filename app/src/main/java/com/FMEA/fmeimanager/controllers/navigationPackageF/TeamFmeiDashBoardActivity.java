package com.FMEA.fmeimanager.controllers.navigationPackageF;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.base.BaseActivity;
import com.FMEA.fmeimanager.database.Participant;

import java.util.ArrayList;
import java.util.List;

public class TeamFmeiDashBoardActivity extends BaseActivity implements TeamFmeiFragment.TeamFmeiItemClickedListener {

    public static final String BUNDLE_TEAM_FMEI_FMEA_ID = "BUNDLE_TEAM_FMEI_FMEA_ID";
    public static final String BUNDLE_TEAM_FMEI_PARTICIPANT_TEAM_ID = "BUNDLE_TEAM_FMEI_PARTICIPANT_TEAM_ID";
    public static final String BUNDLE_TEAM_FMEI_TEAM_LEADER_ID = "BUNDLE_TEAM_FMEI_TEAM_LEADER_ID";

    @Override
    protected Fragment getFirstFragment() {
        return TeamFmeiFragment.newInstance();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_team_fmei;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_team_fmei_dashboard;
    }

    @Override
    protected boolean isAChildActivity() {
        return false;
    }

    public static final int RC_NEW_TEAM_FMEI = 421;

    //BUTTON FRAGMENT
    @Override
    public void teamFmeiDashboard_To_teamFmeiBuilder(View view, int position, List<Participant> participantList, long teamLeaderId) {
        ArrayList<String> participantListId = new ArrayList<>();
        for (int i = 0 ; i < participantList.size() ; i++){
            participantListId.add(String.valueOf(participantList.get(i).getId()));
        }
        Intent intent = new Intent(this, TeamFmeiBuilderActivity.class);
        intent.putExtra(BUNDLE_TEAM_FMEI_FMEA_ID, position);
        intent.putExtra(BUNDLE_TEAM_FMEI_PARTICIPANT_TEAM_ID, participantListId);
        intent.putExtra(BUNDLE_TEAM_FMEI_TEAM_LEADER_ID, teamLeaderId);
        startActivityForResult(intent, RC_NEW_TEAM_FMEI);
    }

    @Override
    public void updateTeamFmeiNavHeader(Participant participant) {
        this.updateHeader(participant);
    }

    /**
     *  On activity result
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
  /*      if (RC_NEW_TEAM_FMEI == requestCode && RESULT_OK == resultCode){
            ArrayList<String> packageNewFmeiId = data.getStringArrayListExtra(TeamFmeiBuilderActivity.BUNDLE_LONG_LIST_NEW_FMEI_ID);
            ArrayList<String> packageNewParticipantId = data.getStringArrayListExtra(TeamFmeiBuilderActivity.BUNDLE_LONG_LIST_NEW_PARTICIPANT_ID);
            ArrayList<String> teamFmeiIdToDelete = data.getStringArrayListExtra(TeamFmeiBuilderActivity.BUNDLE_LONG_LIST_TEAM_FMEI_ID_TO_DELETE);

            ((TeamFmeiFragment) getSupportFragmentManager().findFragmentById(getFragmentLayout())).updateLists(packageNewFmeiId, packageNewParticipantId, teamFmeiIdToDelete);

        }*/
    }


}
