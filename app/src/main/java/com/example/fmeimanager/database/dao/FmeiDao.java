package com.example.fmeimanager.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.fmeimanager.database.Fmei;

import java.util.List;

@Dao
public interface FmeiDao {

    @Query("SELECT * FROM Fmei")
    LiveData<List<Fmei>> getFmeis();

    @Query("SELECT * FROM Fmei WHERE fmei_id = :id")
    LiveData<Fmei> getFmei(long id);

    @Insert long createFmei(Fmei fmei);

    @Update int updateFmei(Fmei fmei);
}
