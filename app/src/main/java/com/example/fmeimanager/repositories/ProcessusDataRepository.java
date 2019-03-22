package com.example.fmeimanager.repositories;

import android.arch.lifecycle.LiveData;

import com.example.fmeimanager.database.dao.ProcessusDao;
import com.example.fmeimanager.database.Processus;

import java.util.List;

public class ProcessusDataRepository {

    private final ProcessusDao mProcessusDao;

    public ProcessusDataRepository(ProcessusDao ProcessusDao) {mProcessusDao = ProcessusDao;}



    //CREATE
    public void createProcessus(Processus Processus) {mProcessusDao.insertProcessus(Processus);}


    //READ
    public LiveData<List<Processus>> getAllProcessus() {return this.mProcessusDao.getAllProcessus();}
    public LiveData<Processus> getProcessus(long id) {return this.mProcessusDao.getProcessus(id);}

    public LiveData<List<Processus>> getProcessussListForFmei(long riskId) {
        return this.mProcessusDao.getProcessusAboutFmeiId(riskId);
    }


    //UPDATE
    public void updateProcessus(Processus Processus) {mProcessusDao.updateProcessus(Processus);}


    //DELETE
    public void deleteProcessus(long ProcessusId) {mProcessusDao.deleteProcessus(ProcessusId);}
}
