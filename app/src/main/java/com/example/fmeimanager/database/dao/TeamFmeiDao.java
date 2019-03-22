package com.example.fmeimanager.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.fmeimanager.database.TeamFmei;

import java.util.List;

@Dao
public interface TeamFmeiDao {

    @Query("SELECT * FROM TeamFmei")
    LiveData<List<TeamFmei>> getTeams();

    @Query("SELECT * FROM TeamFmei WHERE team_fmei_id = :teamFmeiId")
    LiveData<TeamFmei> getTeamFmei(long teamFmeiId);

    @Query("SELECT * FROM TeamFmei WHERE team_fmei_fmei_id = :fmeiId")
    LiveData<List<TeamFmei>> getTeamLinkFmei(long fmeiId);

    @Query("SELECT * FROM TeamFmei WHERE team_fmei_participant_id = :participantId")
    LiveData<List<TeamFmei>> getTeamLinkParticipant(long participantId);

    @Insert long insertTeamFmei(TeamFmei teamFmei);

    @Update int updateTeamFmei(TeamFmei teamFmei);

    @Query("DELETE FROM TeamFmei WHERE team_fmei_id = :id")
    int deleteTeamFmei(long id);
}
