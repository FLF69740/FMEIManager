package com.example.fmeimanager.controllers.navigationPackage1.processusTheme.adapters.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.fmeimanager.R;
import com.example.fmeimanager.models.ProcessusPanel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProcessListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_process_riskItem_title)TextView mRiskIdTitle;
    @BindView(R.id.fragment_process_processItem_title) TextView mProcessIdTitle;
    @BindView(R.id.fragment_process_correctiveItem_title) TextView mCorrectiveTitle;
    @BindView(R.id.fragment_process_participantItem_title) TextView mParticipantTitle;

    private View mItemView;

    public ProcessListViewHolder(@NonNull View itemView) {
        super(itemView);
        mItemView = itemView;
        ButterKnife.bind(this, mItemView);
    }

    public void updateWithAdapterInformation(ProcessusPanel processusPanel){
        if (processusPanel.isProcessusVisible()){
            if (processusPanel.isATittle()) {
                String processusTitle = processusPanel.getProcessusName();
                mProcessIdTitle.setText(processusTitle);
                mRiskIdTitle.setVisibility(View.GONE);
                mCorrectiveTitle.setVisibility(View.GONE);
                mParticipantTitle.setVisibility(View.GONE);
            } else {
                mProcessIdTitle.setVisibility(View.GONE);
                String riskTitle = "RISK " + String.valueOf(processusPanel.getRiskId());
                mRiskIdTitle.setText(riskTitle);
                String correctiveTitle = "AC " + processusPanel.getCorrectiveIndicator();
                mCorrectiveTitle.setText(correctiveTitle);
                mParticipantTitle.setText(processusPanel.getResponsableRisk());
            }
        }else {
                mRiskIdTitle.setVisibility(View.GONE);
                mProcessIdTitle.setVisibility(View.GONE);
                mCorrectiveTitle.setVisibility(View.GONE);
                mParticipantTitle.setVisibility(View.GONE);
        }
    }

}
