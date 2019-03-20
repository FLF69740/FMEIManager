package com.example.fmeimanager.adapters.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.fmeimanager.R;
import com.example.fmeimanager.models.Processus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProcessusBuilderViewHolder extends RecyclerView.ViewHolder {

    private View mItemView;
    @BindView(R.id.fragment_processus_builder_processus_step_title) TextView mProcessusTitle;

    public ProcessusBuilderViewHolder(@NonNull View itemView) {
        super(itemView);
        mItemView = itemView;
        ButterKnife.bind(this, mItemView);
    }

    public void updateWithAdapterInformation(Processus processus){
        String processusTitle = "PROCESSUS " + String.valueOf(processus.getId());
        mProcessusTitle.setText(processusTitle);
    }



}
