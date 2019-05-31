package com.example.fmeimanager;

import com.example.fmeimanager.controllers.navigationPackageF.BusinnessTeamFmei;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.TeamFmei;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class businessTeamFmeiTest {

    @Test
    public void testAddition(){
        assertEquals(2, 1+1);
    }

    private Participant getParticipantOne(){
        Participant participant = new Participant();
        participant.setName("RED");
        participant.setForname("Axel");
        participant.setId(10);
        return participant;
    }

    private Participant getParticipantTwo(){
        Participant participant = new Participant();
        participant.setName("MARTIN");
        participant.setForname("John");
        participant.setId(1);
        return participant;
    }

    private Participant getParticipantThree(){
        Participant participant = new Participant();
        participant.setName("DUPONT");
        participant.setForname("Henry");
        participant.setId(5);
        return participant;
    }

    private ArrayList<String> getFirstList(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1"); arrayList.add("2"); arrayList.add("5"); arrayList.add("12");
        return arrayList;
    }

    private ArrayList<String> getSecondList(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1"); arrayList.add("2"); arrayList.add("3"); arrayList.add("4");
        arrayList.add("6"); arrayList.add("7"); arrayList.add("8");
        arrayList.add("9"); arrayList.add("11"); arrayList.add("12"); arrayList.add("13");
        return arrayList;
    }

    @Test
    public void testIfAxelIsAdded(){
        Participant axel = getParticipantOne();
        ArrayList<String> fullList = getFirstList();

        fullList = BusinnessTeamFmei.participantListRealoaded(axel, fullList, 1);

        assertEquals(5, fullList.size());
        assertEquals("10", fullList.get(4));
    }

    @Test
    public void testIfHenryIsOut(){
        Participant henry = getParticipantThree();
        ArrayList<String> fullList = getFirstList();

        fullList = BusinnessTeamFmei.participantListRealoaded(henry, fullList, 1);

        assertEquals(3, fullList.size());
    }

    @Test
    public void testIfJohnCantBeDesactivatedAsParticipant(){
        Participant john = getParticipantTwo();
        ArrayList<String> fullList = getFirstList();

        fullList = BusinnessTeamFmei.participantListRealoaded(john, fullList, 1);

        assertEquals(4, fullList.size());
    }

    @Test
    public void testIfHenryBecomeTeamLeader(){
        Participant henry = getParticipantThree();
        ArrayList<String> fullList = getFirstList();

        long teamLeaderId = 1;

        teamLeaderId = BusinnessTeamFmei.teamLeaderIdRealoaded(henry, fullList, teamLeaderId);

        assertEquals(5, teamLeaderId);

    }

    @Test
    public void testIfAxelCantBeTeamLeader(){
        Participant axel = getParticipantOne();
        ArrayList<String> fullList = getFirstList();

        long teamLeaderId = 1;

        teamLeaderId = BusinnessTeamFmei.teamLeaderIdRealoaded(axel, fullList, teamLeaderId);

        assertEquals(0, teamLeaderId);
    }

    @Test
    public void testIfThirtyParticipantAreImpossible(){
        Participant axel = getParticipantOne();
        Participant henry = getParticipantThree();
        ArrayList<String> fullList = getSecondList();

        fullList = BusinnessTeamFmei.participantListRealoaded(henry, fullList, 1);

        assertEquals(12, fullList.size());

        fullList = BusinnessTeamFmei.participantListRealoaded(axel, fullList,1);

        assertEquals(12, fullList.size());

    }

    /**
     *  TEAM FMEI ID
     */

    private TeamFmei getTeamFmeaFile_one(){
        TeamFmei teamFmei = new TeamFmei(1, 1);
        teamFmei.setTeamFmeiId(1);
        return teamFmei;
    }

    private TeamFmei getTeamFmeaFile_two(){
        TeamFmei teamFmei = new TeamFmei(1, 3);
        teamFmei.setTeamFmeiId(2);
        return teamFmei;
    }

    private TeamFmei getTeamFmeaFile_three(){
        TeamFmei teamFmei = new TeamFmei(1, 4);
        teamFmei.setTeamFmeiId(3);
        return teamFmei;
    }

    private TeamFmei getTeamFmeaFile_four(){
        TeamFmei teamFmei = new TeamFmei(1, 7);
        teamFmei.setTeamFmeiId(4);
        return teamFmei;
    }

    private TeamFmei getTeamFmeaFile_five(){
        TeamFmei teamFmei = new TeamFmei(2, 1);
        teamFmei.setTeamFmeiId(5);
        return teamFmei;    }

    private TeamFmei getTeamFmeaFile_six(){
        TeamFmei teamFmei = new TeamFmei(2, 4);
        teamFmei.setTeamFmeiId(6);
        return teamFmei;
    }

    private List<TeamFmei> getListTeamFmea(){
        List<TeamFmei> teamFmei = new ArrayList<>();
        teamFmei.add(getTeamFmeaFile_one());
        teamFmei.add(getTeamFmeaFile_two());
        teamFmei.add(getTeamFmeaFile_three());
        teamFmei.add(getTeamFmeaFile_four());
        teamFmei.add(getTeamFmeaFile_five());
        teamFmei.add(getTeamFmeaFile_six());
        return teamFmei;
    }

    @Test
    public void testIfGoodFmeaIsChosen(){
        List<TeamFmei> teamFmeiList = getListTeamFmea();

        long fmeaId = 2;
        long participantId = 1;
        long answer = 0;

        for (TeamFmei teamFmei : teamFmeiList){
            if (teamFmei.getFmeiId() == fmeaId && teamFmei.getParticipantId() == participantId){
                answer = teamFmei.getTeamFmeiId();
            }
        }

        assertEquals(5, answer);
    }

    //BusinnesTeamFmei : getNumberOfPages
    @Test
    public void testIfGoodNumberOfPages(){
        int participantNumber = 19;
        int pages = BusinnessTeamFmei.getNumberOfPages(participantNumber);
        assertEquals(3, pages);
    }













}
