package com.example.fmeimanager.models;



import android.util.Log;

import com.example.fmeimanager.controllers.navigationPackage1.processusTheme.BusinnessProcessusTheme;
import com.example.fmeimanager.database.CorrectiveAction;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.Processus;
import com.example.fmeimanager.database.Risk;
import com.example.fmeimanager.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ProcessusPanelCreator {

    private List<ProcessusPanel> mProcessusPanels;
    private List<Processus> mProcessusList;

    public ProcessusPanelCreator() {
        mProcessusPanels = new ArrayList<>();
        mProcessusList = new ArrayList<>();
    }

    //RECORD all processus about the FMEI Id
    public void updateProcessusList(List<Processus> processuses){
        if (processuses != null && processuses.size() != 0){
            mProcessusList = BusinnessProcessusTheme.getProcessusByStepLadder(processuses);
        }
    }

    //RECORD all risk INTO PANEL
    public void updateRiskList(List<Risk> risks){
        if (risks != null) {
    //        mProcessusPanels = new ArrayList<>();

            for (int i = 0 ; i < mProcessusList.size() ; i++){
                mProcessusPanels.add(BusinnessProcessusTheme.incubeProcessusintoPanel(mProcessusList.get(i), true));

                Log.i(Utils.INFORMATION_LOG, mProcessusPanels.get(
                        mProcessusPanels.size()-1).getProcessusName() + " " + String.valueOf(mProcessusPanels.get(mProcessusPanels.size()-1).isATittle()));

                for (int j = 0 ; j < risks.size() ; j++) {

                    if (risks.get(j).getProcessusId() == mProcessusList.get(i).getId()) {

                        mProcessusPanels.add(BusinnessProcessusTheme.incubeRiskIntoPanel(
                                BusinnessProcessusTheme.incubeProcessusintoPanel(mProcessusList.get(i),false),
                                risks.get(j)));
                    }
                }
            }
        }
    }

    //RECORD all corrective action INTO PANEL
    public void updateCorrectiveActionList(List<CorrectiveAction> correctiveActions){
        if (correctiveActions != null) {
            for (int i = 0 ; i < mProcessusPanels.size() ; i++){
                for (int j = 0 ; j < correctiveActions.size() ; j++){
                    if (correctiveActions.get(j).getRiskId() == mProcessusPanels.get(i).getRiskId()){
                        mProcessusPanels.get(i).setCorrectiveIndicator(correctiveActions.get(j).getNewGravity() *
                                correctiveActions.get(j).getNewDetectability() * correctiveActions.get(j).getNewFrequencies());
                        mProcessusPanels.get(i).setCorrectiveEditFull(true);
                    }
                }
            }
        }
    }

    //RECORD participant INTO PANEL
    public void updateParticipantList(List<Participant> participants){
        if (participants != null){
            for (int i = 0 ; i < mProcessusPanels.size() ; i++){
                for (int j = 0 ; j < participants.size() ; j++){
                    if (participants.get(j).getId() == mProcessusPanels.get(i).getResponsableRiskId()){
                        mProcessusPanels.get(i).setResponsableRisk(participants.get(j).getName());
                        mProcessusPanels.get(i).setParticipantEditFull(true);
                    }
                }
            }
         //   this.updateRecycler(mProcessusPanels);
        }
    }

    public void clear(){
        mProcessusPanels.clear();
    }

    public List<ProcessusPanel> getProcessusPanels(){
        return mProcessusPanels;
    }




}
