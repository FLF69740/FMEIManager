package com.example.fmeimanager;

import com.example.fmeimanager.database.Fmei;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.Processus;
import com.example.fmeimanager.database.Risk;
import com.example.fmeimanager.database.TeamFmei;
import com.example.fmeimanager.models.FmeiPanel;
import com.example.fmeimanager.models.FmeiPanelCreator;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class ModelFmeiPanelCreatorTest {

    private FmeiPanelCreator getNewFmeiPanelCreator(){return new FmeiPanelCreator(200, 150, 100);}

    //FMEI CONFIGURATION
    private Fmei getFirstFmei(){
        Fmei fmei =  new Fmei("fmei_1",1);
        fmei.setId(1);
        return fmei;
    }
    private Fmei getSecondFmei(){
        Fmei fmei =  new Fmei("fmei_2",1);
        fmei.setId(2);
        return fmei;
    }
    private Fmei getThirdFmei(){
        Fmei fmei =  new Fmei("fmei_3",3);
        fmei.setId(3);
        return fmei;
    }
    private List<Fmei> getNewListOfFmei(){
        List<Fmei> fmeiList = new ArrayList<>();
        fmeiList.add(getFirstFmei());
        fmeiList.add(getSecondFmei());
        fmeiList.add(getThirdFmei());
        return fmeiList;
    }


    //PROCESSUS CONFIGURATION
    private Processus getFirstProcessus(){
        Processus processus = new Processus("processus_1", 1, 1);
        processus.setId(1);
        return processus;
    }
    private Processus getSecondProcessus(){
        Processus processus = new Processus("processus_2", 1, 2);
        processus.setId(2);
        return processus;}
    private Processus getThirdProcessus(){
        Processus processus = new Processus("processus_3", 2, 1);
        processus.setId(3);
        return processus;
    }
    private List<Processus> getNewListOfProcessus(){
        List<Processus> processusList = new ArrayList<>();
        processusList.add(getFirstProcessus());
        processusList.add(getSecondProcessus());
        processusList.add(getThirdProcessus());
        return processusList;
    }


    //RISK CONFIGURATION
    private Risk getFirstRisk(){
        Risk risk = new Risk("12/02/2019", "Parts_One", "100145XD", "risk_one", 1, 1);
        risk.setId(1);
        risk.setGravity(10); risk.setDetectability(10);risk.setFrequencies(10);
        return risk;
    }
    private Risk getSecondRisk(){
        Risk risk = new Risk("18/02/2019", "Parts_One", "100145XD", "risk_one", 1, 1);
        risk.setId(2);
        risk.setGravity(5); risk.setDetectability(5);risk.setFrequencies(5);
        return risk;
    }
    private Risk getThirdRisk(){
        Risk risk = new Risk("19/02/2019", "Parts_One", "100145XD", "risk_one", 1, 1);
        risk.setId(3);
        risk.setGravity(10); risk.setDetectability(10);risk.setFrequencies(10);
        return risk;
    }
    private Risk getFourthRisk(){
        Risk risk = new Risk("14/02/2019", "Parts_One", "100145XD", "risk_one", 2, 1);
        risk.setId(4);
        risk.setGravity(5); risk.setDetectability(5);risk.setFrequencies(5);
        return risk;
    }
    private Risk getFifthRisk(){
        Risk risk = new Risk("11/02/2019", "Parts_One", "100145XD", "risk_one", 3, 1);
        risk.setId(5);
        risk.setGravity(6); risk.setDetectability(6);risk.setFrequencies(5);
        return risk;
    }
    private List<Risk> getNewListOfRisk(){
        List<Risk> riskList = new ArrayList<>();
        riskList.add(getFirstRisk());
        riskList.add(getSecondRisk());
        riskList.add(getThirdRisk());
        riskList.add(getFourthRisk());
        riskList.add(getFifthRisk());
        return riskList;
    }


    //TEAM FMEI CONFIGURATION
    private TeamFmei getFirstTeamFmei(){
        TeamFmei teamFmei = new TeamFmei(1,1);
        teamFmei.setTeamFmeiId(1);
        return teamFmei;
    }
    private TeamFmei getSecondTeamFmei(){
        TeamFmei teamFmei = new TeamFmei(1,2);
        teamFmei.setTeamFmeiId(2);
        return teamFmei;
    }
    private TeamFmei getThirdTeamFmei(){
        TeamFmei teamFmei = new TeamFmei(1,3);
        teamFmei.setTeamFmeiId(3);
        return teamFmei;
    }
    private TeamFmei getFourthTeamFmei(){
        TeamFmei teamFmei = new TeamFmei(2,1);
        teamFmei.setTeamFmeiId(4);
        return teamFmei;
    }
    private TeamFmei getFifthTeamFmei(){
        TeamFmei teamFmei = new TeamFmei(2,2);
        teamFmei.setTeamFmeiId(5);
        return teamFmei;
    }
    private TeamFmei getSixthTeamFmei(){
        TeamFmei teamFmei = new TeamFmei(3,1);
        teamFmei.setTeamFmeiId(6);
        return teamFmei;
    }
    private TeamFmei getSeventhTeamFmei(){
        TeamFmei teamFmei = new TeamFmei(3,2);
        teamFmei.setTeamFmeiId(7);
        return teamFmei;
    }
    private List<TeamFmei> getNewListOfTeamFmei(){
        List<TeamFmei> teamFmeiList = new ArrayList<>();
        teamFmeiList.add(getFirstTeamFmei());
        teamFmeiList.add(getSecondTeamFmei());
        teamFmeiList.add(getThirdTeamFmei());
        teamFmeiList.add(getFourthTeamFmei());
        teamFmeiList.add(getFifthTeamFmei());
        teamFmeiList.add(getSixthTeamFmei());
        teamFmeiList.add(getSeventhTeamFmei());
        return teamFmeiList;
    }


    //PARTICIPANT CONFIGURATION
    private Participant getFirstParticipant(){
        Participant participant = new Participant("DOE", "John");
        participant.setId(1);
        return participant;
    }
    private Participant getSecondParticipant(){
        Participant participant = new Participant("DOE", "Jane");
        participant.setId(2);
        return participant;
    }
    private Participant getThirdParticipant(){
        Participant participant = new Participant("RED", "Axel");
        participant.setId(3);
        return participant;
    }
    private List<Participant> getNewParticipantList(){
        List<Participant> participantList = new ArrayList<>();
        participantList.add(getFirstParticipant());
        participantList.add(getSecondParticipant());
        participantList.add(getThirdParticipant());
        return participantList;
    }



    /**
     *  TEST
     */

    @Test
    public void testIfFmeiListIsUpdated(){
        FmeiPanelCreator fmeiPanelCreator = getNewFmeiPanelCreator();
        List<Fmei> fmeis = getNewListOfFmei();

        fmeiPanelCreator.setFmeiList(fmeis);

        List<FmeiPanel> fmeiPanelList = fmeiPanelCreator.getFmeiPanels();

        assertEquals( "fmei_1", fmeiPanelList.get(0).getFmeiTitle());
        assertEquals( 1, fmeiPanelList.get(0).getTeamLeaderId());
        assertEquals( "fmei_2", fmeiPanelList.get(1).getFmeiTitle());
        assertEquals( 1, fmeiPanelList.get(1).getTeamLeaderId());
        assertEquals( "fmei_3", fmeiPanelList.get(2).getFmeiTitle());
        assertEquals( 3, fmeiPanelList.get(2).getTeamLeaderId());
    }

    @Test
    public void testIfProcessusListIsUpdated(){
        FmeiPanelCreator fmeiPanelCreator = getNewFmeiPanelCreator();
        List<Fmei> fmeis = getNewListOfFmei();
        List<Processus> processuses = getNewListOfProcessus();

        fmeiPanelCreator.setFmeiList(fmeis);
        fmeiPanelCreator.updateProcessusList(processuses);

        List<FmeiPanel> fmeiPanelList = fmeiPanelCreator.getFmeiPanels();

        assertEquals(2, fmeiPanelList.get(0).getProcessusList().size());
        assertEquals(1, fmeiPanelList.get(1).getProcessusList().size());
        assertEquals(0, fmeiPanelList.get(2).getProcessusList().size());
    }

    @Test
    public void testIfRiskListIsUpdated(){
        FmeiPanelCreator fmeiPanelCreator = getNewFmeiPanelCreator();
        List<Fmei> fmeis = getNewListOfFmei();
        List<Processus> processuses = getNewListOfProcessus();
        List<Risk> riskList = getNewListOfRisk();

        fmeiPanelCreator.setFmeiList(fmeis);
        fmeiPanelCreator.updateProcessusList(processuses);
        fmeiPanelCreator.updateRiskList(riskList);

        List<FmeiPanel> fmeiPanelList = fmeiPanelCreator.getFmeiPanels();

        assertEquals(4, fmeiPanelList.get(0).getRiskAmount());
        assertEquals(562.5, fmeiPanelList.get(0).getRiskRateAverage(), 0);
        assertEquals(1000, fmeiPanelList.get(0).getIPRMax());
        assertEquals(2, fmeiPanelList.get(0).getRiskAmountHighLevel());
        assertEquals(0, fmeiPanelList.get(0).getRiskAmountMiddleLevel());
        assertEquals(2, fmeiPanelList.get(0).getRiskAmountLowLevel());

        assertEquals(1, fmeiPanelList.get(1).getRiskAmount());
        assertEquals(180, fmeiPanelList.get(1).getRiskRateAverage(), 0);
        assertEquals(180, fmeiPanelList.get(1).getIPRMax());
        assertEquals(0, fmeiPanelList.get(1).getRiskAmountHighLevel());
        assertEquals(1, fmeiPanelList.get(1).getRiskAmountMiddleLevel());
        assertEquals(0, fmeiPanelList.get(1).getRiskAmountLowLevel());

        assertEquals(0, fmeiPanelList.get(2).getRiskAmount());
        assertEquals(0, fmeiPanelList.get(2).getRiskRateAverage(), 0);
        assertEquals(0, fmeiPanelList.get(2).getIPRMax());
        assertEquals(0, fmeiPanelList.get(2).getRiskAmountHighLevel());
        assertEquals(0, fmeiPanelList.get(2).getRiskAmountMiddleLevel());
        assertEquals(0, fmeiPanelList.get(2).getRiskAmountLowLevel());
    }

    @Test
    public void testIfTeamFmeiListIsUpdated(){
        FmeiPanelCreator fmeiPanelCreator = getNewFmeiPanelCreator();
        List<Fmei> fmeis = getNewListOfFmei();
        List<Processus> processuses = getNewListOfProcessus();
        List<Risk> riskList = getNewListOfRisk();
        List<TeamFmei> teamFmeiList = getNewListOfTeamFmei();

        fmeiPanelCreator.setFmeiList(fmeis);
        fmeiPanelCreator.updateProcessusList(processuses);
        fmeiPanelCreator.updateRiskList(riskList);
        fmeiPanelCreator.updateTeamFmeiList(teamFmeiList);

        List<FmeiPanel> fmeiPanelList = fmeiPanelCreator.getFmeiPanels();

        assertEquals(3, fmeiPanelList.get(0).getParticipantNumber());
        assertEquals(2, fmeiPanelList.get(1).getParticipantNumber());
        assertEquals(2, fmeiPanelList.get(2).getParticipantNumber());
    }

    @Test
    public void testIfParticipantListIsUpdated(){
        FmeiPanelCreator fmeiPanelCreator = getNewFmeiPanelCreator();
        List<Fmei> fmeis = getNewListOfFmei();
        List<Processus> processuses = getNewListOfProcessus();
        List<Risk> riskList = getNewListOfRisk();
        List<TeamFmei> teamFmeiList = getNewListOfTeamFmei();
        List<Participant> participantList = getNewParticipantList();

        fmeiPanelCreator.setFmeiList(fmeis);
        fmeiPanelCreator.updateProcessusList(processuses);
        fmeiPanelCreator.updateRiskList(riskList);
        fmeiPanelCreator.updateTeamFmeiList(teamFmeiList);
        fmeiPanelCreator.updateParticipantList(participantList);

        List<FmeiPanel> fmeiPanelList = fmeiPanelCreator.getFmeiPanels();

        assertEquals("John DOE", fmeiPanelList.get(0).getTeamLeader());
        assertEquals("John DOE", fmeiPanelList.get(1).getTeamLeader());
        assertEquals("Axel RED", fmeiPanelList.get(2).getTeamLeader());

    }


}
