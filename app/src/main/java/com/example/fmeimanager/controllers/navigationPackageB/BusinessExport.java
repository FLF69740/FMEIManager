package com.example.fmeimanager.controllers.navigationPackageB;

import android.content.Context;

import com.example.fmeimanager.R;
import com.example.fmeimanager.database.CorrectiveAction;
import com.example.fmeimanager.database.Fmei;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.Processus;
import com.example.fmeimanager.database.Risk;
import com.example.fmeimanager.database.TeamFmei;

import java.util.List;

public class BusinessExport {

    public static String createReport(Context context, Fmei fmei, List<TeamFmei> teamFmeiList, List<Participant> participants,
                                      List<Processus> processusList, List<Risk> risks, List<CorrectiveAction> correctiveActionList){
        StringBuilder builder = new StringBuilder();

        builder.append(getLineOne(context, fmei)).append("\n\n\n")
                .append(";").append(context.getString(R.string.profile_section_team_leader_mention)).append(" :\n")
                .append(getLineTwo(fmei, participants)).append("\n\n")
                .append(";").append(context.getString(R.string.team_fmei_dashboard_title)).append(" :\n")
                .append(getLinesTeam(fmei, teamFmeiList, participants)).append("\n\n")
                .append(getTitleTable(context)).append("\n\n")
                .append(getBody(participants,processusList,risks,correctiveActionList));

        return builder.toString();
    }

    // "FMEA X"
    private static String getLineOne(Context context, Fmei fmei){
        return context.getString(R.string.profile_section_fmea) + " " + fmei.getName();
    }

    // ";team leader name"
    public static String getLineTwo(Fmei fmei, List<Participant> participants){
        String result = "";
        for (Participant participant : participants){
            if (participant.getId() == fmei.getTeamLeader()) result = participant.getForname() + " " + participant.getName();
        }
        return ";" + result;
    }

    // List ";Mr Doe;job"
    public static String getLinesTeam(Fmei fmei, List<TeamFmei> teamFmeiList, List<Participant> participants){
        StringBuilder builder = new StringBuilder();
        for (TeamFmei teamPage : teamFmeiList){
            for (Participant participant : participants){
                if (teamPage.getParticipantId() == participant.getId() && fmei.getId() == teamPage.getFmeiId()){
                    builder.append(";").append(participant.getForname()).append(" ").append(participant.getName()).append(";").append(participant.getFunction()).append("\n");
                }
            }
        }
        return builder.toString();
    }

    // Title TABLE
    private static String getTitleTable(Context context){
        StringBuilder builder = new StringBuilder();
        builder.append(context.getString(R.string.Process_dashboard_title)).append(";").append(context.getString(R.string.main_manager)).append(";")
                .append(context.getString(R.string.Risk_file_corrective_part)).append(";").append(context.getString(R.string.Risk_file_corrective_identification)).append(";")
                .append(context.getString(R.string.Export_Risk_name)).append(";").append(context.getString(R.string.Export_Risk_effect)).append(";")
                .append(context.getString(R.string.Export_Risk_Date)).append(";").append(context.getString(R.string.Export_Risk_Root_cause)).append(";")
                .append(context.getString(R.string.Export_verification_tool)).append(";").append(context.getString(R.string.Risk_file_corrective_severity)).append(";")
                .append(context.getString(R.string.Risk_file_corrective_probability)).append(";").append(context.getString(R.string.Risk_file_corrective_detection)).append(";")
                .append(context.getString(R.string.Risk_file_corrective_ipr_level)).append(";").append(context.getString(R.string.Risk_file_mail_message_line_three)).append(";")
                .append(context.getString(R.string.Export_deadline)).append(";").append(context.getString(R.string.main_manager)).append(";")
                .append(context.getString(R.string.Risk_file_corrective_part)).append(";").append(context.getString(R.string.Risk_file_corrective_identification)).append(";")
                .append(context.getString(R.string.Export_action_realised)).append(";").append(context.getString(R.string.Export_new_score)).append(";")
                .append(context.getString(R.string.Risk_file_corrective_severity)).append(";").append(context.getString(R.string.Risk_file_corrective_probability)).append(";")
                .append(";").append(context.getString(R.string.Risk_file_corrective_detection)).append(";").append(context.getString(R.string.Risk_file_corrective_ipr_level));
        return builder.toString();
    }

    // BODY REPORT
    public static String getBody(List<Participant> participants, List<Processus> processusList, List<Risk> risks, List<CorrectiveAction> correctiveActionList){
        StringBuilder builder = new StringBuilder();
        for (Processus processus : processusList){
            for (Risk risk : risks){
                if (risk.getProcessusId() == processus.getId()){
                    builder.append(processus.getName()).append(";").append(getManagerName(risk.getParticipantId(), participants)).append(";")
                            .append(risk.getParts()).append(";").append(risk.getIdentification()).append(";")
                            .append(risk.getRisk()).append(";").append(risk.getRiskEffect()).append(";")
                            .append(risk.getCreationDate()).append(";").append(risk.getPotentialCause()).append(";")
                            .append(risk.getVerification()).append(";").append(risk.getGravity()).append(";")
                            .append(risk.getFrequencies()).append(";").append(risk.getDetectability()).append(";")
                            .append(getIPR(risk.getGravity(), risk.getFrequencies(), risk.getDetectability())).append(";")
                            .append(getCorrectiveInformations(risk, correctiveActionList, participants)).append("\n");
                }
            }
        }
        return builder.toString();
    }

    // GET participant identity for BODY REPORT
    public static String getManagerName(long id, List<Participant> participants){
        String result = "";
        for (Participant participant : participants){
            if (participant.getId() == id) result = participant.getForname() + " " + participant.getName();
        }
        return result;
    }

    // GET IPR for BODY REPORT
    public static String getIPR(int g, int f, int d){
        return String.valueOf(g*d*f);
    }

    // GET Corrective action for target Risk for BODY REPORT
    public static String getCorrectiveInformations(Risk risk, List<CorrectiveAction> correctiveActionList, List<Participant> participants){
        StringBuilder builder = new StringBuilder();
        for (CorrectiveAction correctiveAction : correctiveActionList){
            if (correctiveAction.getRiskId() == risk.getId()){
                builder.append(correctiveAction.getCorrectiveAction()).append(";").append(correctiveAction.getDeadLineDate()).append(";")
                        .append(getManagerName(correctiveAction.getParticipantId(), participants)).append(";")
                        .append(correctiveAction.getParts()).append(";").append(correctiveAction.getIdentification()).append(";")
                        .append(correctiveAction.getCorrectiveDescription()).append(";").append(correctiveAction.getRealisationDate()).append(";")
                        .append(correctiveAction.getNewGravity()).append(";").append(correctiveAction.getNewFrequencies()).append(";")
                        .append(correctiveAction.getNewDetectability()).append(";")
                        .append(getIPR(correctiveAction.getNewDetectability(), correctiveAction.getNewFrequencies(), correctiveAction.getNewGravity()));
            }
        }
        return builder.toString();
    }
}
