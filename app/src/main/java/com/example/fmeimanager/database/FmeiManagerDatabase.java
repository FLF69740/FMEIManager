package com.example.fmeimanager.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.fmeimanager.database.dao.CorrectiveActionDao;
import com.example.fmeimanager.database.dao.FmeiDao;
import com.example.fmeimanager.database.dao.ParticipantDao;
import com.example.fmeimanager.database.dao.ProcessusDao;
import com.example.fmeimanager.database.dao.RiskDao;
import com.example.fmeimanager.database.dao.TeamFmeiDao;
import com.example.fmeimanager.utils.Utils;

@Database(entities = {
        Fmei.class,
        TeamFmei.class,
        Participant.class,
        Processus.class,
        Risk.class,
        CorrectiveAction.class
}, version = 1, exportSchema = false)
public abstract class FmeiManagerDatabase extends RoomDatabase {

    private static final String DEFAULT_USER_NAME = "DOE";
    public static final String DEFAULT_USER_FORNAME = "John";

    //SINGLETON
    private static volatile FmeiManagerDatabase INSTANCE;

    //DAO
    public abstract FmeiDao mFmeiDao();
    public abstract TeamFmeiDao mTeamFmeiDao();
    public abstract ParticipantDao mParticipantDao();
    public abstract RiskDao mRiskDao();
    public abstract ProcessusDao mProcessusDao();
    public abstract CorrectiveActionDao mCorrectiveActionDao();

    //INSTANCE
    public static FmeiManagerDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (FmeiManagerDatabase.class){
                if (INSTANCE == null){
                 //   context.getApplicationContext().deleteDatabase("StartDatabase");
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FmeiManagerDatabase.class, "StartDatabase")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase(){
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues contentValues = new ContentValues();
                contentValues.put("mName", DEFAULT_USER_NAME);
                contentValues.put("mForname", DEFAULT_USER_FORNAME);
                contentValues.put("mFunction", Utils.EMPTY);
                contentValues.put("mMail", Utils.EMPTY);
                contentValues.put("mTel", Utils.EMPTY);
                contentValues.put("mActivated", true);
                db.insert("Participant", OnConflictStrategy.IGNORE, contentValues);
            }
        };
    }



}
