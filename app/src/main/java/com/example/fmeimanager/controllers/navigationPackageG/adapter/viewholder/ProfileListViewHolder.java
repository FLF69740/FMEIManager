package com.example.fmeimanager.controllers.navigationPackageG.adapter.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fmeimanager.R;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.utils.BitmapStorage;
import com.example.fmeimanager.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_profile_section_photo_item) ImageView mPhoto;
    @BindView(R.id.fragment_profile_section_name_item) TextView mName;
    @BindView(R.id.fragment_profile_section_function_item) TextView mFunction;
    @BindView(R.id.fragment_profile_section_mail_item) TextView mMail;
    @BindView(R.id.frgament_profile_alpha_scale) ImageView mScale;

    private View mItemView;

    public ProfileListViewHolder(@NonNull View itemView) {
        super(itemView);
        mItemView = itemView;
        ButterKnife.bind(this, mItemView);
    }

    public void updateWithAdapterInformation(Participant participant, boolean selected){
        String fullName = participant.getForname() + " " + participant.getName();
        mName.setText(fullName);
        if (participant.getFunction().equals(Utils.EMPTY)) {
            mFunction.setText(mItemView.getContext().getString(R.string.Risk_file_corrective_realisation_default));
        }else {
            mFunction.setText(participant.getFunction());
        }

        if (participant.getMail().equals(Utils.EMPTY)) {
            mMail.setText(mItemView.getContext().getString(R.string.Risk_file_corrective_realisation_default));
        }else {
            mMail.setText(participant.getMail());
        }

        if (participant.isActivated()){
            mScale.setVisibility(View.INVISIBLE);
        }else {
            mScale.setVisibility(View.VISIBLE);
        }

        if (BitmapStorage.isFileExist(mItemView.getContext(), "P" + participant.getId())) {
            mPhoto.setImageBitmap(BitmapStorage.loadImage(mItemView.getContext(), "P" + participant.getId()));
        }else {
            mPhoto.setImageResource(R.drawable.blank_profile_picture);
        }



    }



}
