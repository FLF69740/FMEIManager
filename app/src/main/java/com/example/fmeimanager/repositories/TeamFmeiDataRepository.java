package com.example.fmeimanager.repositories;

import android.arch.lifecycle.LiveData;

import com.example.fmeimanager.database.dao.TeamFmeiDao;
import com.example.fmeimanager.models.TeamFmei;

import java.util.List;

public class TeamFmeiDataRepository {

    private final TeamFmeiDao mTeamFmeiDao;

    public TeamFmeiDataRepository(TeamFmeiDao TeamFmeiDao) {mTeamFmeiDao = TeamFmeiDao;}



    //CREATE
    public void createTeamFmei(TeamFmei TeamFmei) {mTeamFmeiDao.insertTeamFmei(TeamFmei);}


    //READ
    public LiveData<List<TeamFmei>> getAllTeamFmei() {return this.mTeamFmeiDao.getTeams();}
    public LiveData<TeamFmei> getTeamFmei(long id) {return this.mTeamFmeiDao.getTeamFmei(id);}

    public LiveData<List<TeamFmei>> getTeamsWithLinkParticipant(long participantId) {
        return this.mTeamFmeiDao.getTeamLinkParticipant(participantId);
    }

    public LiveData<List<TeamFmei>> getTeamsWithLinkFmei(long fmeiId) {
        return this.mTeamFmeiDao.getTeamLinkFmei(fmeiId);
    }


    //UPDATE
    public void updateTeamFmei(TeamFmei TeamFmei) {mTeamFmeiDao.updateTeamFmei(TeamFmei);}


    //DELETE
    public void deleteTeamFmei(long TeamFmeiId) {mTeamFmeiDao.deleteTeamFmei(TeamFmeiId);}
}
