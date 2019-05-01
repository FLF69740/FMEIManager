package com.example.fmeimanager.models;


import com.example.fmeimanager.database.Fmei;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.Processus;
import com.example.fmeimanager.database.Risk;
import com.example.fmeimanager.database.TeamFmei;

import java.util.ArrayList;
import java.util.List;

public class FmeiPanelCreator {

    private List<FmeiPanel> mFmeiPanels;
    private int mHighRateRiskLevel;
    private int mMediumRateRiskLevel;
    private int mLowRateRiskLevel;

    public FmeiPanelCreator(int highRateRiskLevel, int mediumRateRiskLevel, int lowRateRiskLevel) {
        mHighRateRiskLevel = highRateRiskLevel;
        mMediumRateRiskLevel = mediumRateRiskLevel;
        mLowRateRiskLevel = lowRateRiskLevel;
        mFmeiPanels = new ArrayList<>();
    }

    //RECORD all fmeis with fmei id
    public void setFmeiList(List<Fmei> fmeiList) {
        if (fmeiList != null) {
            for (int i = 0; i < fmeiList.size(); i++) {
                mFmeiPanels.add(incubeFmeiIntoPanel(fmeiList.get(i)));
            }
        }
    }

    //GET fmeis number
    public int getFmeiListSize(){
        return mFmeiPanels.size();
    }

    //RECORD all processus information into panel
    public void updateProcessusList(List<Processus> processusList){
        if (processusList != null){
            for (int i = 0 ; i < mFmeiPanels.size() ; i++){
                List<Processus> listProcessusBuild = new ArrayList<>();
                for (int j = 0 ; j < processusList.size() ; j++){
                    if (processusList.get(j).getFmeiId() == mFmeiPanels.get(i).getFmeiId() && processusList.get(j).isVisible()){
                        listProcessusBuild.add(processusList.get(j));
                    }
                }
                mFmeiPanels.get(i).setProcessusList(listProcessusBuild);
            }
        }
    }

    //RECORD all risk information into panel
    public void updateRiskList(List<Risk> riskList){
        if (riskList != null){
            for (int i = 0 ; i < mFmeiPanels.size() ; i++){
                List<Integer> riskAverageDatas = new ArrayList<>();
                int qty = 0;
                for (int j = 0 ; j < mFmeiPanels.get(i).getProcessusList().size() ; j++){
                    for (int k = 0 ; k < riskList.size() ; k++){
                        if (mFmeiPanels.get(i).getProcessusList().get(j).getId() == riskList.get(k).getProcessusId()){
                            qty++;
                            riskAverageDatas.add(riskList.get(k).getGravity() * riskList.get(k).getFrequencies() * riskList.get(k).getDetectability());
                            mFmeiPanels.get(i).setIPRMax(getIprMax(mFmeiPanels.get(i).getIPRMax(),
                                    riskList.get(k).getGravity() * riskList.get(k).getFrequencies() * riskList.get(k).getDetectability()));
                        }
                    }
                }
                mFmeiPanels.get(i).setRiskAmount(qty);
                mFmeiPanels.get(i).setRiskRateAverage(getRiskAverageIPR(riskAverageDatas));
                mFmeiPanels.get(i).setRiskAmountHighLevel(getHighRiskLevelAmount(riskAverageDatas));
                mFmeiPanels.get(i).setRiskAmountMiddleLevel(getMediumRiskLevelAmount(riskAverageDatas));
                mFmeiPanels.get(i).setRiskAmountLowLevel(getLowRiskLevelAmount(riskAverageDatas));
            }
        }
    }

    //RECORD all team fmei information into panel
    public void updateTeamFmeiList(List<TeamFmei> teams){
        if (teams != null){
            for (int i = 0 ; i < mFmeiPanels.size() ; i++){
                int qty = 0;
                for (int j = 0 ; j < teams.size() ; j++){
                    if (teams.get(j).getFmeiId() == mFmeiPanels.get(i).getFmeiId()){
                        qty++;
                    }
                }
                mFmeiPanels.get(i).setParticipantNumber(qty);
            }
        }
    }

    //RECORD all participant information into panel
    public void updateParticipantList(List<Participant> participants){
        if (participants != null){
            for (int i = 0 ; i < mFmeiPanels.size() ; i++){
                for (int j = 0 ; j < participants.size() ; j++){
                    if (mFmeiPanels.get(i).getTeamLeaderId() == participants.get(j).getId()){
                        mFmeiPanels.get(i).setTeamLeader(participants.get(j).getForname() + " " + participants.get(j).getName());
                    }
                }
            }
        }
    }

    public void clear(){
        mFmeiPanels.clear();
    }

    public List<FmeiPanel> getFmeiPanels(){
        return mFmeiPanels;
    }

    //RECORD all fmeis into panel
    private FmeiPanel incubeFmeiIntoPanel(Fmei fmei){
        return new FmeiPanel(fmei.getName(), fmei.getId(), fmei.getTeamLeader());
    }

    //GET IPR average for a panel
    private double getRiskAverageIPR(List<Integer> IPRs){
        double result = 0;
        if (IPRs.size() != 0) {
            for (int i = 0; i < IPRs.size(); i++) {
                result += IPRs.get(i);
            }
            return result / IPRs.size();
        }else {
            return 0;
        }
    }

    //GET IPR MAX for a panel
    private int getIprMax(int max, int dataProposition){
        return max > dataProposition ? max : dataProposition;
    }

    //GET Number of risk high level
    private int getHighRiskLevelAmount(List<Integer> IPRs){
        int result = 0;
        if (IPRs.size() != 0) {
            for (int i = 0; i < IPRs.size(); i++) {
                if (IPRs.get(i) >= mHighRateRiskLevel) {
                    result++;
                }
            }
            return result;
        }else {
            return 0;
        }
    }

    //GET Number of risk high level
    private int getMediumRiskLevelAmount(List<Integer> IPRs){
        int result = 0;
        if (IPRs.size() != 0) {
            for (int i = 0; i < IPRs.size(); i++) {
                if (IPRs.get(i) < mHighRateRiskLevel && IPRs.get(i) >= mMediumRateRiskLevel) {
                    result++;
                }
            }
            return result;
        }else {
            return 0;
        }
    }

    //GET Number of risk high level
    private int getLowRiskLevelAmount(List<Integer> IPRs){
        int result = 0;
        if (IPRs.size() != 0) {
            for (int i = 0; i < IPRs.size(); i++) {
                if (IPRs.get(i) < mMediumRateRiskLevel && IPRs.get(i) >= mLowRateRiskLevel) {
                    result++;
                }
            }
            return result;
        }else {
            return 0;
        }
    }
}
