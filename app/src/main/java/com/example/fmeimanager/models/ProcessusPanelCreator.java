package com.example.fmeimanager.models;

import com.example.fmeimanager.controllers.navigationPackageA.processusTheme.BusinnessProcessusTheme;
import com.example.fmeimanager.database.CorrectiveAction;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.Processus;
import com.example.fmeimanager.database.Risk;
import java.util.ArrayList;
import java.util.List;

public class ProcessusPanelCreator {

    private List<ProcessusPanel> mProcessusPanels;
    private List<Processus> mProcessusList;

    public ProcessusPanelCreator() {
        mProcessusPanels = new ArrayList<>();
        mProcessusList = new ArrayList<>();
    }

    public void setProcessusList(List<Processus> processusList) {
        mProcessusList = processusList;
    }

    public void setProcessusPanels(List<ProcessusPanel> panels){
        mProcessusPanels = panels;
    }

    public List<Processus> getProcessusList() {
        return mProcessusList;
    }

    //RECORD all processus INTO PANEL CREATOR
    public static ProcessusPanelCreator createCreator(List<Processus> processusList, ProcessusPanelCreator panelCreator){
        if (processusList != null && processusList.size() != 0){
            panelCreator.setProcessusList(BusinnessProcessusTheme.getProcessusByStepLadder(processusList));
        }
        return panelCreator;
    }

    //RECORD all risk INTO PANEL
    public static List<ProcessusPanel> incubeRiskIntoPanel(List<ProcessusPanel> processusPanels, List<Processus> processusList, List<Risk> risks){
        if (!processusPanels.isEmpty()){
            processusPanels.clear();
        }
        if (risks != null) {
            for (int i = 0; i < processusList.size() ; i++){
                processusPanels.add(BusinnessProcessusTheme.incubeProcessusintoPanel(processusList.get(i), true));
                for (int j = 0 ; j < risks.size() ; j++) {
                    if (risks.get(j).getProcessusId() == processusList.get(i).getId()) {
                        processusPanels.add(BusinnessProcessusTheme.incubeRiskIntoPanel(
                                BusinnessProcessusTheme.incubeProcessusintoPanel(processusList.get(i),false),
                                risks.get(j)));
                    }
                }
            }
        }
        return processusPanels;
    }

    //RECORD all corrective action INTO PANEL
    public static List<ProcessusPanel> incubeCorrectiveIntoPanel(List<ProcessusPanel> processusPanels, List<CorrectiveAction> correctiveActions){
        if (correctiveActions != null) {
            for (int i = 0 ; i < processusPanels.size() ; i++){
                for (int j = 0 ; j < correctiveActions.size() ; j++){
                    if (correctiveActions.get(j).getRiskId() == processusPanels.get(i).getRiskId()){
                        processusPanels.get(i).setCorrectiveIndicator(correctiveActions.get(j).getNewGravity() *
                                correctiveActions.get(j).getNewDetectability() * correctiveActions.get(j).getNewFrequencies());
                        processusPanels.get(i).setCorrectiveEditFull(true);
                    }
                }
            }
        }
        return processusPanels;
    }

    //RECORD participant INTO PANEL
    public static List<ProcessusPanel> incubeParticipantIntoPanel(List<ProcessusPanel> processusPanels, List<Participant> participants){
        if (participants != null){
            for (int i = 0 ; i < processusPanels.size() ; i++){
                for (int j = 0 ; j < participants.size() ; j++){
                    if (participants.get(j).getId() == processusPanels.get(i).getResponsableRiskId()){
                        processusPanels.get(i).setResponsableRisk(participants.get(j).getForname() + " " + participants.get(j).getName());
                        processusPanels.get(i).setParticipantEditFull(true);
                    }
                }
            }
        }
        return processusPanels;
    }

    public List<ProcessusPanel> getProcessusPanels(){
        return mProcessusPanels;
    }
}