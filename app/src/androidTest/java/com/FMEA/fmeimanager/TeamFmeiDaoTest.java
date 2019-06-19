package com.FMEA.fmeimanager;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.FMEA.fmeimanager.database.FmeiManagerDatabase;
import com.FMEA.fmeimanager.database.Fmei;
import com.FMEA.fmeimanager.database.Participant;
import com.FMEA.fmeimanager.database.TeamFmei;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class TeamFmeiDaoTest {

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

    //TEAM FMEI
    private static TeamFmei TF_DEMO_1(){return new TeamFmei(ID_1, ID_1);}
    private static TeamFmei TF_DEMO_2(){return new TeamFmei(ID_1, ID_2);}
    private static TeamFmei TF_DEMO_3(){return new TeamFmei(ID_1, ID_3);}
    private static TeamFmei TF_DEMO_4(){return new TeamFmei(ID_2, ID_1);}
    private static TeamFmei TF_DEMO_5(){return new TeamFmei(ID_2, ID_2);}


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                FmeiManagerDatabase.class)
                .allowMainThreadQueries()
                .build();

        this.mDatabase.mFmeiDao().createFmei(FMEI_DEMO_1());
        this.mDatabase.mFmeiDao().createFmei(FMEI_DEMO_2());
        this.mDatabase.mParticipantDao().insertParticipant(PARTICIPANT_DEMO_1());
        this.mDatabase.mParticipantDao().insertParticipant(PARTICIPANT_DEMO_2());
        this.mDatabase.mParticipantDao().insertParticipant(PARTICIPANT_DEMO_3());
        this.mDatabase.mTeamFmeiDao().insertTeamFmei(TF_DEMO_1());
        this.mDatabase.mTeamFmeiDao().insertTeamFmei(TF_DEMO_2());
        this.mDatabase.mTeamFmeiDao().insertTeamFmei(TF_DEMO_3());
        this.mDatabase.mTeamFmeiDao().insertTeamFmei(TF_DEMO_4());
        this.mDatabase.mTeamFmeiDao().insertTeamFmei(TF_DEMO_5());

    }

    @After
    public void closeDb() throws Exception {
        mDatabase.close();
    }

    //TEST : FmeiDao

    @Test
    public void getAllFmeis() throws Exception {
        List<Fmei> fmeiList = LiveDataTestUtil.getValue(this.mDatabase.mFmeiDao().getFmeis());
        assertEquals(2, fmeiList.size());
    }

    @Test
    public void insertAndGetFmeiTest() throws Exception {
        Fmei fmei1 = LiveDataTestUtil.getValue(this.mDatabase.mFmeiDao().getFmei(ID_1));
        Fmei fmei2 = LiveDataTestUtil.getValue(this.mDatabase.mFmeiDao().getFmei(ID_2));

        assertTrue(fmei1.getName().equals(FMEI_1_NAME) && fmei1.getId() == ID_1);
        assertTrue(fmei2.getName().equals(FMEI_2_NAME) && fmei2.getId() == ID_2);
    }

    @Test
    public void updateAndGetFmeiTest() throws Exception {
        Fmei fmei = LiveDataTestUtil.getValue(this.mDatabase.mFmeiDao().getFmei(ID_1));
        assertTrue(fmei.getName().equals(FMEI_1_NAME) && fmei.getId() == ID_1);

        fmei.setName("New Name");
        this.mDatabase.mFmeiDao().updateFmei(fmei);
        Fmei fmeiUpdated = LiveDataTestUtil.getValue(this.mDatabase.mFmeiDao().getFmei(ID_1));
        assertEquals("New Name", fmeiUpdated.getName());
    }

    //TEST : ParticipantDao

    @Test
    public void getAllParticipants() throws Exception {
        List<Participant> participantList = LiveDataTestUtil.getValue(this.mDatabase.mParticipantDao().getParticipants());
        assertEquals(3, participantList.size());
    }

    @Test
    public void insertAndGetParticipantTest() throws Exception {
        Participant participant1 = LiveDataTestUtil.getValue(this.mDatabase.mParticipantDao().getParticipant(ID_1));
        Participant participant2 = LiveDataTestUtil.getValue(this.mDatabase.mParticipantDao().getParticipant(ID_2));
        Participant participant3 = LiveDataTestUtil.getValue(this.mDatabase.mParticipantDao().getParticipant(ID_3));

        assertTrue(participant1.getName().equals(PARTICIPANT_1_NAME) && participant1.getForname().equals(PARTICIPANT_1_FORNAME) && participant1.getId() == ID_1);
        assertTrue(participant2.getName().equals(PARTICIPANT_2_NAME) && participant2.getForname().equals(PARTICIPANT_2_FORNAME) && participant2.getId() == ID_2);
        assertTrue(participant3.getName().equals(PARTICIPANT_3_NAME) && participant3.getForname().equals(PARTICIPANT_3_FORNAME) && participant3.getId() == ID_3);
    }

    @Test
    public void updateAndGetParticipantTest() throws Exception {
        Participant participant = LiveDataTestUtil.getValue(this.mDatabase.mParticipantDao().getParticipant(ID_1));
        assertTrue(participant.getName().equals(PARTICIPANT_1_NAME) && participant.getForname().equals(PARTICIPANT_1_FORNAME) && participant.getId() == ID_1);

        participant.setForname("John");
        participant.setName("Doe");
        this.mDatabase.mParticipantDao().updateParticipant(participant);
        Participant participantUpdated = LiveDataTestUtil.getValue(this.mDatabase.mParticipantDao().getParticipant(ID_1));
        assertEquals("Doe", participantUpdated.getName());
        assertEquals("John", participantUpdated.getForname());
    }

    //TEST : TeamFmeiDao

    @Test
    public void getAllTeams() throws Exception {
        List<TeamFmei> teamFmeiList = LiveDataTestUtil.getValue(this.mDatabase.mTeamFmeiDao().getTeams());
        assertEquals(5, teamFmeiList.size());
    }

    @Test
    public void insertAndGetTeamFmeiTest() throws Exception {
        List<Fmei> fmeiList = LiveDataTestUtil.getValue(this.mDatabase.mFmeiDao().getFmeis());
        List<Participant> participantList = LiveDataTestUtil.getValue(this.mDatabase.mParticipantDao().getParticipants());
        TeamFmei teamFmei1 = LiveDataTestUtil.getValue(this.mDatabase.mTeamFmeiDao().getTeamFmei(ID_1));
        TeamFmei teamFmei2 = LiveDataTestUtil.getValue(this.mDatabase.mTeamFmeiDao().getTeamFmei(ID_2));
        TeamFmei teamFmei3 = LiveDataTestUtil.getValue(this.mDatabase.mTeamFmeiDao().getTeamFmei(ID_3));
        TeamFmei teamFmei4 = LiveDataTestUtil.getValue(this.mDatabase.mTeamFmeiDao().getTeamFmei(ID_4));
        TeamFmei teamFmei5 = LiveDataTestUtil.getValue(this.mDatabase.mTeamFmeiDao().getTeamFmei(ID_5));

        assertTrue(teamFmei1.getFmeiId() == fmeiList.get(0).getId() && teamFmei1.getParticipantId() == participantList.get(0).getId() && teamFmei1.getTeamFmeiId() == ID_1);
        assertTrue(teamFmei2.getFmeiId() == fmeiList.get(0).getId() && teamFmei2.getParticipantId() == participantList.get(1).getId() && teamFmei2.getTeamFmeiId() == ID_2);
        assertTrue(teamFmei3.getFmeiId() == fmeiList.get(0).getId() && teamFmei3.getParticipantId() == participantList.get(2).getId() && teamFmei3.getTeamFmeiId() == ID_3);
        assertTrue(teamFmei4.getFmeiId() == fmeiList.get(1).getId() && teamFmei4.getParticipantId() == participantList.get(0).getId() && teamFmei4.getTeamFmeiId() == ID_4);
        assertTrue(teamFmei5.getFmeiId() == fmeiList.get(1).getId() && teamFmei5.getParticipantId() == participantList.get(1).getId() && teamFmei5.getTeamFmeiId() == ID_5);
    }

    @Test
    public void updateAndGetTeamFmeiTest() throws Exception {
        TeamFmei teamFmei1 = LiveDataTestUtil.getValue(this.mDatabase.mTeamFmeiDao().getTeamFmei(ID_1));
        teamFmei1.setParticipantId(4);
        teamFmei1.setFmeiId(8);
        this.mDatabase.mTeamFmeiDao().updateTeamFmei(teamFmei1);
        TeamFmei teamFmeiUpdated = LiveDataTestUtil.getValue(this.mDatabase.mTeamFmeiDao().getTeamFmei(ID_1));
        assertTrue(teamFmeiUpdated.getFmeiId() == 8 && teamFmeiUpdated.getParticipantId() == 4);
    }

    @Test
    public void deleteAndGetTeamFmeiTest() throws Exception {
        List<TeamFmei> teamFmeiList = LiveDataTestUtil.getValue(this.mDatabase.mTeamFmeiDao().getTeams());
        assertEquals(5, teamFmeiList.size());
        assertEquals(teamFmeiList.get(0).getTeamFmeiId(), ID_1);
        this.mDatabase.mTeamFmeiDao().deleteTeamFmei(ID_1);
        List<TeamFmei> teamFmeiListAfter = LiveDataTestUtil.getValue(this.mDatabase.mTeamFmeiDao().getTeams());
        assertEquals(4, teamFmeiListAfter.size());
        assertEquals(teamFmeiListAfter.get(0).getTeamFmeiId(), ID_2);
    }

    @Test
    public void getListOfParticipantWithFmeiLinkTest() throws Exception {
        List<TeamFmei> teamFmeiList = LiveDataTestUtil.getValue(this.mDatabase.mTeamFmeiDao().getTeamLinkFmei(ID_2));
        assertEquals(2, teamFmeiList.size());
        assertTrue( teamFmeiList.get(0).getFmeiId() == ID_2 && teamFmeiList.get(0).getParticipantId() == ID_1 && teamFmeiList.get(0).getTeamFmeiId() == ID_4);
        assertTrue( teamFmeiList.get(1).getFmeiId() == ID_2 && teamFmeiList.get(1).getParticipantId() == ID_2 && teamFmeiList.get(1).getTeamFmeiId() == ID_5);
    }

    @Test
    public void getListOfFmeiWithParticipantLinkTest() throws Exception {
        List<TeamFmei> teamFmeiList = LiveDataTestUtil.getValue(this.mDatabase.mTeamFmeiDao().getTeamLinkParticipant(ID_1));
        assertEquals(2, teamFmeiList.size());
        assertTrue( teamFmeiList.get(0).getFmeiId() == ID_1 && teamFmeiList.get(0).getParticipantId() == ID_1 && teamFmeiList.get(0).getTeamFmeiId() == ID_1);
        assertTrue( teamFmeiList.get(1).getFmeiId() == ID_2 && teamFmeiList.get(1).getParticipantId() == ID_1 && teamFmeiList.get(1).getTeamFmeiId() == ID_4);
    }



}
