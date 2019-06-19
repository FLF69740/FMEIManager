package com.FMEA.fmeimanager.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.FMEA.fmeimanager.database.Risk;

import java.util.List;

@Dao
public interface RiskDao {

    @Query("SELECT * FROM Risk")
    LiveData<List<Risk>> getAllRisk();

    @Query("SELECT * FROM Risk WHERE risk_id = :id")
    LiveData<Risk> getRisk(long id);

    @Query("SELECT * FROM Risk WHERE risk_participantId = :participantId")
    LiveData<List<Risk>> getRiskAboutParticipantId(long participantId);

    @Query("SELECT * FROM Risk WHERE risk_processusId = :processusId")
    LiveData<List<Risk>> getRiskAboutProcessusId(long processusId);

    @Insert
    long insertRisk(Risk risk);

    @Update
    int updateRisk(Risk risk);

    @Query("DELETE FROM Risk WHERE risk_id = :id")
    int deleteRisk(long id);
}
