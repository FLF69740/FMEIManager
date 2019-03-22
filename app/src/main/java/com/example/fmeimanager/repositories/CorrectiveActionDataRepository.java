package com.example.fmeimanager.repositories;

import android.arch.lifecycle.LiveData;


import com.example.fmeimanager.database.dao.CorrectiveActionDao;
import com.example.fmeimanager.database.CorrectiveAction;

import java.util.List;

public class CorrectiveActionDataRepository {

    private final CorrectiveActionDao mCorrectiveActionDao;

    public CorrectiveActionDataRepository(CorrectiveActionDao correctiveActionDao) {mCorrectiveActionDao = correctiveActionDao;}



    //CREATE
    public void createCorrectiveAction(CorrectiveAction correctiveAction) {mCorrectiveActionDao.insertCorrectiveAction(correctiveAction);}


    //READ
    public LiveData<List<CorrectiveAction>> getAllCorrectiveAction() {return this.mCorrectiveActionDao.getAllCorrectiveAction();}
    public LiveData<CorrectiveAction> getCorrectiveAction(long id) {return this.mCorrectiveActionDao.getCorrectiveAction(id);}

    public LiveData<List<CorrectiveAction>> getCorrectiveActionsListForParticipant(long participantId) {
        return this.mCorrectiveActionDao.getCorrectiveActionAboutParticipantId(participantId);
    }

    public LiveData<List<CorrectiveAction>> getCorrectiveActionsListForRisk(long riskId) {
        return this.mCorrectiveActionDao.getCorrectiveActionAboutRiskId(riskId);
    }


    //UPDATE
    public void updateCorrectiveAction(CorrectiveAction correctiveAction) {mCorrectiveActionDao.updateCorrectiveAction(correctiveAction);}


    //DELETE
    public void deleteCorrectiveAction(long correctiveActionId) {mCorrectiveActionDao.deleteCorrectiveAction(correctiveActionId);}



}
