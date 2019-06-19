package com.FMEA.fmeimanager.models;

import com.FMEA.fmeimanager.database.Fmei;
import com.FMEA.fmeimanager.database.Participant;
import com.FMEA.fmeimanager.database.TeamFmei;
import java.util.ArrayList;
import java.util.List;

public class TeamPanelCreator {

    private List<TeamPanel> mTeamPanels;
    private List<Participant> mParticipantList;

    public TeamPanelCreator() {
        mTeamPanels = new ArrayList<>();
    }

    public void setTeamPanels(List<TeamPanel> teamPanels) {
        mTeamPanels = teamPanels;
    }

    //RECORD all participants into TeamPanelCreator
    public void setParticipantList(List<Participant> participantList){
        mParticipantList = participantList;
    }

    public List<Participant> getParticipantList() {
        return mParticipantList;
    }

    public List<TeamPanel> getTeamPanels(){
        return mTeamPanels;
    }

    //RECORD all fmeis into TeamPanelCreator
    public static TeamPanelCreator incubeFmeiIntoTeamPanel(List<TeamPanel> teamPanels, List<Fmei> fmeiList){
        if (fmeiList != null){
            for (int i = 0; i < fmeiList.size(); i++) {
                teamPanels.add(new TeamPanel(fmeiList.get(i).getId(), fmeiList.get(i).getTeamLeader()));
            }
        }
        TeamPanelCreator teamPanelCreator = new TeamPanelCreator();
        teamPanelCreator.setTeamPanels(teamPanels);
        return teamPanelCreator;
    }

    //RECORD all participants into TeamPanelCreator
    public static TeamPanelCreator incubeParticipantIntoTeamPanel(TeamPanelCreator teamPanelCreator, List<Participant> participantList){
        teamPanelCreator.setParticipantList(participantList);
        return teamPanelCreator;
    }

    //CREATE list of TeamFmea
    public static List<TeamPanel> incubeTeamFmeiIntoTeamPanel(List<TeamFmei> teamFmeiList, TeamPanelCreator teamPanelCreator){
        for (int i = 0 ; i < teamPanelCreator.getTeamPanels().size() ; i++){
            List<Participant> participants = new ArrayList<>();
            for (int j = 0 ; j < teamFmeiList.size() ; j++){

                if (teamFmeiList.get(j).getFmeiId() == teamPanelCreator.getTeamPanels().get(i).getFmeaId()){
                    for (int k = 0 ; k < teamPanelCreator.getParticipantList().size() ; k++){
                        if (teamPanelCreator.getParticipantList().get(k).getId() == teamFmeiList.get(j).getParticipantId()){
                            participants.add(teamPanelCreator.getParticipantList().get(k));
                        }
                    }
                }
            }
            teamPanelCreator.getTeamPanels().get(i).setParticipantList(participants);
        }
        return teamPanelCreator.getTeamPanels();
    }

}
