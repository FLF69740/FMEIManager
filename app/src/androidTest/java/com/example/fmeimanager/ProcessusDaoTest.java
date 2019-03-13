package com.example.fmeimanager;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.example.fmeimanager.database.FmeiManagerDatabase;
import com.example.fmeimanager.models.Fmei;
import com.example.fmeimanager.models.Participant;
import com.example.fmeimanager.models.Processus;
import com.example.fmeimanager.models.Risk;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ProcessusDaoTest {

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
    private static Fmei FMEI_DEMO_1(){return new Fmei(FMEI_1_NAME);}
    private static Fmei FMEI_DEMO_2(){return new Fmei(FMEI_2_NAME);}

    //PARTICIPANT
    private static Participant PARTICIPANT_DEMO_1(){return new Participant(PARTICIPANT_1_NAME, PARTICIPANT_1_FORNAME);}
    private static Participant PARTICIPANT_DEMO_2(){return new Participant(PARTICIPANT_2_NAME, PARTICIPANT_2_FORNAME);}
    private static Participant PARTICIPANT_DEMO_3(){return new Participant(PARTICIPANT_3_NAME, PARTICIPANT_3_FORNAME);}
    private static Participant PARTICIPANT_DEMO_4(){return new Participant(PARTICIPANT_4_NAME, PARTICIPANT_4_FORNAME);}

    //RISK
    private static Risk RISK_DEMO_1(){return new Risk("12/02/2019", "Parts_One", "100145XD", "risk_one", ID_1, ID_1);}
    private static Risk RISK_DEMO_2(){return new Risk("10/02/2019", "Parts_One", "100145XD", "risk_two", ID_3, ID_2);}
    private static Risk RISK_DEMO_3(){return new Risk("15/02/2019", "Parts_Two", "178145IO", "risk_three", ID_5, ID_4);}

    //PROCESSUS
    private static Processus PROCESSUS_DEMO_1(){return new Processus("processus_step_1", ID_1);}
    private static Processus PROCESSUS_DEMO_2(){return new Processus("processus_step_2", ID_1);}
    private static Processus PROCESSUS_DEMO_3(){return new Processus("processus_step_3", ID_1);}
    private static Processus PROCESSUS_DEMO_4(){return new Processus("processus_step_4", ID_2);}
    private static Processus PROCESSUS_DEMO_5(){return new Processus("processus_step_5", ID_1);}


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
    }

    @After
    public void closeDb() {
        mDatabase.close();
    }

    //TEST : ProcessusDao

    @Test
    public void getAllProcessus() throws Exception {
        List<Processus> processusList = LiveDataTestUtil.getValue(mDatabase.mProcessusDao().getAllProcessus());
        assertEquals(5, processusList.size());
    }

    @Test
    public void insertAndGetProcessusTest() throws Exception {
        Processus processus = LiveDataTestUtil.getValue(this.mDatabase.mProcessusDao().getProcessus(ID_1));
        assertTrue(processus.getName().equals("processus_step_1") && processus.getId() == ID_1);
    }

    @Test
    public void updateAndGetProcessusTest() throws Exception {
        Processus processus = LiveDataTestUtil.getValue(this.mDatabase.mProcessusDao().getProcessus(ID_1));
        assertTrue(processus.getName().equals("processus_step_1") && processus.getId() == ID_1);

        processus.setName("processus_step_2");
        processus.setFmeiId(ID_2);
        this.mDatabase.mProcessusDao().updateProcessus(processus);
        Processus processusUpdated = LiveDataTestUtil.getValue(mDatabase.mProcessusDao().getProcessus(ID_1));
        assertTrue(processusUpdated.getName().equals("processus_step_2") && processusUpdated.getFmeiId() == ID_2);
    }

    @Test
    public void deleteAndGetProcessusTest() throws Exception {
        List<Processus> processusList = LiveDataTestUtil.getValue(this.mDatabase.mProcessusDao().getAllProcessus());
        assertEquals(5, processusList.size());
        assertEquals(processusList.get(0).getId(), ID_1);
        this.mDatabase.mProcessusDao().deleteProcessus(ID_1);
        List<Processus> processusListAfter = LiveDataTestUtil.getValue(this.mDatabase.mProcessusDao().getAllProcessus());
        assertEquals(4, processusListAfter.size());
        assertEquals(processusListAfter.get(0).getId(), ID_2);
        List<Risk> riskList = LiveDataTestUtil.getValue(this.mDatabase.mRiskDao().getAllRisk());
        assertEquals(2, riskList.size());
    }

    @Test
    public void getListOfProcessusWithFmeiLinkTest() throws Exception {
        List<Processus> processusList = LiveDataTestUtil.getValue(this.mDatabase.mProcessusDao().getProcessusAboutFmeiId(ID_1));
        assertEquals(4, processusList.size());
        List<Processus> processusList2 = LiveDataTestUtil.getValue(this.mDatabase.mProcessusDao().getProcessusAboutFmeiId(ID_2));
        assertEquals(1, processusList2.size());
        assertTrue(processusList2.get(0).getName().equals("processus_step_4") && processusList2.get(0).getFmeiId() == ID_2 && processusList2.get(0).getId() == ID_4);
    }



}
