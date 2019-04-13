package com.example.fmeimanager.controllers.navigationPackageF.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.navigationPackageF.adapter.viewholder.TeamFmeiHolder;
import com.example.fmeimanager.models.TeamPanel;

import java.util.List;

public class TeamFmeiAdapter extends RecyclerView.Adapter<TeamFmeiHolder> {

    private List<TeamPanel> mTeamPanelList;

    public TeamFmeiAdapter(List<TeamPanel> teamPanelList) {
        mTeamPanelList = teamPanelList;
    }

    @NonNull
    @Override
    public TeamFmeiHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_team_fmei_recycler_item, viewGroup, false);
        return new TeamFmeiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamFmeiHolder teamFmeiHolder, int i) {
        teamFmeiHolder.setIsRecyclable(false);
        teamFmeiHolder.updateWithAdapterInformation(mTeamPanelList.get(i), i);
    }

    @Override
    public int getItemCount() {
        return mTeamPanelList.size();
    }
}
