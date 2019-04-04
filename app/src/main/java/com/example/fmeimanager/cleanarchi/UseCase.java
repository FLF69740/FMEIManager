package com.example.fmeimanager.cleanarchi;

import android.arch.lifecycle.MediatorLiveData;

import com.example.fmeimanager.database.Fmei;
import com.example.fmeimanager.database.dao.FmeiDao;

import java.util.List;

public class UseCase {

//    private CleanRepoFmei mMyCleanRepoFmei = new CleanRepoFmei();
    private CleanRepoProcessus myCleanRepoProcessus = new CleanRepoProcessus();

    public UseCase() {
  //      mData.addSource(mMyCleanRepoFmei.getAllFmei());
  //      mData.addSource(myCleanRepoProcessus.getAllProcessus());
    }

    public MediatorLiveData<List<CleanViewModel.CleanModel>> mData = new MediatorLiveData<>();

}
