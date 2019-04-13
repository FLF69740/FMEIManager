package com.example.fmeimanager.models;

import com.example.fmeimanager.database.Participant;

import java.util.List;

public class TeamPanel {

    private long mFmeaId;
    private List<Participant> mParticipantList;
    private long mTeamLeaderId;

    public TeamPanel(long fmeiad, long teamLeaderId) {
        mFmeaId = fmeiad;
        mTeamLeaderId = teamLeaderId;
    }

    //GETTER
    public long getFmeaId() {return mFmeaId;}
    public List<Participant> getParticipantList() {return mParticipantList;}
    public long getTeamLeaderId() {return mTeamLeaderId;}

    //SETTER
    public void setFmeaId(long fmeiad) {mFmeaId = fmeiad;}
    public void setParticipantList(List<Participant> participantList) {mParticipantList = participantList;}
    public void setTeamLeaderId(long teamLeaderId) {mTeamLeaderId = teamLeaderId;}
}
