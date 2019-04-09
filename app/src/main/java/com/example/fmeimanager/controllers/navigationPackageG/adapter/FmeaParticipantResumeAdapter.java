package com.example.fmeimanager.controllers.navigationPackageG.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.navigationPackageG.adapter.viewholder.FmeaParticipantResumeHolder;

import java.util.List;

public class FmeaParticipantResumeAdapter extends RecyclerView.Adapter<FmeaParticipantResumeHolder> {

    private List<String> mFmeaListString;
    private List<Boolean> mTeamLeaderListBoolean;

    public FmeaParticipantResumeAdapter(List<String> fmeaListString, List<Boolean> teamLeaderListBoolean) {
        mFmeaListString = fmeaListString;
        mTeamLeaderListBoolean = teamLeaderListBoolean;
    }

    @NonNull
    @Override
    public FmeaParticipantResumeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_profile_fmea_list_participant_resume_item, viewGroup, false);
        return new FmeaParticipantResumeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FmeaParticipantResumeHolder fmeaParticipantResumeHolder, int i) {
        fmeaParticipantResumeHolder.updateWithAdapterInformation(mFmeaListString.get(i), mTeamLeaderListBoolean.get(i));
    }

    @Override
    public int getItemCount() {
        return mFmeaListString.size();
    }
}
