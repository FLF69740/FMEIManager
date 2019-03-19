package com.example.fmeimanager.adapters.viewholders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.fmeimanager.R;
import com.example.fmeimanager.models.Risk;

import java.util.List;

public class ProcessListAdapter extends RecyclerView.Adapter<ProcessListViewHolder>{

    private List<Risk> mRiskList;
    private List<Integer> mProcessusStepList;
    private List<Boolean> mStepSingleTitle;

    public ProcessListAdapter(List<Risk> riskList, List<Integer> processusList) {
        mProcessusStepList = processusList;
        mRiskList = riskList;
    }

    public void setProcessusList(List<Risk> riskList, List<Integer> processusList, List<Boolean> stepSingleTitle){
        mProcessusStepList = processusList;
        mRiskList = riskList;
        mStepSingleTitle = stepSingleTitle;
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
        processListViewHolder.updateWithAdapterInformation(this.mRiskList.get(i), this.mProcessusStepList.get(i), this.mStepSingleTitle.get(i));
    }

    @Override
    public int getItemCount() {
        return mRiskList.size();
    }

    public Risk getProcessus(int position) {
        return  mRiskList.get(position);
    }


}
