package com.example.fmeimanager.controllers.navigationPackageA.fmeiTheme.adapters.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.fmeimanager.R;
import com.example.fmeimanager.database.Fmei;
import com.example.fmeimanager.models.FmeiPanel;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FmeiListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_fmei_item_title) TextView mFmeiIdTitle;
    @BindView(R.id.fragment_fmei_processus_number) TextView mProcessusNumber;
    @BindView(R.id.fragment_fmei_team_Leader) TextView mFmeiTeamLeader;
    @BindView(R.id.fragment_fmei_participant_number) TextView mParticipantNumber;
    @BindView(R.id.fragment_fmei_risk_amount_title) TextView mRiskNumber;
    @BindView(R.id.fragment_fmei_average_title) TextView mRiskAverage;
    @BindView(R.id.fragment_fmei_ipr_max_title) TextView mIprMax;
    @BindView(R.id.fragment_fmei_high_risk_title) TextView mHighRiskNumber;
    @BindView(R.id.fragment_fmei_medium_risk_title) TextView mMediumRiskNumber;
    @BindView(R.id.fragment_fmei_low_risk_title) TextView mLowRiskNumber;

    private View mItemView;

    public FmeiListViewHolder(@NonNull View itemView) {
        super(itemView);
        mItemView = itemView;
        ButterKnife.bind(this, mItemView);
    }

    public void updateWithAdapterInformation(FmeiPanel fmeiPanel, boolean selectedFmei){

        mFmeiIdTitle.setText(fmeiPanel.getFmeiTitle());
        String string = mItemView.getContext().getString(R.string.Fmei_dashboard_processus_number) + " " + String.valueOf(fmeiPanel.getProcessusList().size());
        mProcessusNumber.setText(string);
        string = mItemView.getContext().getString(R.string.Fmei_dashboard_team_leader) + " " + fmeiPanel.getTeamLeader();
        mFmeiTeamLeader.setText(string);
        string = mItemView.getContext().getString(R.string.Fmei_dashboard_participant_amount) + " " +String.valueOf(fmeiPanel.getParticipantNumber());
        mParticipantNumber.setText(string);
        string = mItemView.getContext().getString(R.string.Fmei_dashboard_risks_amount) + " " +String.valueOf(fmeiPanel.getRiskAmount());
        mRiskNumber.setText(string);
        double average = fmeiPanel.getRiskRateAverage();
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        String averageString = decimalFormat.format(average);
        string = mItemView.getContext().getString(R.string.Fmei_dashboard_risks_average) + " " + averageString;
        mRiskAverage.setText(string);
        string = mItemView.getContext().getString(R.string.Fmei_dashboard_risk_max) + " " +String.valueOf(fmeiPanel.getIPRMax());
        mIprMax.setText(string);
        string = mItemView.getContext().getString(R.string.Fmei_dashboard_high_risk_amount) + " " +String.valueOf(fmeiPanel.getRiskAmountHighLevel());
        mHighRiskNumber.setText(string);
        string = mItemView.getContext().getString(R.string.Fmei_dashboard_medium_risk_amount) + " " +String.valueOf(fmeiPanel.getRiskAmountMiddleLevel());
        mMediumRiskNumber.setText(string);
        string = mItemView.getContext().getString(R.string.Fmei_dashboard_low_risk_amount) + " " +String.valueOf(fmeiPanel.getRiskAmountLowLevel());
        mLowRiskNumber.setText(string);



    }



}
