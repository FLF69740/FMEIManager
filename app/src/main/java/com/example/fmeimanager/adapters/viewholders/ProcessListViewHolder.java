package com.example.fmeimanager.adapters.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.fmeimanager.R;
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

    public void updateWithAdapterInformation(Risk risk, int processusStep, boolean single){
        if (single) {
            String processusTitle = "PROCESSUS " + String.valueOf(processusStep);
            mProcessIdTitle.setText(processusTitle);
            mRiskIdTitle.setVisibility(View.GONE);
        } else {
            String processusTitle = "PROCESSUS " + String.valueOf(processusStep);
            mProcessIdTitle.setVisibility(View.GONE);
            String riskTitle = "RISK " + String.valueOf(risk.getId());
            mRiskIdTitle.setText(riskTitle);
        }
    }


}
