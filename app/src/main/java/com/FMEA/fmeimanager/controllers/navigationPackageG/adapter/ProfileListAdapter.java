package com.FMEA.fmeimanager.controllers.navigationPackageG.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.controllers.navigationPackageG.adapter.viewholder.ProfileListViewHolder;
import com.FMEA.fmeimanager.database.Participant;

import java.util.List;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListViewHolder> {

    private List<Participant> mParticipantList;
    private int mSelectedParticipant;

    public ProfileListAdapter(List<Participant> participantList) {
        mParticipantList = participantList;
    }

    @NonNull
    @Override
    public ProfileListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_profile_section_recyclerview_item, viewGroup, false);
        return new ProfileListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileListViewHolder profileListViewHolder, int i) {
        boolean selected = mSelectedParticipant == i;
        profileListViewHolder.updateWithAdapterInformation(this.mParticipantList.get(i), selected);
    }

    @Override
    public int getItemCount() {
        return this.mParticipantList.size();
    }

    public Participant getParticipant(int position){
        return mParticipantList.get(position);
    }

    public void setSelectedParticipant(int position){
        mSelectedParticipant = position;
    }


}
