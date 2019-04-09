package com.example.fmeimanager.controllers.navigationPackageG.adapter.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.fmeimanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FmeaParticipantResumeHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_profile_fmea_item) TextView mFmeaName;
    @BindView(R.id.fragment_profile_isleader_item) TextView mTeamLeaderMessage;

    public FmeaParticipantResumeHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithAdapterInformation(String fmeaName, boolean isTeamLeader){
        mFmeaName.setText(fmeaName);
        if (isTeamLeader){
            mTeamLeaderMessage.setVisibility(View.VISIBLE);
        }else {
            mTeamLeaderMessage.setVisibility(View.INVISIBLE);
        }
    }
}
