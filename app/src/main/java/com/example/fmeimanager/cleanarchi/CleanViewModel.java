package com.example.fmeimanager.cleanarchi;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class CleanViewModel extends ViewModel {

    private UseCase myUseCase = new UseCase();

    public LiveData<List<CleanModel>> live = myUseCase.mData;

    public static class CleanModel{

    }

}
