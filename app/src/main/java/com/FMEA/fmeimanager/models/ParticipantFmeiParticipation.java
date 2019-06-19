package com.FMEA.fmeimanager.models;

import com.FMEA.fmeimanager.database.Fmei;
import com.FMEA.fmeimanager.database.TeamFmei;

import java.util.ArrayList;
import java.util.List;

public class ParticipantFmeiParticipation {

    private List<TeamFmei> mTeamFmeiList;
    private List<Fmei> mFmeiList;

    public void setTeamFmeiList(List<TeamFmei> teamFmeiList) {
        mTeamFmeiList = teamFmeiList;
    }

    public void setFmeiList(List<Fmei> fmeiList) {
        mFmeiList = fmeiList;
    }

    public List<TeamFmei> getTeamFmeiList() {
        return mTeamFmeiList;
    }

    public List<Fmei> getFmeiList() {
        return mFmeiList;
    }

    //GET FMEA title String
    public List<String> getFmeaParticipation(List<Fmei> fmeiList){
        List<Fmei> selected =  getSelectedFmea(fmeiList);
        List<String> strings = new ArrayList<>();
        for (Fmei fmei : selected){
            strings.add(fmei.getName());
        }
        return strings;
    }

    //GET TeamLeader participation boolean
    public List<Boolean> getTeamLeaderParticipation(long participantId, List<Fmei> fmeiList){
        List<Fmei> selected =  getSelectedFmea(fmeiList);
        List<Boolean> result = new ArrayList<>();
        if (mTeamFmeiList != null){
            for (int j = 0 ; j < selected.size() ; j++){
                if (selected.get(j).getTeamLeader() == participantId){
                    result.add(true);
                }else {
                    result.add(false);
                }
            }
        }
        return result;
    }

    //GET selected FMEA into TEAM FMEI
    private List<Fmei> getSelectedFmea(List<Fmei> fmeiList){
        List<Fmei> result = new ArrayList<>();
        if (mTeamFmeiList != null) {
            for (int i = 0; i < mTeamFmeiList.size(); i++) {
                for (int j = 0; j < fmeiList.size(); j++) {
                    if (fmeiList.get(j).getId() == mTeamFmeiList.get(i).getFmeiId()) {
                        result.add(fmeiList.get(j));
                    }
                }
            }
        }
        return result;
    }
}
