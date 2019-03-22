package com.example.fmeimanager.controllers.navigationPackage4;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.base.BaseActivity;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.viewmodels.ParticipantViewModel;

public class HelpActivity extends BaseActivity {

    private ParticipantViewModel mParticipantViewModel;

    @Override
    protected Fragment getFirstFragment() {
        return HelpFragment.newInstance();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_help;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.frame_layout_help;
    }

    @Override
    protected Fragment getSecondFragment() {
        return null;
    }

    @Override
    protected int getSecondFragmentLayout() {
        return 0;
    }

    @Override
    protected boolean isAChildActivity() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.configureViewModel();
        this.getAdministrator(1);
    }

    /**
     *  DATAS
     */

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
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
        Toast.makeText(this, participant.getForname() + " " + participant.getName() + "/HelpActivity", Toast.LENGTH_SHORT).show();
        this.updateHeader(participant);
    }
}
