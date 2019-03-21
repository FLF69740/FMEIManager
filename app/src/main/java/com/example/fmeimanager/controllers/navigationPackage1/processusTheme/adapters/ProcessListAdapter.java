package com.example.fmeimanager.controllers.navigationPackage1.processusTheme.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.navigationPackage1.processusTheme.adapters.viewholders.ProcessListViewHolder;
import com.example.fmeimanager.models.ProcessusPanel;

import java.util.List;

public class ProcessListAdapter extends RecyclerView.Adapter<ProcessListViewHolder>{

    private List<ProcessusPanel> mProcessusPanelList;

    public ProcessListAdapter(List<ProcessusPanel> processusPanelList) {
        mProcessusPanelList = processusPanelList;
    }

    public void setProcessusList(List<ProcessusPanel> processusPanelList){
        mProcessusPanelList = processusPanelList;
    }

    @NonNull
    @Override
    public ProcessListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_process_recyclerview_item, viewGroup, false);
        return new ProcessListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProcessListViewHolder processListViewHolder, int i) {
        processListViewHolder.setIsRecyclable(false);
        processListViewHolder.updateWithAdapterInformation(this.mProcessusPanelList.get(i));
    }

    @Override
    public int getItemCount() {
        return mProcessusPanelList.size();
    }

    public ProcessusPanel getProcessusPanel(int position){
        return mProcessusPanelList.get(position);
    }


}
