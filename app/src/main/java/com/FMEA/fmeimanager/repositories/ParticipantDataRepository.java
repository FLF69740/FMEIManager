package com.FMEA.fmeimanager.repositories;

import android.arch.lifecycle.LiveData;

import com.FMEA.fmeimanager.database.dao.ParticipantDao;
import com.FMEA.fmeimanager.database.Participant;

import java.util.List;

public class ParticipantDataRepository {

    private final ParticipantDao mParticipantDao;

    public ParticipantDataRepository(ParticipantDao ParticipantDao) {mParticipantDao = ParticipantDao;}



    //CREATE
    public void createParticipant(Participant Participant) {mParticipantDao.insertParticipant(Participant);}


    //READ
    public LiveData<List<Participant>> getAllParticipant() {return this.mParticipantDao.getParticipants();}
    public LiveData<Participant> getParticipant(long id) {return this.mParticipantDao.getParticipant(id);}


    //UPDATE
    public void updateParticipant(Participant Participant) {mParticipantDao.updateParticipant(Participant);}


    //DELETE

}
