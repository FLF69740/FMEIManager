package com.example.fmeimanager.controllers.navigationPackage1.processusTheme;

import android.util.Log;
import com.example.fmeimanager.database.Processus;
import com.example.fmeimanager.models.ProcessusPanel;
import com.example.fmeimanager.database.Risk;
import com.example.fmeimanager.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class BusinnessProcessusTheme {

    public static List<Processus> getProcessusByStepLadder(List<Processus> processusList){
        boolean checkingEnd = false;

        while (!checkingEnd) {
            checkingEnd = true;
            for (int i = 0; i < processusList.size() - 1; i++) {
                if (processusList.get(i).getStep() > processusList.get(i + 1).getStep()) {
                    processusList.add(i + 1, processusList.remove(i));
                    checkingEnd = false;
                }
            }
        }

        return processusList;
    }

    public static ArrayList<String> getProcessusListId(List<ProcessusPanel> processusPanels){
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0 ; i < processusPanels.size() ; i++){
            if (i == 0){
                result.add(String.valueOf(processusPanels.get(i).getProcessusId()));
            }else if (processusPanels.get(i).getProcessusId() != processusPanels.get(i - 1).getProcessusId()) {
                result.add(String.valueOf(processusPanels.get(i).getProcessusId()));
            }
        }

        Log.i(Utils.INFORMATION_LOG, result.toString());
        return result;
    }

    public static ArrayList<String> getProcessusListStep(List<ProcessusPanel> processusPanels){
        ArrayList<String> result = new ArrayList<>();


        for (int i = 0 ; i < processusPanels.size() ; i++){
            if (i == 0){
                result.add(String.valueOf(processusPanels.get(i).getProcessusStep()));
            }else if (processusPanels.get(i).getProcessusStep() != processusPanels.get(i - 1).getProcessusStep()) {
                result.add(String.valueOf(processusPanels.get(i).getProcessusStep()));
            }
        }

        Log.i(Utils.INFORMATION_LOG, result.toString());
        return result;
    }

    /**
     *  PROCESSUS PANEL
     */

    public static ProcessusPanel incubeProcessusintoPanel(Processus processus, boolean isATitle){
        ProcessusPanel processusPanel = new ProcessusPanel(processus.getId(), processus.getStep(), processus.getName(), isATitle);
        processusPanel.setProcessusVisible(processus.isVisible());
        processusPanel.setProcessusEditFull(true);
        return processusPanel;
    }

    public static ProcessusPanel incubeRiskIntoPanel(ProcessusPanel processusPanel, Risk risk){
        processusPanel.setRiskId((int)risk.getId());
        processusPanel.setTitleRisk(risk.getRisk());
        processusPanel.setCreationDateRisk(risk.getCreationDate());
        processusPanel.setIPR(risk.getGravity() * risk.getDetectability() * risk.getFrequencies());
        processusPanel.setGravivity(risk.getGravity());
        processusPanel.setFrequencies(risk.getFrequencies());
        processusPanel.setDetectability(risk.getDetectability());
        processusPanel.setResponsableRiskId(risk.getParticipantId());
        processusPanel.setRiskEditFull(true);
        return processusPanel;
    }




}
