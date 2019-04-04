package com.example.fmeimanager.cleanarchi;


import android.arch.lifecycle.LiveData;

import com.example.fmeimanager.database.dao.FmeiDao;
import com.example.fmeimanager.database.Fmei;

import java.util.List;

public class CleanRepoFmei {

    private final FmeiDao mFmeiDao;


    public CleanRepoFmei(FmeiDao fmeiDao) {
        mFmeiDao = fmeiDao;
    }



    //CREATE
    public void createFmei(Fmei fmei) {mFmeiDao.createFmei(fmei);}


    //READ
    public LiveData<List<Fmei>> getAllFmei() {return this.mFmeiDao.getFmeis();}
    public LiveData<Fmei> getFmei(long id) {return this.mFmeiDao.getFmei(id);}


    //UPDATE
    public void updateFmei(Fmei fmei) {mFmeiDao.updateFmei(fmei);}


    //DELETE

}
