package com.FMEA.fmeimanager.models;

import com.FMEA.fmeimanager.database.Processus;

import java.util.ArrayList;
import java.util.List;

public class FmeiPanel {

    private long mFmeiId;
    private String mFmeiTitle;
    private List<Processus> mProcessusList;
    private long mTeamLeaderId;
    private String mTeamLeader;
    private int mParticipantNumber;
    private int mRiskAmount;
    private double mRiskRateAverage;
    private int mIPRMax;
    private int mRiskAmountHighLevel;
    private int mRiskAmountMiddleLevel;
    private int mRiskAmountLowLevel;

    public FmeiPanel(String fmeiTitle, long fmeiId, long teamLeaderId) {
        mTeamLeaderId = teamLeaderId;
        mFmeiId = fmeiId;
        mFmeiTitle = fmeiTitle;
        mIPRMax = 0;
        mProcessusList = new ArrayList<>();
    }

    /**
     *  GETTER
     */

    public String getFmeiTitle() {
        return mFmeiTitle;
    }
    public String getTeamLeader() {
        return mTeamLeader;
    }
    public int getParticipantNumber() {
        return mParticipantNumber;
    }
    public int getRiskAmount() {
        return mRiskAmount;
    }
    public double getRiskRateAverage() {
        return mRiskRateAverage;
    }
    public int getIPRMax() {
        return mIPRMax;
    }
    public int getRiskAmountHighLevel() {
        return mRiskAmountHighLevel;
    }
    public int getRiskAmountMiddleLevel() {
        return mRiskAmountMiddleLevel;
    }
    public int getRiskAmountLowLevel() {
        return mRiskAmountLowLevel;
    }
    public long getFmeiId() {
        return mFmeiId;
    }
    public List<Processus> getProcessusList() {
        return mProcessusList;
    }
    public long getTeamLeaderId() {
        return mTeamLeaderId;
    }

    /**
     *  SETTER
     */

    public void setFmeiTitle(String fmeiTitle) {
        mFmeiTitle = fmeiTitle;
    }
    public void setTeamLeader(String teamLeader) {
        mTeamLeader = teamLeader;
    }
    public void setParticipantNumber(int participantNumber) {
        mParticipantNumber = participantNumber;
    }
    public void setRiskAmount(int riskAmount) {
        mRiskAmount = riskAmount;
    }
    public void setRiskRateAverage(double riskRateAverage) {
        mRiskRateAverage = riskRateAverage;
    }
    public void setIPRMax(int IPRMax) {
        mIPRMax = IPRMax;
    }
    public void setRiskAmountHighLevel(int riskAmountHighLevel) {
        mRiskAmountHighLevel = riskAmountHighLevel;
    }
    public void setRiskAmountMiddleLevel(int riskAmountMiddleLevel) {
        mRiskAmountMiddleLevel = riskAmountMiddleLevel;
    }
    public void setRiskAmountLowLevel(int riskAmountLowLevel) {
        mRiskAmountLowLevel = riskAmountLowLevel;
    }
    public void setFmeiId(long fmeiId) {
        mFmeiId = fmeiId;
    }
    public void setProcessusList(List<Processus> processusList) {
        mProcessusList = processusList;
    }
    public void setTeamLeaderId(long teamLeaderId) {
        mTeamLeaderId = teamLeaderId;
    }
}
