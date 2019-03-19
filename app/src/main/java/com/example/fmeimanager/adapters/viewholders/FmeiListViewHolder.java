package com.example.fmeimanager.adapters.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.fmeimanager.R;
import com.example.fmeimanager.models.Fmei;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FmeiListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_fmei_item_title) TextView mFmeiIdTitle;

    private View mItemView;

    public FmeiListViewHolder(@NonNull View itemView) {
        super(itemView);
        mItemView = itemView;
        ButterKnife.bind(this, mItemView);
    }

    public void updateWithAdapterInformation(Fmei fmei, boolean selectedFmei){

        String fmeiTitle = "FMEI " + String.valueOf(fmei.getId());
        mFmeiIdTitle.setText(fmeiTitle);



    }



}
