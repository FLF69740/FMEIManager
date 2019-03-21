package com.example.fmeimanager.controllers.navigationPackage1.processusTheme.adapters.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.fmeimanager.R;
import com.example.fmeimanager.models.Processus;
import com.example.fmeimanager.models.ProcessusPanel;
import com.example.fmeimanager.models.Risk;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProcessListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_process_riskItem_title)TextView mRiskIdTitle;
    @BindView(R.id.fragment_process_processItem_title) TextView mProcessIdTitle;

    private View mItemView;

    public ProcessListViewHolder(@NonNull View itemView) {
        super(itemView);
        mItemView = itemView;
        ButterKnife.bind(this, mItemView);
    }
/*
    public void updateWithAdapterInformation(Risk risk, Processus processus, boolean single){
        if (processus.isVisible()) {
            if (single) {
                String processusTitle = processus.getName();
                mProcessIdTitle.setText(processusTitle);
                mRiskIdTitle.setVisibility(View.GONE);
            } else {
                mProcessIdTitle.setVisibility(View.GONE);
                String riskTitle = "RISK " + String.valueOf(risk.getId());
                mRiskIdTitle.setText(riskTitle);
            }
        } else {
            mRiskIdTitle.setVisibility(View.GONE);
            mProcessIdTitle.setVisibility(View.GONE);
        }
    }
*/
    public void updateWithAdapterInformation(ProcessusPanel processusPanel){
        if (processusPanel.isProcessusVisible()){
            if (processusPanel.isATittle()) {
                String processusTitle = processusPanel.getProcessusName();
                mProcessIdTitle.setText(processusTitle);
                mRiskIdTitle.setVisibility(View.GONE);
            } else {
                mProcessIdTitle.setVisibility(View.GONE);
                String riskTitle = "RISK " + String.valueOf(processusPanel.getRiskId());
                mRiskIdTitle.setText(riskTitle);
            }
        }else {
                mRiskIdTitle.setVisibility(View.GONE);
                mProcessIdTitle.setVisibility(View.GONE);
        }
    }

}
