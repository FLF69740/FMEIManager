package com.example.fmeimanager.controllers.navigationPackageA.processusTheme.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.navigationPackageA.processusTheme.adapters.viewholders.ProcessusBuilderViewHolder;
import com.example.fmeimanager.database.Processus;

import java.util.List;

public class ProcessusBuilderAdapter extends RecyclerView.Adapter<ProcessusBuilderViewHolder> {

    private List<Processus> mProcessusList;
    private final Listener mCallback;

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
        processusBuilderViewHolder.updateWithAdapterInformation(this.mProcessusList.get(i), this.mCallback);
    }

    @Override
    public int getItemCount() {
        return mProcessusList.size();
    }
}
