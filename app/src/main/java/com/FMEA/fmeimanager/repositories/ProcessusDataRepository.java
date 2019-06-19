package com.FMEA.fmeimanager.repositories;

import android.arch.lifecycle.LiveData;

import com.FMEA.fmeimanager.database.dao.ProcessusDao;
import com.FMEA.fmeimanager.database.Processus;

import java.util.List;

public class ProcessusDataRepository {

    private final ProcessusDao mProcessusDao;

    public ProcessusDataRepository(ProcessusDao ProcessusDao) {mProcessusDao = ProcessusDao;}



    //CREATE
    public void createProcessus(Processus Processus) {mProcessusDao.insertProcessus(Processus);}


    //READ
    public LiveData<List<Processus>> getAllProcessus() {return this.mProcessusDao.getAllProcessus();}
    public LiveData<Processus> getProcessus(long id) {return this.mProcessusDao.getProcessus(id);}

    public LiveData<List<Processus>> getProcessussListForFmei(long fmeaId) {
        return this.mProcessusDao.getProcessusAboutFmeiId(fmeaId);
    }


    //UPDATE
    public void updateProcessus(Processus Processus) {mProcessusDao.updateProcessus(Processus);}


    //DELETE
    public void deleteProcessus(long ProcessusId) {mProcessusDao.deleteProcessus(ProcessusId);}
}
