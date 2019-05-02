package com.example.fmeimanager.controllers.navigationPackageA.processusTheme.adapters.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.fmeimanager.R;
import com.example.fmeimanager.models.ProcessusPanel;
import com.example.fmeimanager.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProcessListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_process_processItem_title) TextView mProcessIdTitle;
    @BindView(R.id.fragment_process_riskItem_title)TextView mRiskTitle;
    @BindView(R.id.fragment_process_riskItem_id) TextView mRiskId;
    @BindView(R.id.fragment_process_creation_date) TextView mCreationDate;
    @BindView(R.id.fragment_process_participantItem_title) TextView mParticipantTitle;
    @BindView(R.id.fragment_process_ipr) TextView mIpr;
    @BindView(R.id.fragment_process_gravity) TextView mGravity;
    @BindView(R.id.fragment_process_frequency) TextView mFrequency;
    @BindView(R.id.fragment_process_detectability) TextView mDetectability;
    @BindView(R.id.fragment_process_correctiveItem_title) TextView mCorrectiveTitle;

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
                this.riskPanelGone();
            } else {
                mProcessIdTitle.setVisibility(View.GONE);
                String riskTitle;
                if (processusPanel.getTitleRisk().equals(Utils.EMPTY)){
                    riskTitle = mItemView.getContext().getString(R.string.Risk_file_mail_message_line_two) + " " + processusPanel.getRiskId();
                } else {
                    riskTitle = processusPanel.getTitleRisk();
                }
                mRiskTitle.setText(riskTitle);
                String correctiveTitle = "AC " + processusPanel.getCorrectiveIndicator();
                mCorrectiveTitle.setText(correctiveTitle);
                mParticipantTitle.setText(processusPanel.getResponsableRisk());
                mRiskId.setText(String.valueOf(processusPanel.getRiskId()));
                mCreationDate.setText(processusPanel.getCreationDateRisk());
                mIpr.setText(String.valueOf(processusPanel.getIPR()));
                mGravity.setText(String.valueOf(processusPanel.getGravivity()));
                mFrequency.setText(String.valueOf(processusPanel.getFrequencies()));
                mDetectability.setText(String.valueOf(processusPanel.getDetectability()));
            }
        }else {
            mProcessIdTitle.setVisibility(View.GONE);
            this.riskPanelGone();
        }
    }

    private void riskPanelGone(){
        mRiskTitle.setVisibility(View.GONE);
        mCorrectiveTitle.setVisibility(View.GONE);
        mParticipantTitle.setVisibility(View.GONE);
        mRiskId.setVisibility(View.GONE);
        mCreationDate.setVisibility(View.GONE);
        mIpr.setVisibility(View.GONE);
        mGravity.setVisibility(View.GONE);
        mFrequency.setVisibility(View.GONE);
        mDetectability.setVisibility(View.GONE);
    }

}
