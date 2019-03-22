package com.example.fmeimanager.controllers.navigationPackage2;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.viewmodels.ParticipantViewModel;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExportDatasFragment extends Fragment {

    private View mView;
    private ParticipantViewModel mParticipantViewModel;

    public ExportDatasFragment() {}

    public static ExportDatasFragment newInstance(){
        return new ExportDatasFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_export_datas, container, false);
        ButterKnife.bind(this, mView);

        this.configureViewModel();
        this.getAdministrator(1);

        return mView;
    }

    /**
     *  DATAS
     */

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(getContext());
        this.mParticipantViewModel = ViewModelProviders.of(this, viewModelFactory).get(ParticipantViewModel.class);
        this.mParticipantViewModel.init(1);
    }

    private void getAdministrator(long id){
        this.mParticipantViewModel.getParticipant(id).observe(this, this::updateAdministrator);
    }

    /**
     *  UI
     */

    private void updateAdministrator(Participant participant){
        Toast.makeText(getContext(), participant.getForname() + " " + participant.getName() + "/Export", Toast.LENGTH_SHORT).show();
    //    this.updateHeader(participant);
    }

}
