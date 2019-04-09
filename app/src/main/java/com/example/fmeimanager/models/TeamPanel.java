package com.example.fmeimanager.models;

import com.example.fmeimanager.database.Participant;

import java.util.List;

public class TeamPanel {

    private long mFmeaId;
    private List<Participant> mParticipantList;

    public TeamPanel(long fmeiad) {
        mFmeaId = fmeiad;
    }

    //GETTER
    public long getFmeaId() {return mFmeaId;}
    public List<Participant> getParticipantList() {return mParticipantList;}

    //SETTER
    public void setFmeaId(long fmeiad) {mFmeaId = fmeiad;}
    public void setParticipantList(List<Participant> participantList) {mParticipantList = participantList;}
}
