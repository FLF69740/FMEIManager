package com.example.fmeimanager.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.fmeimanager.database.Processus;

import java.util.List;

@Dao
public interface ProcessusDao {

    @Query("SELECT * FROM Processus")
    LiveData<List<Processus>> getAllProcessus();

    @Query("SELECT * FROM Processus WHERE processus_id = :id")
    LiveData<Processus> getProcessus(long id);

    @Query("SELECT * FROM Processus WHERE processus_fmeiId = :fmeiId")
    LiveData<List<Processus>> getProcessusAboutFmeiId(long fmeiId);

    @Insert
    long insertProcessus(Processus processus);

    @Update
    int updateProcessus(Processus processus);

    @Query("DELETE FROM Processus WHERE processus_id = :id")
    int deleteProcessus(long id);
}
