package com.FMEA.fmeimanager.models;

import com.FMEA.fmeimanager.controllers.navigationPackageA.fmeiTheme.BusinessFmeaTheme;
import com.FMEA.fmeimanager.database.Fmei;
import com.FMEA.fmeimanager.database.Participant;
import com.FMEA.fmeimanager.database.Processus;
import com.FMEA.fmeimanager.database.Risk;
import com.FMEA.fmeimanager.database.TeamFmei;

import java.util.ArrayList;
import java.util.List;

public class FmeiPanelCreator {

    private List<FmeiPanel> mFmeiPanels;

    public FmeiPanelCreator() {
        mFmeiPanels = new ArrayList<>();
    }

    public void setFmeiPanelsWithFmeiLivedata(List<FmeiPanel> fmeiPanels){
        if (fmeiPanels != null){
            mFmeiPanels.clear();
            mFmeiPanels.addAll(fmeiPanels);
        }
    }

    //GET fmeis number
    public int getFmeiListSize(){
        return mFmeiPanels.size();
    }

    //return panels
    public List<FmeiPanel> getFmeiPanels(){
        return mFmeiPanels;
    }

    //clear panels
    public void clear(){mFmeiPanels.clear();}

    //include fmea into panels
    public static List<FmeiPanel> incubeFmeaIntoPanel(List<FmeiPanel> panels, List<Fmei> fmeiList){
        if (!panels.isEmpty()){
            panels.clear();
        }
        if (fmeiList != null) {
            for (Fmei fmei : fmeiList) {
                panels.add(new FmeiPanel(fmei.getName(), fmei.getId(), fmei.getTeamLeader()));
            }
        }
        return panels;
    }

    //incube processus into panels
    public static List<FmeiPanel> incubeProcessusIntoPanel(List<FmeiPanel> panels, List<Processus> processusList){
        if (processusList != null) {
            for (int i = 0; i < panels.size(); i++) {
                List<Processus> listProcessusBuild = new ArrayList<>();
                for (int j = 0; j < processusList.size(); j++) {
                    if (processusList.get(j).getFmeiId() == panels.get(i).getFmeiId() && processusList.get(j).isVisible()) {
                        listProcessusBuild.add(processusList.get(j));
                    }
                }
                panels.get(i).setProcessusList(listProcessusBuild);
            }
        }
        return panels;
    }

    //incube risks into panels
    public static List<FmeiPanel> incubeRiskIntoPanel(List<FmeiPanel> panels, List<Risk> risks, int high, int medium, int low){
        if (risks != null) {
            for (int i = 0; i < panels.size(); i++) {
                List<Integer> riskAverageDatas = new ArrayList<>();
                int qty = 0;
                for (int j = 0; j < panels.get(i).getProcessusList().size(); j++) {
                    for (int k = 0; k < risks.size(); k++) {
                        if (panels.get(i).getProcessusList().get(j).getId() == risks.get(k).getProcessusId()) {
                            qty++;
                            riskAverageDatas.add(risks.get(k).getGravity() * risks.get(k).getFrequencies() * risks.get(k).getDetectability());
                            panels.get(i).setIPRMax(BusinessFmeaTheme.getIprMax(panels.get(i).getIPRMax(),
                                    risks.get(k).getGravity() * risks.get(k).getFrequencies() * risks.get(k).getDetectability()));
                        }
                    }
                }
                panels.get(i).setRiskAmount(qty);
                panels.get(i).setRiskRateAverage(BusinessFmeaTheme.getRiskAverageIPR(riskAverageDatas));
                panels.get(i).setRiskAmountHighLevel(BusinessFmeaTheme.getHighRiskLevelAmount(riskAverageDatas, high));
                panels.get(i).setRiskAmountMiddleLevel(BusinessFmeaTheme.getMediumRiskLevelAmount(riskAverageDatas, high, medium));
                panels.get(i).setRiskAmountLowLevel(BusinessFmeaTheme.getLowRiskLevelAmount(riskAverageDatas, medium, low));
            }
        }
        return panels;
    }

    //incube teamFmea into panels
    public static List<FmeiPanel> incubeTeamFmeaIntoPanel(List<FmeiPanel> panels, List<TeamFmei> teams){
        if (teams != null) {
            for (int i = 0; i < panels.size(); i++) {
                int qty = 0;
                for (int j = 0; j < teams.size(); j++) {
                    if (teams.get(j).getFmeiId() == panels.get(i).getFmeiId()) {
                        qty++;
                    }
                }
                panels.get(i).setParticipantNumber(qty);
            }
        }
        return panels;
    }

    //incube participant into panels
    public static List<FmeiPanel> incubeParticipantIntoPanel(List<FmeiPanel> panels, List<Participant> participants){
        if (participants != null){
            for (int i = 0 ; i < panels.size() ; i++){
                for (int j = 0 ; j < participants.size() ; j++){
                    if (panels.get(i).getTeamLeaderId() == participants.get(j).getId()){
                        panels.get(i).setTeamLeader(participants.get(j).getForname() + " " + participants.get(j).getName());
                    }
                }
            }
        }
        return panels;
    }







}
