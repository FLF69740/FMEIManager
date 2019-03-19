package com.example.fmeimanager.controllers.navigationPackage1.processusTheme;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.adapters.viewholders.ProcessListAdapter;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.models.CorrectiveAction;
import com.example.fmeimanager.models.Participant;
import com.example.fmeimanager.models.Processus;
import com.example.fmeimanager.models.Risk;
import com.example.fmeimanager.utils.RecyclerItemClickSupport;
import com.example.fmeimanager.viewmodels.ProcessusViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProcessDashboardFragment extends Fragment {

    private static final String BUNDLE_FMEI_ID = "BUNDLE_FMEI_ID";
    private static final int CREATE_RISK_REQUEST_CODE = 110;

    private View mView;
    private ProcessusViewModel mProcessusViewModel;
    private long mFmeiId;
    private List<Processus> mProcessusList = new ArrayList<>();
    private List<Integer> mStepProcessusList = new ArrayList<>();
    private List<Risk> mRiskList = new ArrayList<>();
    private List<Boolean> mTitleProcessSingle = new ArrayList<>();
    private List<CorrectiveAction> mCorrectiveActionList = new ArrayList<>();
    private ProcessListAdapter mAdapter;

    @BindView(R.id.fragment_process_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.process_fmei_indicator_number) TextView mFmeiIndicator;

    public static ProcessDashboardFragment newInstance(long fmeiId){
        ProcessDashboardFragment processDashboardFragment = new ProcessDashboardFragment();
        Bundle bundle = new Bundle(1);
        bundle.putLong(BUNDLE_FMEI_ID, fmeiId);
        processDashboardFragment.setArguments(bundle);
        return processDashboardFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_process_dashboard, container, false);
        ButterKnife.bind(this, mView);
        mFmeiId = getArguments().getLong(BUNDLE_FMEI_ID);
        this.configureRecyclerView();
        this.configureOnClickRecyclerView();
        this.configureViewModel();
        this.getAdministrator(1);
        this.getProcessusAboutFMEI(mFmeiId);
        return mView;
    }

    //configure recyclerView
    private void configureRecyclerView(){
        this.mAdapter = new ProcessListAdapter(this.mRiskList, this.mStepProcessusList);
        this.mRecyclerView.setAdapter(mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    //update recyclerView after other thread finalisation
    private void updateRecycler(List<Risk> riskList, List<Integer> processusStepList, List<Boolean> singleTitleList){
        mAdapter.setProcessusList(riskList, processusStepList, singleTitleList);
        mAdapter.notifyDataSetChanged();
    }

    //itemView click from RecyclerView
    private void configureOnClickRecyclerView(){
        RecyclerItemClickSupport.addTo(mRecyclerView, R.layout.fragment_process_recyclerview_item)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    if (mRiskList.get(position).getId() != 0) {
                        mCallback.processDashBoard_To_RiskFile(mView, mRiskList.get(position).getId(), mFmeiId);
                    }
                });
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface ItemClickedListener{
        void processDashBoard_To_RiskFile(View view, long riskId, long parentId);
    }

    //callback for button clicked
    private ItemClickedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (ItemClickedListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }

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

    //GET all risk
    private void getAllRisk(){
        this.mProcessusViewModel.getAllRisk().observe(this, this::updateRiskList);
    }

    //GET all corrective action
    private void getAllCorrectiveActions(){
        this.mProcessusViewModel.getAllCorrectiveAction().observe(this, this::updateCorrectiveActionList);
    }

    //CREATE PROCESSUS
    public void createProcessus(){
        Toast.makeText(getContext(), " CREATE PROCESSUS : " + String.valueOf(mProcessusList.size() + 1), Toast.LENGTH_SHORT).show();
        simulationCreateProcessus();
    }

    public static final String BUNDLE_KEY_LIST_PROCESSUS_ID = "BUNDLE_KEY_LIST_PROCESSUS_ID";

    //CREATE RISK
    public void createRisk(){
        if (mProcessusList != null) {
            Intent intent = new Intent(getActivity(), ViewPagerInsertRiskActivity.class);
            if (!mProcessusList.isEmpty()) {
                String listString = BusinnessProcessusTheme.getProcessusListId(mProcessusList);
                intent.putExtra(BUNDLE_KEY_LIST_PROCESSUS_ID, listString);
            }
            startActivityForResult(intent, CREATE_RISK_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (CREATE_RISK_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            long processusId = data.getLongExtra(ViewPagerInsertRiskActivity.BUNDLE_RISK_PROCESSUS_ID, 0);
            if (processusId != 0){
                    simulationCreateRisk(processusId);
            }
        }
    }

    //******* TEMP *******
    private void simulationCreateProcessus(){
        Processus processus = new Processus("processus_" + String.valueOf(mProcessusList.size()+1), mFmeiId, mProcessusList.size()+1);
        this.mProcessusViewModel.createProcessus(processus);
    }

    private void simulationCreateRisk(long processusId){
        Risk risk = new Risk("date_creation","parts_1","XC747","explosion", processusId,1);
        this.mProcessusViewModel.createRisk(risk);
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
            getAllRisk();
            getAllCorrectiveActions();
        }
    }

    //RECORD all risk INTO OBJECT
    private void updateRiskList(List<Risk> risks) {
        if (risks != null) {
            mStepProcessusList.clear();
            mRiskList.clear();
            mTitleProcessSingle.clear();
            for (int i = 0 ; i < mProcessusList.size() ; i++){
                Log.i("ee" , String.valueOf(i));
                mStepProcessusList.add(mProcessusList.get(i).getStep());
                mRiskList.add(new Risk());
                mTitleProcessSingle.add(true);
                for (int j = 0 ; j < risks.size() ; j++) {
                    if (risks.get(j).getProcessusId() == mProcessusList.get(i).getId()) {
                        mRiskList.add(risks.get(j));
                        mStepProcessusList.add(mProcessusList.get(i).getStep());
                        mTitleProcessSingle.add(false);
                    }
                }
            }
            this.updateRecycler(mRiskList, mStepProcessusList, mTitleProcessSingle);
        }
    }

    //RECORD all corrective action INTO OBJECT
    private void updateCorrectiveActionList(List<CorrectiveAction> correctiveActions) {
        if (correctiveActions != null) {
            mCorrectiveActionList = correctiveActions;
        }
       // Toast.makeText(getContext(), "CO  : " + String.valueOf(mCorrectiveActionList.size()), Toast.LENGTH_SHORT).show();
    }

    /**
     *  UI
     */

    private void updateFragmentScreen(long fmeiId){
        String string = "FMEI " + String.valueOf(fmeiId);
        mFmeiIndicator.setText(string);
    }
}
