package com.FMEA.fmeimanager;


import com.FMEA.fmeimanager.controllers.navigationPackageB.BusinessExport;
import com.FMEA.fmeimanager.database.CorrectiveAction;
import com.FMEA.fmeimanager.database.Fmei;
import com.FMEA.fmeimanager.database.Participant;
import com.FMEA.fmeimanager.database.Processus;
import com.FMEA.fmeimanager.database.Risk;
import com.FMEA.fmeimanager.database.TeamFmei;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class BusinessExportTest {

    @Test
    public void testAddition(){
        assertEquals(2, 1+1);
    }

    private static final String SEPARATOR = ";";
    private static final String PARTICIPANT_NAME_1 = "DOE";
    private static final String PARTICIPANT_NAME_2 = "DUPONT";
    private static final String PARTICIPANT_NAME_3 = "ALIAS";
    private static final String PARTICIPANT_NAME_4 = "TATA";
    private static final String PARTICIPANT_NAME_5 = "FRIGO";
    private static final String PARTICIPANT_FORNAME_1 = "John";
    private static final String PARTICIPANT_FORNAME_2 = "Martin";
    private static final String PARTICIPANT_FORNAME_3 = "Kévin";
    private static final String PARTICIPANT_FORNAME_4 = "Francesca";
    private static final String PARTICIPANT_FORNAME_5 = "Léon";
    private static final String PARTICIPANT_1 = PARTICIPANT_FORNAME_1 + " " + PARTICIPANT_NAME_1;
    private static final String PARTICIPANT_2 = PARTICIPANT_FORNAME_2 + " " + PARTICIPANT_NAME_2;
    private static final String PARTICIPANT_3 = PARTICIPANT_FORNAME_3 + " " + PARTICIPANT_NAME_3;
    private static final String PARTICIPANT_4 = PARTICIPANT_FORNAME_4 + " " + PARTICIPANT_NAME_4;
    private static final String PARTICIPANT_5 = PARTICIPANT_FORNAME_5 + " " + PARTICIPANT_NAME_5;
    private static final String PARTICIPANT_JOB_1 = "PARTICIPANT_JOB_1";
    private static final String PARTICIPANT_JOB_2 = "PARTICIPANT_JOB_2";
    private static final String PARTICIPANT_JOB_3 = "PARTICIPANT_JOB_3";
    private static final String PARTICIPANT_JOB_4 = "PARTICIPANT_JOB_4";
    private static final String PARTICIPANT_JOB_5 = "PARTICIPANT_JOB_5";
    private static final String FMEA_NAME_1 = "FMEA_NAME_1";
    private static final String FMEA_NAME_2 = "FMEA_NAME_2";
    private static final String PROCESSUS_NAME_1 = "PROCESSUS_NAME_1";
    private static final String PROCESSUS_NAME_2 = "PROCESSUS_NAME_2";
    private static final String PROCESSUS_NAME_3 = "PROCESSUS_NAME_3";
    private static final String PROCESSUS_NAME_4 = "PROCESSUS_NAME_4";
    private static final String PROCESSUS_NAME_5 = "PROCESSUS_NAME_5";
    private static final String PROCESSUS_NAME_6 = "PROCESSUS_NAME_6";
    private static final String DATE_1 = "12/01/2000";
    private static final String DATE_2 = "23/01/2000";
    private static final String DATE_3 = "12/02/2000";
    private static final String DATE_4 = "18/03/2000";
    private static final String PART_1 = "PART_1";
    private static final String PART_2 = "PART_2";
    private static final String IDENTIFICATION_1 = "IDENTIFICATION_1";
    private static final String IDENTIFICATION_2 = "IDENTIFICATION_2";
    private static final String RISK_NAME_1 = "RISK_NAME_1";
    private static final String RISK_NAME_2 = "RISK_NAME_2";
    private static final String RISK_NAME_3 = "RISK_NAME_3";
    private static final String RISK_NAME_4 = "RISK_NAME_4";
    private static final String RISK_NAME_5 = "RISK_NAME_5";
    private static final String RISK_NAME_6 = "RISK_NAME_6";
    private static final String RISK_NAME_7 = "RISK_NAME_7";
    private static final String RISK_NAME_8 = "RISK_NAME_8";
    private static final String CORRECTIVE_1 = "CORRECTIVE_1";
    private static final String CORRECTIVE_2 = "CORRECTIVE_2";
    private static final String CORRECTIVE_3 = "CORRECTIVE_3";
    private static final String CORRECTIVE_4 = "CORRECTIVE_4";
    private static final String CORRECTIVE_5 = "CORRECTIVE_5";

    /**
     *  OBJECT
     */

    //*****     PARTICIPANT     *****

    private static Participant getParticipant_one(){
        Participant participant = new Participant();
        participant.setName(PARTICIPANT_NAME_1);
        participant.setForname(PARTICIPANT_FORNAME_1);
        participant.setId(1);
        participant.setFunction(PARTICIPANT_JOB_1);
        return participant;
    }

    private static Participant getParticipant_two(){
        Participant participant = new Participant();
        participant.setName(PARTICIPANT_NAME_2);
        participant.setForname(PARTICIPANT_FORNAME_2);
        participant.setId(2);
        participant.setFunction(PARTICIPANT_JOB_2);
        return participant;
    }

    private static Participant getParticipant_three(){
        Participant participant = new Participant();
        participant.setName(PARTICIPANT_NAME_3);
        participant.setForname(PARTICIPANT_FORNAME_3);
        participant.setId(3);
        participant.setFunction(PARTICIPANT_JOB_3);
        return participant;
    }

    private static Participant getParticipant_four(){
        Participant participant = new Participant();
        participant.setName(PARTICIPANT_NAME_4);
        participant.setForname(PARTICIPANT_FORNAME_4);
        participant.setId(4);
        participant.setFunction(PARTICIPANT_JOB_4);
        return participant;
    }

    private static Participant getParticipant_five(){
        Participant participant = new Participant();
        participant.setName(PARTICIPANT_NAME_5);
        participant.setForname(PARTICIPANT_FORNAME_5);
        participant.setId(5);
        participant.setFunction(PARTICIPANT_JOB_5);
        return participant;
    }

    private static List<Participant> getParticipants(){
        List<Participant> participants = new ArrayList<>();
        participants.add(getParticipant_one());
        participants.add(getParticipant_two());
        participants.add(getParticipant_three());
        participants.add(getParticipant_four());
        participants.add(getParticipant_five());
        return participants;
    }

    //*****     FMEA     *****

    private static Fmei getFmea_one(){
        Fmei fmei = new Fmei();
        fmei.setId(1);
        fmei.setName(FMEA_NAME_1);
        fmei.setTeamLeader(getParticipant_one().getId());
        return fmei;
    }

    private static Fmei getFmea_two(){
        Fmei fmei = new Fmei();
        fmei.setName(FMEA_NAME_2);
        fmei.setTeamLeader(getParticipant_one().getId());
        fmei.setId(2);
        return fmei;
    }

    //*****     TEAM FMEA     *****

    private static TeamFmei getTeam_file_one(){
        TeamFmei teamFmei = new TeamFmei(getFmea_one().getId(), getParticipant_one().getId());
        teamFmei.setTeamFmeiId(1);
        return teamFmei;
    }

    private static TeamFmei getTeam_file_two(){
        TeamFmei teamFmei = new TeamFmei(getFmea_one().getId(), getParticipant_two().getId());
        teamFmei.setTeamFmeiId(2);
        return teamFmei;
    }

    private static TeamFmei getTeam_file_three(){
        TeamFmei teamFmei = new TeamFmei(getFmea_one().getId(), getParticipant_four().getId());
        teamFmei.setTeamFmeiId(3);
        return teamFmei;
    }

    private static TeamFmei getTeam_file_four(){
        TeamFmei teamFmei = new TeamFmei(getFmea_one().getId(), getParticipant_five().getId());
        teamFmei.setTeamFmeiId(4);
        return teamFmei;
    }

    private static TeamFmei getTeam_file_five(){
        TeamFmei teamFmei = new TeamFmei(getFmea_two().getId(), getParticipant_one().getId());
        teamFmei.setTeamFmeiId(5);
        return teamFmei;
    }

    private static TeamFmei getTeam_file_six(){
        TeamFmei teamFmei = new TeamFmei(getFmea_two().getId(), getParticipant_three().getId());
        teamFmei.setTeamFmeiId(6);
        return teamFmei;
    }

    private static List<TeamFmei> getTeams(){
        List<TeamFmei> teamFmeiList = new ArrayList<>();
        teamFmeiList.add(getTeam_file_one());
        teamFmeiList.add(getTeam_file_two());
        teamFmeiList.add(getTeam_file_three());
        teamFmeiList.add(getTeam_file_four());
        teamFmeiList.add(getTeam_file_five());
        teamFmeiList.add(getTeam_file_six());
        return teamFmeiList;
    }

    //*****     PROCESSUS     *****

    private static Processus getProcessus_one(){
        Processus processus = new Processus(PROCESSUS_NAME_1, getFmea_one().getId(), 1);
        processus.setId(1);
        return processus;
    }

    private static Processus getProcessus_two(){
        Processus processus = new Processus(PROCESSUS_NAME_2, getFmea_one().getId(), 2);
        processus.setId(2);
        return processus;
    }

    private static Processus getProcessus_three(){
        Processus processus = new Processus(PROCESSUS_NAME_3, getFmea_one().getId(), 3);
        processus.setId(3);
        return processus;
    }

    private static Processus getProcessus_four(){
        Processus processus = new Processus(PROCESSUS_NAME_4, getFmea_one().getId(), 4);
        processus.setId(4);
        return processus;
    }

    private static Processus getProcessus_five(){
        Processus processus = new Processus(PROCESSUS_NAME_5, getFmea_two().getId(), 1);
        processus.setId(5);
        return processus;
    }

    private static Processus getProcessus_six(){
        Processus processus = new Processus(PROCESSUS_NAME_6, getFmea_two().getId(), 2);
        processus.setId(6);
        return processus;
    }

    private static List<Processus> getProcessusListOne(){
        List<Processus> processusList = new ArrayList<>();
        processusList.add(getProcessus_one());
        processusList.add(getProcessus_two());
        processusList.add(getProcessus_three());
        processusList.add(getProcessus_four());
        return processusList;
    }

    private static List<Processus> getProcessusListTwo(){
        List<Processus> processusList = new ArrayList<>();
        processusList.add(getProcessus_five());
        processusList.add(getProcessus_six());
        return processusList;
    }

    //*****     RISK & CORRECTIVES ACTIONS    *****

    private static Risk getRisk_one(){
        Risk risk = new Risk();
        risk.setCreationDate(DATE_1);
        risk.setParts(PART_1);
        risk.setIdentification(IDENTIFICATION_1);
        risk.setRisk(RISK_NAME_1);
        risk.setProcessusId(getProcessus_one().getId());
        risk.setParticipantId(getParticipant_one().getId());
        risk.setId(1);
        risk.setGravity(9);
        risk.setDetectability(9);
        risk.setFrequencies(9);
        return risk;
    }

    private static Risk getRisk_two(){
        Risk risk = new Risk();
        risk.setCreationDate(DATE_1);
        risk.setParts(PART_1);
        risk.setIdentification(IDENTIFICATION_1);
        risk.setRisk(RISK_NAME_2);
        risk.setProcessusId(getProcessus_two().getId());
        risk.setParticipantId(getParticipant_one().getId());
        risk.setGravity(8);
        risk.setDetectability(8);
        risk.setFrequencies(8);
        risk.setId(2);
        return risk;
    }

    private static Risk getRisk_three(){
        Risk risk = new Risk();
        risk.setCreationDate(DATE_1);
        risk.setParts(PART_1);
        risk.setIdentification(IDENTIFICATION_1);
        risk.setRisk(RISK_NAME_3);
        risk.setProcessusId(getProcessus_three().getId());
        risk.setParticipantId(getParticipant_one().getId());
        risk.setId(3);
        risk.setGravity(7);
        risk.setDetectability(7);
        risk.setFrequencies(7);
        return risk;
    }

    private static Risk getRisk_four(){
        Risk risk = new Risk();
        risk.setCreationDate(DATE_1);
        risk.setParts(PART_1);
        risk.setIdentification(IDENTIFICATION_1);
        risk.setRisk(RISK_NAME_4);
        risk.setProcessusId(getProcessus_three().getId());
        risk.setParticipantId(getParticipant_one().getId());
        risk.setId(4);
        risk.setGravity(1);
        risk.setDetectability(1);
        risk.setFrequencies(1);
        return risk;
    }

    private static Risk getRisk_five(){
        Risk risk = new Risk();
        risk.setCreationDate(DATE_1);
        risk.setParts(PART_1);
        risk.setIdentification(IDENTIFICATION_1);
        risk.setRisk(RISK_NAME_5);
        risk.setProcessusId(getProcessus_four().getId());
        risk.setParticipantId(getParticipant_one().getId());
        risk.setId(5);
        risk.setGravity(6);
        risk.setDetectability(6);
        risk.setFrequencies(6);
        return risk;
    }

    private static Risk getRisk_six(){
        Risk risk = new Risk();
        risk.setCreationDate(DATE_1);
        risk.setParts(PART_1);
        risk.setIdentification(IDENTIFICATION_1);
        risk.setRisk(RISK_NAME_6);
        risk.setProcessusId(getProcessus_one().getId());
        risk.setParticipantId(getParticipant_one().getId());
        risk.setId(6);
        risk.setGravity(1);
        risk.setDetectability(1);
        risk.setFrequencies(1);
        return risk;
    }

    private static Risk getRisk_seven(){
        Risk risk = new Risk();
        risk.setCreationDate(DATE_2);
        risk.setParts(PART_2);
        risk.setIdentification(IDENTIFICATION_2);
        risk.setRisk(RISK_NAME_7);
        risk.setProcessusId(getProcessus_six().getId());
        risk.setParticipantId(getParticipant_two().getId());
        risk.setId(7);
        risk.setGravity(9);
        risk.setDetectability(9);
        risk.setFrequencies(9);
        return risk;
    }

    private static Risk getRisk_height(){
        Risk risk = new Risk();
        risk.setCreationDate(DATE_2);
        risk.setParts(PART_2);
        risk.setIdentification(IDENTIFICATION_2);
        risk.setRisk(RISK_NAME_8);
        risk.setProcessusId(getProcessus_six().getId());
        risk.setParticipantId(getParticipant_three().getId());
        risk.setId(8);
        risk.setGravity(1);
        risk.setDetectability(1);
        risk.setFrequencies(1);
        return risk;
    }

    private static List<Risk> getRisks(){
        List<Risk> riskList = new ArrayList<>();
        riskList.add(getRisk_one());
        riskList.add(getRisk_two());
        riskList.add(getRisk_three());
        riskList.add(getRisk_four());
        riskList.add(getRisk_five());
        riskList.add(getRisk_six());
        riskList.add(getRisk_seven());
        riskList.add(getRisk_height());
        return riskList;
    }

    private static CorrectiveAction getCorrective_one(){
        CorrectiveAction correctiveAction = new CorrectiveAction();
        correctiveAction.setCorrectiveAction(CORRECTIVE_1);
        correctiveAction.setCreationDate(DATE_3);
        correctiveAction.setParts(PART_1);
        correctiveAction.setIdentification(IDENTIFICATION_1);
        correctiveAction.setDeadLineDate(DATE_3);
        correctiveAction.setRiskId(getRisk_one().getId());
        correctiveAction.setParticipantId(getParticipant_two().getId());
        correctiveAction.setId(1);
        correctiveAction.setNewGravity(2);
        correctiveAction.setNewDetectability(2);
        correctiveAction.setNewFrequencies(2);
        correctiveAction.setRealisationDate(DATE_4);
        return correctiveAction;
    }

    private static CorrectiveAction getCorrective_two(){
        CorrectiveAction correctiveAction = new CorrectiveAction();
        correctiveAction.setCorrectiveAction(CORRECTIVE_2);
        correctiveAction.setCreationDate(DATE_3);
        correctiveAction.setParts(PART_1);
        correctiveAction.setIdentification(IDENTIFICATION_1);
        correctiveAction.setDeadLineDate(DATE_3);
        correctiveAction.setRiskId(getRisk_two().getId());
        correctiveAction.setParticipantId(getParticipant_two().getId());
        correctiveAction.setId(2);
        correctiveAction.setNewGravity(2);
        correctiveAction.setNewDetectability(2);
        correctiveAction.setNewFrequencies(2);
        correctiveAction.setRealisationDate(DATE_4);
        return correctiveAction;
    }

    private static CorrectiveAction getCorrective_three(){
        CorrectiveAction correctiveAction = new CorrectiveAction();
        correctiveAction.setCorrectiveAction(CORRECTIVE_3);
        correctiveAction.setCreationDate(DATE_3);
        correctiveAction.setParts(PART_1);
        correctiveAction.setIdentification(IDENTIFICATION_1);
        correctiveAction.setDeadLineDate(DATE_3);
        correctiveAction.setRiskId(getRisk_three().getId());
        correctiveAction.setParticipantId(getParticipant_two().getId());
        correctiveAction.setId(3);
        correctiveAction.setNewGravity(2);
        correctiveAction.setNewDetectability(2);
        correctiveAction.setNewFrequencies(2);
        correctiveAction.setRealisationDate(DATE_4);
        return correctiveAction;
    }

    private static CorrectiveAction getCorrective_four(){
        CorrectiveAction correctiveAction = new CorrectiveAction();
        correctiveAction.setCorrectiveAction(CORRECTIVE_4);
        correctiveAction.setCreationDate(DATE_3);
        correctiveAction.setParts(PART_1);
        correctiveAction.setIdentification(IDENTIFICATION_1);
        correctiveAction.setDeadLineDate(DATE_3);
        correctiveAction.setRiskId(getRisk_five().getId());
        correctiveAction.setParticipantId(getParticipant_two().getId());
        correctiveAction.setId(4);
        correctiveAction.setNewGravity(2);
        correctiveAction.setNewDetectability(2);
        correctiveAction.setNewFrequencies(2);
        correctiveAction.setRealisationDate(DATE_4);
        return correctiveAction;
    }

    private static CorrectiveAction getCorrective_five(){
        CorrectiveAction correctiveAction = new CorrectiveAction();
        correctiveAction.setCorrectiveAction(CORRECTIVE_5);
        correctiveAction.setCreationDate(DATE_3);
        correctiveAction.setParts(PART_2);
        correctiveAction.setIdentification(IDENTIFICATION_2);
        correctiveAction.setDeadLineDate(DATE_3);
        correctiveAction.setRiskId(getRisk_seven().getId());
        correctiveAction.setParticipantId(getParticipant_two().getId());
        correctiveAction.setId(5);
        correctiveAction.setNewGravity(2);
        correctiveAction.setNewDetectability(2);
        correctiveAction.setNewFrequencies(2);
        correctiveAction.setRealisationDate(DATE_4);
        return correctiveAction;
    }

    private static List<CorrectiveAction> getCorrectiveList(){
        List<CorrectiveAction> correctiveActions = new ArrayList<>();
        correctiveActions.add(getCorrective_one());
        correctiveActions.add(getCorrective_two());
        correctiveActions.add(getCorrective_three());
        correctiveActions.add(getCorrective_four());
        correctiveActions.add(getCorrective_five());
        return correctiveActions;
    }

    /**
     *  TEST
     */

    @Test
    public void testIfGoodInformationAboutLineTwoFmeiOne(){
        String verification = BusinessExport.getLineTwo(getFmea_one(), getParticipants());
        assertEquals(SEPARATOR + PARTICIPANT_1, verification);
    }

    @Test
    public void testIfGoodInformationAboutLineTwoFmeiTwo(){
        String verification = BusinessExport.getLineTwo(getFmea_two(), getParticipants());
        assertEquals(SEPARATOR + PARTICIPANT_1, verification);
    }

    @Test
    public void testIfGoodTeamInformationsFmeiOne(){
        String verification = BusinessExport.getLinesTeam(getFmea_one(), getTeams(), getParticipants());
        assertEquals(SEPARATOR + PARTICIPANT_1 + SEPARATOR + PARTICIPANT_JOB_1 + "\n" +
                SEPARATOR + PARTICIPANT_2 + SEPARATOR + PARTICIPANT_JOB_2 + "\n" +
                SEPARATOR + PARTICIPANT_4 + SEPARATOR + PARTICIPANT_JOB_4 + "\n" +
                SEPARATOR + PARTICIPANT_5 + SEPARATOR + PARTICIPANT_JOB_5 + "\n", verification);
    }

    @Test
    public void testIfGoodTeamInformationsFmeiTwo(){
        String verification = BusinessExport.getLinesTeam(getFmea_two(), getTeams(), getParticipants());
        assertEquals(SEPARATOR + PARTICIPANT_1 + SEPARATOR + PARTICIPANT_JOB_1 + "\n" +
                SEPARATOR + PARTICIPANT_3 + SEPARATOR + PARTICIPANT_JOB_3 + "\n", verification);
    }

    @Test
    public void testIfGoodBodyInformationsFmeaOne(){
        String verification = BusinessExport.getBody(getParticipants(), getProcessusListOne(), getRisks(), getCorrectiveList());
        String[] bodyLines = verification.split("\n");
        String bodyLineOne = bodyLines[0];
        String bodyLinetwo = bodyLines[1];
        String bodyLineThree = bodyLines[2];
        String bodyLineFour = bodyLines[3];
        String bodyLineFive = bodyLines[4];
        String bodyLineSix = bodyLines[5];

        assertEquals(6, bodyLines.length);

        assertEquals(PROCESSUS_NAME_1 + SEPARATOR + PARTICIPANT_1 + SEPARATOR +  PART_1 + SEPARATOR +  IDENTIFICATION_1 +
                SEPARATOR + RISK_NAME_1 + SEPARATOR + getRisk_one().getRiskEffect() + SEPARATOR + DATE_1 + SEPARATOR + getRisk_one().getPotentialCause()
                + SEPARATOR + getRisk_one().getVerification() + SEPARATOR + getRisk_one().getGravity() + SEPARATOR + getRisk_one().getFrequencies()
                + SEPARATOR + getRisk_one().getDetectability() + SEPARATOR + BusinessExport.getIPR(getRisk_one().getGravity(), getRisk_one().getFrequencies(), getRisk_one().getDetectability())
                + SEPARATOR + getCorrective_one().getCorrectiveAction() + SEPARATOR + DATE_3 + SEPARATOR + PARTICIPANT_2 + SEPARATOR + PART_1 + SEPARATOR + IDENTIFICATION_1
                + SEPARATOR + getCorrective_one().getCorrectiveDescription() + SEPARATOR + DATE_4 + SEPARATOR + getCorrective_one().getNewGravity() + SEPARATOR +
                getCorrective_one().getNewFrequencies() + SEPARATOR + getCorrective_one().getNewDetectability() + SEPARATOR +
                BusinessExport.getIPR(getCorrective_one().getNewGravity(), getCorrective_one().getNewFrequencies(), getCorrective_one().getNewDetectability()), bodyLineOne);

        assertEquals(PROCESSUS_NAME_1 + SEPARATOR + PARTICIPANT_1 + SEPARATOR +  PART_1 + SEPARATOR +  IDENTIFICATION_1 +
                SEPARATOR + RISK_NAME_6 + SEPARATOR + getRisk_six().getRiskEffect() + SEPARATOR + DATE_1 + SEPARATOR + getRisk_six().getPotentialCause()
                + SEPARATOR + getRisk_six().getVerification() + SEPARATOR + getRisk_six().getGravity() + SEPARATOR + getRisk_six().getFrequencies()
                + SEPARATOR + getRisk_six().getDetectability() + SEPARATOR + BusinessExport.getIPR(getRisk_six().getGravity(), getRisk_six().getFrequencies(), getRisk_six().getDetectability())
                + SEPARATOR, bodyLinetwo);

        assertEquals(PROCESSUS_NAME_2 + SEPARATOR + PARTICIPANT_1 + SEPARATOR +  PART_1 + SEPARATOR +  IDENTIFICATION_1 +
                SEPARATOR + RISK_NAME_2 + SEPARATOR + getRisk_two().getRiskEffect() + SEPARATOR + DATE_1 + SEPARATOR + getRisk_two().getPotentialCause()
                + SEPARATOR + getRisk_two().getVerification() + SEPARATOR + getRisk_two().getGravity() + SEPARATOR + getRisk_two().getFrequencies()
                + SEPARATOR + getRisk_two().getDetectability() + SEPARATOR + BusinessExport.getIPR(getRisk_two().getGravity(), getRisk_two().getFrequencies(), getRisk_two().getDetectability())
                + SEPARATOR + getCorrective_two().getCorrectiveAction() + SEPARATOR + DATE_3 + SEPARATOR + PARTICIPANT_2 + SEPARATOR + PART_1 + SEPARATOR + IDENTIFICATION_1
                + SEPARATOR + getCorrective_two().getCorrectiveDescription() + SEPARATOR + DATE_4 + SEPARATOR + getCorrective_two().getNewGravity() + SEPARATOR +
                getCorrective_two().getNewFrequencies() + SEPARATOR + getCorrective_two().getNewDetectability() + SEPARATOR +
                BusinessExport.getIPR(getCorrective_two().getNewGravity(), getCorrective_two().getNewFrequencies(), getCorrective_two().getNewDetectability()), bodyLineThree);

        assertEquals(PROCESSUS_NAME_3 + SEPARATOR + PARTICIPANT_1 + SEPARATOR +  PART_1 + SEPARATOR +  IDENTIFICATION_1 +
                SEPARATOR + RISK_NAME_3 + SEPARATOR + getRisk_three().getRiskEffect() + SEPARATOR + DATE_1 + SEPARATOR + getRisk_three().getPotentialCause()
                + SEPARATOR + getRisk_three().getVerification() + SEPARATOR + getRisk_three().getGravity() + SEPARATOR + getRisk_three().getFrequencies()
                + SEPARATOR + getRisk_three().getDetectability() + SEPARATOR + BusinessExport.getIPR(getRisk_three().getGravity(), getRisk_three().getFrequencies(), getRisk_three().getDetectability())
                + SEPARATOR + getCorrective_three().getCorrectiveAction() + SEPARATOR + DATE_3 + SEPARATOR + PARTICIPANT_2 + SEPARATOR + PART_1 + SEPARATOR + IDENTIFICATION_1
                + SEPARATOR + getCorrective_three().getCorrectiveDescription() + SEPARATOR + DATE_4 + SEPARATOR + getCorrective_three().getNewGravity() + SEPARATOR +
                getCorrective_three().getNewFrequencies() + SEPARATOR + getCorrective_three().getNewDetectability() + SEPARATOR +
                BusinessExport.getIPR(getCorrective_three().getNewGravity(), getCorrective_three().getNewFrequencies(), getCorrective_three().getNewDetectability()), bodyLineFour);

        assertEquals(PROCESSUS_NAME_3 + SEPARATOR + PARTICIPANT_1 + SEPARATOR +  PART_1 + SEPARATOR +  IDENTIFICATION_1 +
                SEPARATOR + RISK_NAME_4 + SEPARATOR + getRisk_four().getRiskEffect() + SEPARATOR + DATE_1 + SEPARATOR + getRisk_four().getPotentialCause()
                + SEPARATOR + getRisk_four().getVerification() + SEPARATOR + getRisk_four().getGravity() + SEPARATOR + getRisk_four().getFrequencies()
                + SEPARATOR + getRisk_four().getDetectability() + SEPARATOR + BusinessExport.getIPR(getRisk_four().getGravity(), getRisk_four().getFrequencies(), getRisk_four().getDetectability())
                + SEPARATOR , bodyLineFive);

        assertEquals(PROCESSUS_NAME_4 + SEPARATOR + PARTICIPANT_1 + SEPARATOR +  PART_1 + SEPARATOR +  IDENTIFICATION_1 +
                SEPARATOR + RISK_NAME_5 + SEPARATOR + getRisk_five().getRiskEffect() + SEPARATOR + DATE_1 + SEPARATOR + getRisk_five().getPotentialCause()
                + SEPARATOR + getRisk_five().getVerification() + SEPARATOR + getRisk_five().getGravity() + SEPARATOR + getRisk_five().getFrequencies()
                + SEPARATOR + getRisk_five().getDetectability() + SEPARATOR + BusinessExport.getIPR(getRisk_five().getGravity(), getRisk_five().getFrequencies(), getRisk_five().getDetectability())
                + SEPARATOR + getCorrective_four().getCorrectiveAction() + SEPARATOR + DATE_3 + SEPARATOR + PARTICIPANT_2 + SEPARATOR + PART_1 + SEPARATOR + IDENTIFICATION_1
                + SEPARATOR + getCorrective_four().getCorrectiveDescription() + SEPARATOR + DATE_4 + SEPARATOR + getCorrective_four().getNewGravity() + SEPARATOR +
                getCorrective_four().getNewFrequencies() + SEPARATOR + getCorrective_four().getNewDetectability() + SEPARATOR +
                BusinessExport.getIPR(getCorrective_four().getNewGravity(), getCorrective_four().getNewFrequencies(), getCorrective_four().getNewDetectability()) , bodyLineSix);
    }

    @Test
    public void testIfGoodBodyInformationsFmeaTwo() {
        String verification = BusinessExport.getBody(getParticipants(), getProcessusListTwo(), getRisks(), getCorrectiveList());
        String[] bodyLines = verification.split("\n");
        String bodyLineOne = bodyLines[0];
        String bodyLinetwo = bodyLines[1];

        assertEquals(2, bodyLines.length);

        assertEquals(PROCESSUS_NAME_6 + SEPARATOR + PARTICIPANT_2 + SEPARATOR +  PART_2 + SEPARATOR +  IDENTIFICATION_2 +
                SEPARATOR + RISK_NAME_7 + SEPARATOR + getRisk_seven().getRiskEffect() + SEPARATOR + DATE_2 + SEPARATOR + getRisk_seven().getPotentialCause()
                + SEPARATOR + getRisk_seven().getVerification() + SEPARATOR + getRisk_seven().getGravity() + SEPARATOR + getRisk_seven().getFrequencies()
                + SEPARATOR + getRisk_seven().getDetectability() + SEPARATOR + BusinessExport.getIPR(getRisk_seven().getGravity(), getRisk_seven().getFrequencies(), getRisk_seven().getDetectability())
                + SEPARATOR + getCorrective_five().getCorrectiveAction() + SEPARATOR + DATE_3 + SEPARATOR + PARTICIPANT_2 + SEPARATOR + PART_2 + SEPARATOR + IDENTIFICATION_2
                + SEPARATOR + getCorrective_five().getCorrectiveDescription() + SEPARATOR + DATE_4 + SEPARATOR + getCorrective_five().getNewGravity() + SEPARATOR +
                getCorrective_five().getNewFrequencies() + SEPARATOR + getCorrective_five().getNewDetectability() + SEPARATOR +
                BusinessExport.getIPR(getCorrective_five().getNewGravity(), getCorrective_five().getNewFrequencies(), getCorrective_five().getNewDetectability()) , bodyLineOne);

        assertEquals(PROCESSUS_NAME_6 + SEPARATOR + PARTICIPANT_3 + SEPARATOR +  PART_2 + SEPARATOR +  IDENTIFICATION_2 +
                SEPARATOR + RISK_NAME_8 + SEPARATOR + getRisk_height().getRiskEffect() + SEPARATOR + DATE_2 + SEPARATOR + getRisk_height().getPotentialCause()
                + SEPARATOR + getRisk_height().getVerification() + SEPARATOR + getRisk_height().getGravity() + SEPARATOR + getRisk_height().getFrequencies()
                + SEPARATOR + getRisk_height().getDetectability() + SEPARATOR + BusinessExport.getIPR(getRisk_height().getGravity(), getRisk_height().getFrequencies(), getRisk_height().getDetectability())
                + SEPARATOR , bodyLinetwo);
    }







}
