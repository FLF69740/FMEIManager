package com.example.fmeimanager.controllers.navigationPackage1.processusTheme;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fmeimanager.R;
import com.example.fmeimanager.adapters.ProcessusBuilderAdapter;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.models.Participant;
import com.example.fmeimanager.models.Processus;
import com.example.fmeimanager.utils.Utils;
import com.example.fmeimanager.viewmodels.ProcessusViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProcessusBuilderFragment extends Fragment implements ProcessusBuilderAdapter.Listener{

    private View mView;
    private long mFmeiId;
    private ProcessusViewModel mProcessusViewModel;
    private List<Processus> mProcessusList = new ArrayList<>();
    private ProcessusBuilderAdapter mAdapter;

    public static final String BUNDLE_KEY_PROCESSUS_NAME = "BUNDLE_KEY_PROCESSUS_NAME";
    public static final String BUNDLE_KEY_PROCESSUS_POSITION = "BUNDLE_KEY_PROCESSUS_POSITION";
    private static final int REQUEST_CODE_WRITE_ACTIVITY  = 10023;

    @BindView(R.id.fragment_processus_builder_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.processus_builder_fmei_indicator_number) TextView mFmeiIndicator;

    public ProcessusBuilderFragment() {}

    public static ProcessusBuilderFragment newInstance(long fmeid){
        ProcessusBuilderFragment processusBuilderFragment = new ProcessusBuilderFragment();
        Bundle bundle = new Bundle(1);
        bundle.putLong("MA KEY", fmeid);
        processusBuilderFragment.setArguments(bundle);
        return processusBuilderFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_processus_builder, container, false);
        ButterKnife.bind(this, mView);
        mFmeiId = getArguments().getLong("MA KEY");
        this.configureRecyclerView();
        this.configureOnClickRecyclerView();
        this.configureViewModel();
        this.getAdministrator(1);
        this.getProcessusAboutFMEI(mFmeiId);
        return mView;
    }

    //configure recyclerView
    private void configureRecyclerView(){
        this.mAdapter = new ProcessusBuilderAdapter(this.mProcessusList, this);
        this.mRecyclerView.setAdapter(mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    //update recyclerView after other thread finalisation
    private void updateRecycler(List<Processus> processusList){
        mAdapter.setProcessusList(processusList);
        mAdapter.notifyDataSetChanged();
    }

    //itemView click from RecyclerView
    private void configureOnClickRecyclerView(){}


        /**
         *  DATAS
         */

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(getContext());
        this.mProcessusViewModel = ViewModelProviders.of(this, viewModelFactory).get(ProcessusViewModel.class);
        this.mProcessusViewModel.init(1);
    }

    private void getAdministrator(long id){
        this.mProcessusViewModel.getParticipant(id).observe(this, this::updateAdministrator);
    }

    //GET all processus for FMEI Id
    private void getProcessusAboutFMEI(long fmeiId){
        this.mProcessusViewModel.getProcessussListForFmei(fmeiId).observe(this, this::updateProcessusList);
    }


    public void saveBuilder() {
        Log.i(Utils.INFORMATION_LOG, "PROCESSUS BUILDER SAVE - FMEI ID = " + mFmeiId);
        for (int i = 0 ; i < mProcessusList.size() ; i++){
            this.mProcessusViewModel.updateProcessus(mProcessusList.get(i));
        }
        getActivity().finish();
    }

    //ADD a new Processus
    public void addProcessus() {
        Log.i(Utils.INFORMATION_LOG, "PROCESSUS BUILDER ADD - PROCESSUS STEP = " + String.valueOf(mProcessusList.size()+1));
        Processus processus = new Processus("processus_" + String.valueOf(mProcessusList.size()+1), mFmeiId, mProcessusList.size()+1);
        this.mProcessusViewModel.createProcessus(processus);
    }

    /**
     *  CLASS DEFINITION
     */

    private void updateAdministrator(Participant participant){
        //    Toast.makeText(this, participant.getForname() + " " + participant.getName(), Toast.LENGTH_SHORT).show();
    }

    //RECORD all processus about the FMEI Id
    private void updateProcessusList(List<Processus> processuses) {
        updateFragmentScreen(mFmeiId);
        if (processuses != null && processuses.size() != 0){
            mProcessusList = BusinnessProcessusTheme.getProcessusByStepLadder(processuses);
            this.updateRecycler(mProcessusList);
        }
    }

    /**
     *  UI
     */

    private void updateFragmentScreen(long fmeiId){
        String string = "FMEI " + String.valueOf(fmeiId);
        mFmeiIndicator.setText(string);
    }

    @Override
    public void onClickUpButton(int position) {
        Log.i(Utils.INFORMATION_LOG,"(FRAGMENT) UP " + position);
        if (position != 0){
            mProcessusList.get(position).setStep(position);
            mProcessusList.get(position-1).setStep(position+1);
            mProcessusList = BusinnessProcessusTheme.getProcessusByStepLadder(mProcessusList);
            this.updateRecycler(mProcessusList);
        }else {
            Log.i(Utils.INFORMATION_LOG,"(FRAGMENT) NO " + position);
        }
    }

    @Override
    public void onClickDownButton(int position) {
        Log.i(Utils.INFORMATION_LOG,"(FRAGMENT) DOWN " + position + "PROCESSUS LIST : " + mProcessusList.size());
        if (position != mProcessusList.size()-1){
            mProcessusList.get(position).setStep(position+2);
            mProcessusList.get(position+1).setStep(position+1);
            mProcessusList = BusinnessProcessusTheme.getProcessusByStepLadder(mProcessusList);
            this.updateRecycler(mProcessusList);
        }else {
            Log.i(Utils.INFORMATION_LOG,"(FRAGMENT) NO " + position);
        }
    }

    @Override
    public void onClickWritteButton(int position) {
        Log.i(Utils.INFORMATION_LOG,"(FRAGMENT) WRITTE " + position);
        Intent intent = new Intent(getActivity(), WriteFromProcessusBuilderActivity.class);
        intent.putExtra(BUNDLE_KEY_PROCESSUS_NAME, mProcessusList.get(position).getName());
        intent.putExtra(BUNDLE_KEY_PROCESSUS_POSITION, position);
        startActivityForResult(intent,REQUEST_CODE_WRITE_ACTIVITY);
    }

    @Override
    public void onClickVisibleButton(int position) {
        Log.i(Utils.INFORMATION_LOG,"(FRAGMENT) INVISIBLE " + position);
        mProcessusList.get(position).setVisible(false);
        this.updateRecycler(mProcessusList);
    }

    @Override
    public void onClickInvisibleButton(int position) {
        Log.i(Utils.INFORMATION_LOG,"(FRAGMENT) VISIBLE " + position);
        mProcessusList.get(position).setVisible(true);
        this.updateRecycler(mProcessusList);
    }

    /**
     *  ACTIVITY FOR RESULT
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE_WRITE_ACTIVITY == requestCode && RESULT_OK == resultCode){
            String name = data.getStringExtra(WriteFromProcessusBuilderActivity.BUNDLE_WRITE_ACTIVITY_PROCESSUS_NAME);
            int position = data.getIntExtra(WriteFromProcessusBuilderActivity.BUNDLE_WRITE_ACTIVITY_PROCESSUS_POSITION, 100000);
            if (name != null && position != 100000){
                mProcessusList.get(position).setName(name);
            }
            this.updateRecycler(mProcessusList);
        }
    }
}
