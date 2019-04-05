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

        if (participant.getUrlPicture().equals(Utils.EMPTY)) {
            mPhoto.setImageResource(R.drawable.blank_profile_picture);
        }else {
            mPhoto.setImageBitmap(BitmapStorage.loadImage(mItemView.getContext(), participant.getUrlPicture()));
        }
    }



}
