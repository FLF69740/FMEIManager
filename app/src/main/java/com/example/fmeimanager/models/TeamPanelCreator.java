package com.example.fmeimanager.models;

import android.util.Log;

import com.example.fmeimanager.database.Fmei;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.Processus;
import com.example.fmeimanager.database.TeamFmei;
import com.example.fmeimanager.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TeamPanelCreator {

    private List<TeamPanel> mTeamPanels;
    private List<Participant> mParticipantList;

    public TeamPanelCreator() {
        mTeamPanels = new ArrayList<>();
    }

    //RECORD all fmeis into TeamPanelCreator
    public void setFmeiList(List<Fmei> fmeiList){
        if (fmeiList != null){
            for (int i = 0; i < fmeiList.size(); i++) {
                mTeamPanels.add(new TeamPanel(fmeiList.get(i).getId()));
                Log.i(Utils.INFORMATION_LOG, "new TeamPanel " + mTeamPanels.get(i).getFmeaId());
            }
        }
    }

    //RECORD all participants into TeamPanelCreator
    public void setParticipantList(List<Participant> participantList){
        mParticipantList = participantList;
    }

    //CREATE list of TeamFmea
    public void setTeamFmeaList(List<TeamFmei> teamFmeiList){
        for (int i = 0 ; i < mTeamPanels.size() ; i++){
            List<Participant> participants = new ArrayList<>();
            for (int j = 0 ; j < teamFmeiList.size() ; j++){

                if (teamFmeiList.get(j).getFmeiId() == mTeamPanels.get(i).getFmeaId()){
                    for (int k = 0 ; k < mParticipantList.size() ; k++){
                        if (mParticipantList.get(k).getId() == teamFmeiList.get(j).getParticipantId()){
                            participants.add(mParticipantList.get(k));
                            Log.i(Utils.INFORMATION_LOG, getParticipantAddInformation(mTeamPanels.get(i).getFmeaId(), mParticipantList.get(k)));
                        }
                    }
                }
            }
            mTeamPanels.get(i).setParticipantList(participants);
        }
    }

    //send complete information when a participant is added into TeamPanel
    public String getParticipantAddInformation(long teamPanelId, Participant participant){
        return "TEAM PANEL " + teamPanelId + " add Participant : " + participant.getName();
    }

    public List<TeamPanel> getTeamPanels(){
        return mTeamPanels;
    }

}
