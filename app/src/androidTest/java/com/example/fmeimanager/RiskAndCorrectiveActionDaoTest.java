package com.example.fmeimanager;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.fmeimanager.database.FmeiManagerDatabase;
import com.example.fmeimanager.database.CorrectiveAction;
import com.example.fmeimanager.database.Fmei;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.Processus;
import com.example.fmeimanager.database.Risk;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class RiskAndCorrectiveActionDaoTest {

    //FOR DATA
    private FmeiManagerDatabase mDatabase;

    private static final String FMEI_1_NAME  = "FMEI_1_NAME";
    private static final String FMEI_2_NAME  = "FMEI_2_NAME";

    private static final String PARTICIPANT_1_NAME  = "PARTICIPANT_1_NAME";
    private static final String PARTICIPANT_1_FORNAME  = "PARTICIPANT_1_FORNAME";
    private static final String PARTICIPANT_2_NAME  = "PARTICIPANT_2_NAME";
    private static final String PARTICIPANT_2_FORNAME  = "PARTICIPANT_2_FORNAME";
    private static final String PARTICIPANT_3_NAME  = "PARTICIPANT_3_NAME";
    private static final String PARTICIPANT_3_FORNAME  = "PARTICIPANT_3_FORNAME";
    private static final String PARTICIPANT_4_NAME  = "PARTICIPANT_4_NAME";
    private static final String PARTICIPANT_4_FORNAME  = "PARTICIPANT_4_FORNAME";

    private static final long ID_1 = 1;
    private static final long ID_2 = 2;
    private static final long ID_3 = 3;
    private static final long ID_4 = 4;
    private static final long ID_5 = 5;

    //FMEI
    private static Fmei FMEI_DEMO_1(){
        Fmei fmei = new Fmei();
        fmei.setName(FMEI_1_NAME);
        fmei.setTeamLeader(1);
        return fmei;
    }

    private static Fmei FMEI_DEMO_2(){
        Fmei fmei = new Fmei();
        fmei.setName(FMEI_2_NAME);
        fmei.setTeamLeader(1);
        return fmei;
    }

    //PROCESSUS
    private static Processus PROCESSUS_DEMO_1(){return new Processus("processus_step_1", ID_1, 1);}
    private static Processus PROCESSUS_DEMO_2(){return new Processus("processus_step_2", ID_1, 2);}
    private static Processus PROCESSUS_DEMO_3(){return new Processus("processus_step_3", ID_1, 2);}
    private static Processus PROCESSUS_DEMO_4(){return new Processus("processus_step_4", ID_2, 3);}
    private static Processus PROCESSUS_DEMO_5(){return new Processus("processus_step_5", ID_1, 3);}

    //RISK
    private static Risk RISK_DEMO_1(){
        Risk risk = new Risk();
        risk.setCreationDate("12/02/2019");
        risk.setParts("Parts_One");
        risk.setIdentification("100145XD");
        risk.setRisk("risk_one");
        risk.setProcessusId(ID_1);
        risk.setParticipantId(ID_1);
        return risk;
    }

    private static Risk RISK_DEMO_2(){
        Risk risk = new Risk();
        risk.setCreationDate("10/02/2019");
        risk.setParts("Parts_One");
        risk.setIdentification("100145XD");
        risk.setRisk("risk_two");
        risk.setProcessusId(ID_2);
        risk.setParticipantId(ID_2);
        return risk;
    }

    private static Risk RISK_DEMO_3(){
        Risk risk = new Risk();
        risk.setCreationDate("15/02/2019");
        risk.setParts("Parts_Two");
        risk.setIdentification("178145IO");
        risk.setRisk("risk_three");
        risk.setProcessusId(ID_3);
        risk.setParticipantId(ID_4);
        return risk;
    }

    private static Risk RISK_DEMO_4(){
        Risk risk = new Risk();
        risk.setCreationDate("17/02/2019");
        risk.setParts("Parts_Two");
        risk.setIdentification("178145IO");
        risk.setRisk("risk_four");
        risk.setProcessusId(ID_5);
        risk.setParticipantId(ID_4);
        return risk;
    }

    private static Risk RISK_DEMO_5(){
        Risk risk = new Risk();
        risk.setCreationDate("18/02/2019");
        risk.setParts("Parts_Two");
        risk.setIdentification("178145IO");
        risk.setRisk("risk_five");
        risk.setProcessusId(ID_5);
        risk.setParticipantId(ID_4);
        return risk;
    }

    //CORRECTIVE ACTION
    private static CorrectiveAction CORRECTIVE_DEMO_1(){
        CorrectiveAction correctiveAction = new CorrectiveAction();
        correctiveAction.setCorrectiveAction("Corrective_action_title_1");
        correctiveAction.setCreationDate("21/02/2019");
        correctiveAction.setParts("Parts_One");
        correctiveAction.setIdentification("100145XD");
        correctiveAction.setDeadLineDate("01/03/2019");
        correctiveAction.setRiskId(ID_1);
        correctiveAction.setParticipantId(ID_3);
        return correctiveAction;
    }

    private static CorrectiveAction CORRECTIVE_DEMO_2(){
        CorrectiveAction correctiveAction = new CorrectiveAction();
        correctiveAction.setCorrectiveAction("Corrective_action_title_2");
        correctiveAction.setCreationDate("20/02/2019");
        correctiveAction.setParts("Parts_One");
        correctiveAction.setIdentification("100145XD");
        correctiveAction.setDeadLineDate("08/03/2019");
        correctiveAction.setRiskId(ID_2);
        correctiveAction.setParticipantId(ID_3);
        return correctiveAction;
    }

    private static CorrectiveAction CORRECTIVE_DEMO_3(){
        CorrectiveAction correctiveAction = new CorrectiveAction();
        correctiveAction.setCorrectiveAction("Corrective_action_title_3");
        correctiveAction.setCreationDate("16/02/2019");
        correctiveAction.setParts("Parts_Two");
        correctiveAction.setIdentification("178145IO");
        correctiveAction.setDeadLineDate("02/04/2019");
        correctiveAction.setRiskId(ID_3);
        correctiveAction.setParticipantId(ID_2);
        return correctiveAction;
    }

    private static CorrectiveAction CORRECTIVE_DEMO_4(){
        CorrectiveAction correctiveAction = new CorrectiveAction();
        correctiveAction.setCorrectiveAction("Corrective_action_title_4");
        correctiveAction.setCreationDate("19/02/2019");
        correctiveAction.setParts("Parts_Two");
        correctiveAction.setIdentification("178145IO");
        correctiveAction.setDeadLineDate("07/03/2019");
        correctiveAction.setRiskId(ID_4);
        correctiveAction.setParticipantId(ID_3);
        return correctiveAction;
    }

    private static CorrectiveAction CORRECTIVE_DEMO_5(){
        CorrectiveAction correctiveAction = new CorrectiveAction();
        correctiveAction.setCorrectiveAction("Corrective_action_title_5");
        correctiveAction.setCreationDate("21/02/2019");
        correctiveAction.setParts("Parts_Two");
        correctiveAction.setIdentification("178145IO");
        correctiveAction.setDeadLineDate("21/03/2019");
        correctiveAction.setRiskId(ID_5);
        correctiveAction.setParticipantId(ID_3);
        return correctiveAction;
    }

    //PARTICIPANT
    private static Participant PARTICIPANT_DEMO_1(){
        Participant participant = new Participant();
        participant.setName(PARTICIPANT_1_NAME);
        participant.setForname(PARTICIPANT_1_FORNAME);
        return participant;
    }

    private static Participant PARTICIPANT_DEMO_2(){
        Participant participant = new Participant();
        participant.setName(PARTICIPANT_2_NAME);
        participant.setForname(PARTICIPANT_2_FORNAME);
        return participant;
    }

    private static Participant PARTICIPANT_DEMO_3(){
        Participant participant = new Participant();
        participant.setName(PARTICIPANT_3_NAME);
        participant.setForname(PARTICIPANT_3_FORNAME);
        return participant;
    }

    private static Participant PARTICIPANT_DEMO_4(){
        Participant participant = new Participant();
        participant.setName(PARTICIPANT_4_NAME);
        participant.setForname(PARTICIPANT_4_FORNAME);
        return participant;
    }

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() {
        this.mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                FmeiManagerDatabase.class)
                .allowMainThreadQueries()
                .build();

        this.mDatabase.mFmeiDao().createFmei(FMEI_DEMO_1());
        this.mDatabase.mFmeiDao().createFmei(FMEI_DEMO_2());
        this.mDatabase.mProcessusDao().insertProcessus(PROCESSUS_DEMO_1());
        this.mDatabase.mProcessusDao().insertProcessus(PROCESSUS_DEMO_2());
        this.mDatabase.mProcessusDao().insertProcessus(PROCESSUS_DEMO_3());
        this.mDatabase.mProcessusDao().insertProcessus(PROCESSUS_DEMO_4());
        this.mDatabase.mProcessusDao().insertProcessus(PROCESSUS_DEMO_5());
        this.mDatabase.mParticipantDao().insertParticipant(PARTICIPANT_DEMO_1());
        this.mDatabase.mParticipantDao().insertParticipant(PARTICIPANT_DEMO_2());
        this.mDatabase.mParticipantDao().insertParticipant(PARTICIPANT_DEMO_3());
        this.mDatabase.mParticipantDao().insertParticipant(PARTICIPANT_DEMO_4());
        this.mDatabase.mRiskDao().insertRisk(RISK_DEMO_1());
        this.mDatabase.mRiskDao().insertRisk(RISK_DEMO_2());
        this.mDatabase.mRiskDao().insertRisk(RISK_DEMO_3());
        this.mDatabase.mRiskDao().insertRisk(RISK_DEMO_4());
        this.mDatabase.mRiskDao().insertRisk(RISK_DEMO_5());
        this.mDatabase.mCorrectiveActionDao().insertCorrectiveAction(CORRECTIVE_DEMO_1());
        this.mDatabase.mCorrectiveActionDao().insertCorrectiveAction(CORRECTIVE_DEMO_2());
        this.mDatabase.mCorrectiveActionDao().insertCorrectiveAction(CORRECTIVE_DEMO_3());
        this.mDatabase.mCorrectiveActionDao().insertCorrectiveAction(CORRECTIVE_DEMO_4());
        this.mDatabase.mCorrectiveActionDao().insertCorrectiveAction(CORRECTIVE_DEMO_5());
    }

    @After
    public void closeDb() {
        mDatabase.close();
    }

    //TEST : RiskDao

    @Test
    public void getAllRisk() throws Exception {
        List<Risk> riskList = LiveDataTestUtil.getValue(mDatabase.mRiskDao().getAllRisk());
        assertEquals(5, riskList.size());
    }

    @Test
    public void insertAndGetRiskTest() throws Exception {
        Risk risk = LiveDataTestUtil.getValue(this.mDatabase.mRiskDao().getRisk(ID_1));
        assertTrue(risk.getCreationDate().equals("12/02/2019") && risk.getId() == ID_1);
    }

    @Test
    public void updateAndGetRiskTest() throws Exception {
        Risk risk = LiveDataTestUtil.getValue(this.mDatabase.mRiskDao().getRisk(ID_1));
        assertTrue(risk.getCreationDate().equals("12/02/2019") && risk.getId() == ID_1);

        risk.setCreationDate("13/02/2019");
        risk.setProcessusId(ID_2);
        this.mDatabase.mRiskDao().updateRisk(risk);
        Risk riskUpdated = LiveDataTestUtil.getValue(mDatabase.mRiskDao().getRisk(ID_1));
        assertTrue(riskUpdated.getCreationDate().equals("13/02/2019") && riskUpdated.getProcessusId() == ID_2);
    }

    @Test
    public void deleteAndGetRiskTest() throws Exception {
        List<Risk> riskList = LiveDataTestUtil.getValue(this.mDatabase.mRiskDao().getAllRisk());
        assertEquals(5, riskList.size());
        assertEquals(riskList.get(0).getId(), ID_1);
        this.mDatabase.mRiskDao().deleteRisk(ID_1);
        List<Risk> riskListAfter = LiveDataTestUtil.getValue(this.mDatabase.mRiskDao().getAllRisk());
        assertEquals(4, riskListAfter.size());
        assertEquals(riskListAfter.get(0).getId(), ID_2);
        List<CorrectiveAction> correctiveActionList = LiveDataTestUtil.getValue(this.mDatabase.mCorrectiveActionDao().getAllCorrectiveAction());
        assertEquals(4, correctiveActionList.size());
    }

    @Test
    public void getRiskListWithParticipantIdTest() throws Exception {
        List<Risk> riskList = LiveDataTestUtil.getValue(this.mDatabase.mRiskDao().getRiskAboutParticipantId(ID_4));
        assertEquals(3, riskList.size());
        assertTrue(riskList.get(0).getRisk().equals("risk_three") && riskList.get(1).getRisk().equals("risk_four") && riskList.get(2).getRisk().equals("risk_five"));
    }

    @Test
    public void getRiskListWithProcessusIdTest() throws Exception {
        List<Risk> riskList = LiveDataTestUtil.getValue(this.mDatabase.mRiskDao().getRiskAboutProcessusId(ID_5));
        assertEquals(2, riskList.size());
        assertTrue(riskList.get(0).getRisk().equals("risk_four") && riskList.get(1).getRisk().equals("risk_five"));
    }


    //TEST : CorrectiveActionDao

    @Test
    public void getAllCorrectiveAction() throws Exception {
        List<CorrectiveAction> correctiveActionList = LiveDataTestUtil.getValue(mDatabase.mCorrectiveActionDao().getAllCorrectiveAction());
        assertEquals(5, correctiveActionList.size());
    }

    @Test
    public void insertAndGetCorrectiveActionTest() throws Exception {
        CorrectiveAction correctiveActionList = LiveDataTestUtil.getValue(this.mDatabase.mCorrectiveActionDao().getCorrectiveAction(ID_1));
        assertTrue(correctiveActionList.getCreationDate().equals("21/02/2019") && correctiveActionList.getId() == ID_1);
    }

    @Test
    public void updateAndGetCorrectiveActionTest() throws Exception {
        CorrectiveAction correctiveAction = LiveDataTestUtil.getValue(this.mDatabase.mCorrectiveActionDao().getCorrectiveAction(ID_1));
        assertTrue(correctiveAction.getCreationDate().equals("21/02/2019") && correctiveAction.getId() == ID_1);

        correctiveAction.setCreationDate("13/02/2019");
        correctiveAction.setRiskId(ID_2);
        this.mDatabase.mCorrectiveActionDao().updateCorrectiveAction(correctiveAction);
        CorrectiveAction correctiveActionUpdated = LiveDataTestUtil.getValue(mDatabase.mCorrectiveActionDao().getCorrectiveAction(ID_1));
        assertTrue(correctiveActionUpdated.getCreationDate().equals("13/02/2019") && correctiveActionUpdated.getRiskId() == ID_2);
    }

    @Test
    public void deleteAndGetCorrectiveActionTest() throws Exception {
        List<CorrectiveAction> correctiveActionList = LiveDataTestUtil.getValue(this.mDatabase.mCorrectiveActionDao().getAllCorrectiveAction());
        assertEquals(5, correctiveActionList.size());
        assertEquals(correctiveActionList.get(0).getId(), ID_1);
        this.mDatabase.mCorrectiveActionDao().deleteCorrectiveAction(ID_1);
        List<CorrectiveAction> correctiveActionListAfter = LiveDataTestUtil.getValue(this.mDatabase.mCorrectiveActionDao().getAllCorrectiveAction());
        assertEquals(4, correctiveActionListAfter.size());
        assertEquals(correctiveActionListAfter.get(0).getId(), ID_2);
    }

    @Test
    public void getCorrectiveActionListWithParticipantIdTest() throws Exception {
        List<CorrectiveAction> correctiveActionList = LiveDataTestUtil.getValue(this.mDatabase.mCorrectiveActionDao().getCorrectiveActionAboutParticipantId(ID_3));
        assertEquals(4, correctiveActionList.size());
        assertTrue(correctiveActionList.get(0).getCorrectiveAction().equals("Corrective_action_title_1") &&
                correctiveActionList.get(1).getCorrectiveAction().equals("Corrective_action_title_2") &&
                correctiveActionList.get(2).getCorrectiveAction().equals("Corrective_action_title_4") &&
                correctiveActionList.get(3).getCorrectiveAction().equals("Corrective_action_title_5"));
    }

    @Test
    public void getCorrectiveActionListWithProcessusIdTest() throws Exception {
        CorrectiveAction correctiveAction = LiveDataTestUtil.getValue(this.mDatabase.mCorrectiveActionDao().getCorrectiveActionAboutRiskId(ID_5));
        assertTrue(correctiveAction.getCorrectiveAction().equals("Corrective_action_title_5"));
    }


}
