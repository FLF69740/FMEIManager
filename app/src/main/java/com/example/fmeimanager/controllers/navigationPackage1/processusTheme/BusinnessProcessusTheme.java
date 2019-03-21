package com.example.fmeimanager.controllers.navigationPackage1.processusTheme;

import android.util.Log;

import com.example.fmeimanager.models.CorrectiveAction;
import com.example.fmeimanager.models.Processus;
import com.example.fmeimanager.models.ProcessusPanel;
import com.example.fmeimanager.models.Risk;
import com.example.fmeimanager.utils.Utils;

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

    public static String getProcessusListId(List<Processus> processusList){
        StringBuilder result = new StringBuilder();

        for (int i = 0 ; i < processusList.size() ; i++){
            result.append(processusList.get(i).getStep() + "/");
        }

        Log.i(Utils.INFORMATION_LOG, result.toString());
        return result.toString();
    }

    /**
     *  PROCESSUS PANEL
     */

    public static ProcessusPanel incubeProcessusintoPanel(Processus processus, boolean isATitle){
        ProcessusPanel processusPanel = new ProcessusPanel(processus.getStep(), processus.getName(), isATitle);
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
