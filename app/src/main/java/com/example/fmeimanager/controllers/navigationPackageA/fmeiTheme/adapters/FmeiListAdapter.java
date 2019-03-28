package com.example.fmeimanager.controllers.navigationPackageA.fmeiTheme.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.navigationPackageA.fmeiTheme.adapters.viewholders.FmeiListViewHolder;
import com.example.fmeimanager.database.Fmei;
import com.example.fmeimanager.models.FmeiPanel;

import java.util.List;

public class FmeiListAdapter extends RecyclerView.Adapter<FmeiListViewHolder> {

    private List<FmeiPanel> mFmeiPanelList;
    private int mSelectedFmei;

    public FmeiListAdapter(List<FmeiPanel> fmeiPanels) {
        mFmeiPanelList = fmeiPanels;
    }

    public void setFmeiList(List<FmeiPanel> fmeiPanels) {
        mFmeiPanelList = fmeiPanels;
    }

    @NonNull
    @Override
    public FmeiListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_fmei_recyclerview_item, viewGroup, false);
        return new FmeiListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FmeiListViewHolder fmeiListViewHolder, int i) {
        boolean selected = mSelectedFmei == i;
        fmeiListViewHolder.updateWithAdapterInformation(this.mFmeiPanelList.get(i), selected);
    }

    @Override
    public int getItemCount() {
        return this.mFmeiPanelList.size();
    }

    public FmeiPanel getFmei(int position){
        return mFmeiPanelList.get(position);
    }

    public void setSelectedFmei(int position){
        mSelectedFmei = position;
    }


}