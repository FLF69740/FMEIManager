package com.FMEA.fmeimanager.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.FMEA.fmeimanager.database.CorrectiveAction;

import java.util.List;

@Dao
public interface CorrectiveActionDao {

    @Query("SELECT * FROM CorrectiveAction")
    LiveData<List<CorrectiveAction>> getAllCorrectiveAction();

    @Query("SELECT * FROM CorrectiveAction WHERE corrective_id = :id")
    LiveData<CorrectiveAction> getCorrectiveAction(long id);

    @Query("SELECT * FROM CorrectiveAction WHERE corrective_participantId = :participantId")
    LiveData<List<CorrectiveAction>> getCorrectiveActionAboutParticipantId(long participantId);

    @Query("SELECT * FROM CorrectiveAction WHERE corrective_riskId = :riskId")
    LiveData<CorrectiveAction> getCorrectiveActionAboutRiskId(long riskId);

    @Insert
    long insertCorrectiveAction(CorrectiveAction correctiveAction);

    @Update
    int updateCorrectiveAction(CorrectiveAction correctiveAction);

    @Query("DELETE FROM CorrectiveAction WHERE corrective_id = :id")
    int deleteCorrectiveAction(long id);
}
