package com.example.fmeimanager.controllers.navigationPackageF.adapter.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fmeimanager.R;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.models.TeamPanel;
import com.example.fmeimanager.utils.BitmapStorage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamFmeiHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_team_fmei_title_item) TextView mFmeaTitle;
    @BindView(R.id.fragment_team_fmei_photo_1) ImageView mPhoto1;
    @BindView(R.id.fragment_team_fmei_photo_2) ImageView mPhoto2;
    @BindView(R.id.fragment_team_fmei_photo_3) ImageView mPhoto3;
    @BindView(R.id.fragment_team_fmei_photo_4) ImageView mPhoto4;
    @BindView(R.id.fragment_team_fmei_photo_5) ImageView mPhoto5;
    @BindView(R.id.fragment_team_fmei_photo_6) ImageView mPhoto6;
    @BindView(R.id.fragment_team_fmei_photo_7) ImageView mPhoto7;
    @BindView(R.id.fragment_team_fmei_photo_8) ImageView mPhoto8;
    @BindView(R.id.fragment_team_fmei_photo_9) ImageView mPhoto9;
    @BindView(R.id.fragment_team_fmei_photo_10) ImageView mPhoto10;
    @BindView(R.id.fragment_team_fmei_photo_11) ImageView mPhoto11;
    @BindView(R.id.fragment_team_fmei_photo_12) ImageView mPhoto12;
    private ImageView[] mImageViews = new ImageView[12];

    View mItemView;

    public TeamFmeiHolder(@NonNull View itemView) {
        super(itemView);
        mItemView = itemView;
        ButterKnife.bind(this, mItemView);
    }

    public void updateWithAdapterInformation(TeamPanel teamPanel, int position){
        String title = mItemView.getContext().getString(R.string.profile_section_fmea) + " " + (position + 1);
        mFmeaTitle.setText(title);
        this.createImageTable();

        for (int i = 0 ; i < teamPanel.getParticipantList().size() ; i++){
            mImageViews[i].setImageBitmap(BitmapStorage.loadImage(mItemView.getContext(), "P" + teamPanel.getParticipantList().get(i).getId()));
        }

        for (int i = teamPanel.getParticipantList().size() ; i < mImageViews.length ; i++){
            mImageViews[i].setVisibility(View.GONE);
        }

    }

    // create the table of photo imageView
    private void createImageTable(){
        mImageViews[0] = mPhoto1;
        mImageViews[1] = mPhoto2;
        mImageViews[2] = mPhoto3;
        mImageViews[3] = mPhoto4;
        mImageViews[4] = mPhoto5;
        mImageViews[5] = mPhoto6;
        mImageViews[6] = mPhoto7;
        mImageViews[7] = mPhoto8;
        mImageViews[8] = mPhoto9;
        mImageViews[9] = mPhoto10;
        mImageViews[10] = mPhoto11;
        mImageViews[11] = mPhoto12;
    }
}