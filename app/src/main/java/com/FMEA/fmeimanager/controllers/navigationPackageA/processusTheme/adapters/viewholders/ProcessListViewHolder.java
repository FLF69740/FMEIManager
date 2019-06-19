package com.FMEA.fmeimanager.controllers.navigationPackageA.processusTheme.adapters.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.controllers.navigationPackageA.processusTheme.drawing.ProcessDashCustomBody;
import com.FMEA.fmeimanager.controllers.navigationPackageA.processusTheme.drawing.ProcessDashCustomTitle;
import com.FMEA.fmeimanager.models.ProcessusPanel;
import com.FMEA.fmeimanager.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProcessListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_process_processItem_title) TextView mProcessIdTitle;
    @BindView(R.id.fragment_process_riskItem_title)TextView mRiskTitle;
    @BindView(R.id.fragment_process_riskItem_id) TextView mRiskId;
    @BindView(R.id.fragment_process_creation_date) TextView mCreationDate;
    @BindView(R.id.fragment_process_ipr) TextView mIpr;
    @BindView(R.id.fragment_process_correctiveItem_title) TextView mCorrectiveTitle;
    @BindView(R.id.fragment_process_title_mask) ProcessDashCustomTitle mTitleMask;
    @BindView(R.id.fragment_process_body_mask) ProcessDashCustomBody mBodyMask;

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
                // textview
                mProcessIdTitle.setText(processusTitle);
                // canvas
                mTitleMask.setTitle(processusTitle);
                this.riskPanelGone();
            } else {
                mProcessIdTitle.setVisibility(View.GONE);
                String riskTitle;
                if (processusPanel.getTitleRisk().equals(Utils.EMPTY)){
                    riskTitle = mItemView.getContext().getString(R.string.Risk_file_mail_message_line_two) + " " + processusPanel.getRiskId();
                } else {
                    riskTitle = processusPanel.getTitleRisk();
                }
                mBodyMask.actualise(processusPanel.getGravivity(), processusPanel.getFrequencies(), processusPanel.getDetectability(), processusPanel.getCorrectiveIndicator(),
                        processusPanel.getRiskId(), processusPanel.getTitleRisk(), processusPanel.getResponsableRisk());
                mRiskTitle.setText(riskTitle);
                String correctiveTitle = "AC " + processusPanel.getCorrectiveIndicator();
                mCorrectiveTitle.setText(correctiveTitle);
                mRiskId.setText(String.valueOf(processusPanel.getRiskId()));
                mCreationDate.setText(processusPanel.getCreationDateRisk());
                mIpr.setText(String.valueOf(processusPanel.getIPR()));
            }
        }else {
            mProcessIdTitle.setVisibility(View.GONE);
            this.riskPanelGone();
        }
    }

    private void riskPanelGone(){
        mRiskTitle.setVisibility(View.GONE);
        mCorrectiveTitle.setVisibility(View.GONE);
        mRiskId.setVisibility(View.GONE);
        mCreationDate.setVisibility(View.GONE);
        mIpr.setVisibility(View.GONE);
    }

}
