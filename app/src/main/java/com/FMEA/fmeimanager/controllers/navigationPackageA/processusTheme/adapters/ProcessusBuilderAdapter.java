package com.FMEA.fmeimanager.controllers.navigationPackageA.processusTheme.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.controllers.navigationPackageA.processusTheme.adapters.viewholders.ProcessusBuilderViewHolder;
import com.FMEA.fmeimanager.database.Processus;

import java.util.List;

public class ProcessusBuilderAdapter extends RecyclerView.Adapter<ProcessusBuilderViewHolder> {

    private List<Processus> mProcessusList;
    private final Listener mCallback;
    private int mRedLightPosition = -1 , mGreenLightPosition = -2;

    public interface Listener{
        void onClickUpButton(int position);
        void onClickDownButton(int position);
        void onClickWritteButton(int position);
        void onClickVisibleButton(int position);
        void onClickInvisibleButton(int position);
    }

    public ProcessusBuilderAdapter(List<Processus> processusList, Listener callback) {
        mCallback = callback;
        mProcessusList = processusList;
    }

    public void pushLights(int up, int down){
        mGreenLightPosition = up;
        mRedLightPosition = down;
    }

    @NonNull
    @Override
    public ProcessusBuilderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_process_builder_recyclerview_item, viewGroup, false);
        return new ProcessusBuilderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProcessusBuilderViewHolder processusBuilderViewHolder, int i) {
        boolean redLightOn = false, greenLightOn = false;
        if (mRedLightPosition == i){
            redLightOn = true;
        }
        if (mGreenLightPosition == i){
            greenLightOn = true;
        }
        processusBuilderViewHolder.updateWithAdapterInformation(this.mProcessusList.get(i), this.mCallback, redLightOn, greenLightOn);
    }

    @Override
    public int getItemCount() {
        return mProcessusList.size();
    }
}
