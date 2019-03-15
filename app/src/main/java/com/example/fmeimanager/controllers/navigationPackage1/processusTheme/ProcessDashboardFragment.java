package com.example.fmeimanager.controllers.navigationPackage1.processusTheme;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.models.CorrectiveAction;
import com.example.fmeimanager.models.Participant;
import com.example.fmeimanager.models.Processus;
import com.example.fmeimanager.models.Risk;
import com.example.fmeimanager.viewmodels.ProcessusViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProcessDashboardFragment extends Fragment {

    private static final String BUNDLE_FMEI_ID = "BUNDLE_FMEI_ID";

    private View mView;
    private ProcessusViewModel mProcessusViewModel;
    private long mFmeiId;
    private List<Processus> mProcessusList = new ArrayList<>();
    private List<Risk> mRiskList = new ArrayList<>();
    private List<CorrectiveAction> mCorrectiveActionList = new ArrayList<>();


    @BindView(R.id.process_dashboard_first_link) Button mButton;

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

        this.configureViewModel();
        this.getAdministrator(1);
        this.getProcessusAboutFMEI(mFmeiId);

        Toast.makeText(requireActivity(), ""+mFmeiId, Toast.LENGTH_SHORT).show();
        return mView;
    }

    @OnClick(R.id.process_dashboard_first_link)
    public void processDashBoard_To_ProcessDashboard(){
        long riskId_TEMP = mRiskList.size();
        mCallback.processDashBoard_To_RiskFile(mView, riskId_TEMP, mFmeiId);
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

    //CREATE RISK
    public void createRisk(){
        Toast.makeText(getContext(), " CREATE RISK : " + String.valueOf(mRiskList.size() + 1), Toast.LENGTH_SHORT).show();
        simulationCreateRisk();
    }

    //******* TEMP *******
    private void simulationCreateProcessus(){
        Processus processus = new Processus("processus_1", mFmeiId);
        this.mProcessusViewModel.createProcessus(processus);
    }

    private void simulationCreateRisk(){
        Risk risk = new Risk("date_creation","parts_1","XC747","explosion",1,1);
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
            mProcessusList = processuses;
            getAllRisk();
            getAllCorrectiveActions();
        }
        // Toast.makeText(this, String.valueOf(mFmeiId) + " - PROCESSUS  : " + String.valueOf(mRiskList.size()), Toast.LENGTH_SHORT).show();
    }

    //RECORD all risk INTO OBJECT
    private void updateRiskList(List<Risk> risks) {
        if (risks != null) {
            mRiskList = risks;
        }
        //    Toast.makeText(this, "RISKS  : " + String.valueOf(mRiskList.size()), Toast.LENGTH_SHORT).show();
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
        String string = "FMEI " + String.valueOf(fmeiId) + "\n" +  getContext().getString(R.string.Risk_file_description_title);
        mButton.setText(string);
    }
}
