package com.FMEA.fmeimanager.controllers.navigationPackageF.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.FMEA.fmeimanager.controllers.navigationPackageF.CatalogParticipantFragment;
import com.FMEA.fmeimanager.database.Participant;

import java.util.ArrayList;

public class CatalogParticipantAdapter extends FragmentPagerAdapter {

    private int mCatalogSize;
    private ArrayList<Participant> mListOfParticipantList;
    private ArrayList<String> mListOfSelectedParticipantId;
    private Long mTeamLeaderId;

    public CatalogParticipantAdapter(FragmentManager fm, ArrayList<Participant> listOfParticipantList, ArrayList<String> selectedParticipant, long teamLeaderId, int catalogSize) {
        super(fm);
        mListOfParticipantList = listOfParticipantList;
        mListOfSelectedParticipantId = selectedParticipant;
        mTeamLeaderId = teamLeaderId;
        mCatalogSize = catalogSize;
    }

    @Override
    public Fragment getItem(int i) {
        return CatalogParticipantFragment.newInstance(mListOfParticipantList, mListOfSelectedParticipantId, mTeamLeaderId, i);
    }

    @Override
    public int getCount() {
        return mCatalogSize;
    }

}
