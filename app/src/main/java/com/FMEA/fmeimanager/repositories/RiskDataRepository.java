package com.FMEA.fmeimanager.repositories;

import android.arch.lifecycle.LiveData;

import com.FMEA.fmeimanager.database.dao.RiskDao;
import com.FMEA.fmeimanager.database.Risk;

import java.util.List;

public class RiskDataRepository {

    private final RiskDao mRiskDao;

    public RiskDataRepository(RiskDao RiskDao) {mRiskDao = RiskDao;}



    //CREATE
    public void createRisk(Risk Risk) {mRiskDao.insertRisk(Risk);}


    //READ
    public LiveData<List<Risk>> getAllRisk() {return this.mRiskDao.getAllRisk();}
    public LiveData<Risk> getRisk(long id) {return this.mRiskDao.getRisk(id);}

    public LiveData<List<Risk>> getRisksListForParticipant(long participantId) {
        return this.mRiskDao.getRiskAboutParticipantId(participantId);
    }

    public LiveData<List<Risk>> getRisksListForProcessus(long processusId) {
        return this.mRiskDao.getRiskAboutProcessusId(processusId);
    }


    //UPDATE
    public void updateRisk(Risk Risk) {mRiskDao.updateRisk(Risk);}


    //DELETE
    public void deleteRisk(long RiskId) {mRiskDao.deleteRisk(RiskId);}
}
