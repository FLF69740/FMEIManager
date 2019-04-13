package com.example.fmeimanager.controllers.navigationPackageF.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.fmeimanager.controllers.navigationPackageF.CatalogParticipantFragment;
import com.example.fmeimanager.database.Participant;

import java.util.ArrayList;

public class CatalogParticipantAdapter extends FragmentStatePagerAdapter {

    private ArrayList<ArrayList<Participant>> mListOfParticipantList;
    private ArrayList<String> mListOfSelectedParticipantId;

    public CatalogParticipantAdapter(FragmentManager fm, ArrayList<ArrayList<Participant>> listOfParticipantList, ArrayList<String> selectedParticipant) {
        super(fm);
        mListOfParticipantList = listOfParticipantList;
        mListOfSelectedParticipantId = selectedParticipant;
    }

    public void updateInformation(ArrayList<ArrayList<Participant>> listOfParticipantList, ArrayList<String> selectedParticipant){
        mListOfParticipantList = listOfParticipantList;
        mListOfSelectedParticipantId = selectedParticipant;
    }

    public int getItemPosition(Object object){
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int i) {
        return CatalogParticipantFragment.newInstance(mListOfParticipantList.get(i), mListOfSelectedParticipantId, i);
    }

    @Override
    public int getCount() {
        return mListOfParticipantList.size();
    }
}
