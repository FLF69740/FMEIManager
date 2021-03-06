package com.FMEA.fmeimanager.controllers.navigationPackageA.processusTheme.adapters.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.controllers.navigationPackageA.processusTheme.adapters.ProcessusBuilderAdapter;
import com.FMEA.fmeimanager.controllers.navigationPackageA.processusTheme.drawing.ProcessBuilderBody;
import com.FMEA.fmeimanager.database.Processus;
import com.FMEA.fmeimanager.utils.Utils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProcessusBuilderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private View mItemView;
    private WeakReference<ProcessusBuilderAdapter.Listener> mCallbackWeakRef;

    @BindView(R.id.fragment_processus_builder_processus_step_title) TextView mProcessusTitle;
    @BindView(R.id.fragment_processus_builder_item_up) ImageButton mImageButtonUp;
    @BindView(R.id.fragment_processus_builder_item_down) ImageButton mImageButtonDown;
    @BindView(R.id.fragment_processus_builder_item_writte) ImageButton mImageButtonWritte;
    @BindView(R.id.fragment_processus_builder_item_invisible) ImageButton mImageButtonInvisible;
    @BindView(R.id.fragment_processus_builder_item_visible) ImageButton mImageButtonVisible;
    @BindView(R.id.fragment_processus_builder_canvas) ProcessBuilderBody mCustomView;

    public ProcessusBuilderViewHolder(@NonNull View itemView) {
        super(itemView);
        mItemView = itemView;
        ButterKnife.bind(this, mItemView);
    }

    public void updateWithAdapterInformation(Processus processus, ProcessusBuilderAdapter.Listener callback, boolean red, boolean green){
        String processusTitle = processus.getName();
        this.mImageButtonDown.setOnClickListener(this);
        this.mImageButtonUp.setOnClickListener(this);
        this.mImageButtonWritte.setOnClickListener(this);
        this.mImageButtonVisible.setOnClickListener(this);
        this.mImageButtonInvisible.setOnClickListener(this);
        this.mProcessusTitle.setText(processusTitle);
        this.mCustomView.setTitleBody(processusTitle);

        if (red){
            mCustomView.setBarStatut(ProcessBuilderBody.FALL);
            mCustomView.startTranslation();
        }else if (green){
            mCustomView.setBarStatut(ProcessBuilderBody.RISE);
            mCustomView.startTranslation();
        }

        this.mCallbackWeakRef = new WeakReference<>(callback);

        if (processus.isVisible()){
            mImageButtonVisible.setVisibility(View.VISIBLE);
            mImageButtonInvisible.setVisibility(View.INVISIBLE);
        }else {
            mImageButtonVisible.setVisibility(View.INVISIBLE);
            mImageButtonInvisible.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        ProcessusBuilderAdapter.Listener callback = mCallbackWeakRef.get();
        switch (v.getTag().toString()){
            case "WRITTE":
                Log.i(Utils.INFORMATION_LOG,"WRITTE");
                if (callback != null) callback.onClickWritteButton(getAdapterPosition());
                break;
            case "UP":
                Log.i(Utils.INFORMATION_LOG,"UP");
                if (callback != null) callback.onClickUpButton(getAdapterPosition());
                break;
            case "DOWN":
                Log.i(Utils.INFORMATION_LOG,"DOWN");
                if (callback != null) callback.onClickDownButton(getAdapterPosition());
                break;
            case "VISIBLE":
                Log.i(Utils.INFORMATION_LOG,"VISIBLE");
                if (callback != null) callback.onClickVisibleButton(getAdapterPosition());
                break;
            case "INVISIBLE":
                Log.i(Utils.INFORMATION_LOG,"INVISIBLE");
                if (callback != null) callback.onClickInvisibleButton(getAdapterPosition());
                break;
        }
    }
}
