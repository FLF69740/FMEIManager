package com.example.fmeimanager.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.fmeimanager.database.Participant;

import java.util.List;

@Dao
public interface ParticipantDao {

    @Query("SELECT * FROM Participant")
    LiveData<List<Participant>> getParticipants();

    @Query("SELECT * FROM Participant WHERE participant_id = :id")
    LiveData<Participant> getParticipant(long id);

    @Insert long insertParticipant(Participant participant);

    @Update int updateParticipant(Participant participant);
}
