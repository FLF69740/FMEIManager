package com.example.fmeimanager.controllers.navigationPackageA.processusTheme;

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
import com.example.fmeimanager.controllers.navigationPackageA.processusTheme.adapters.ProcessListAdapter;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.database.CorrectiveAction;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.Processus;
import com.example.fmeimanager.models.ProcessusPanel;
import com.example.fmeimanager.models.ProcessusPanelCreator;
import com.example.fmeimanager.database.Risk;
import com.example.fmeimanager.utils.RecyclerItemClickSupport;
import com.example.fmeimanager.utils.Utils;
import com.example.fmeimanager.viewmodels.ProcessusViewModel;
import org.joda.time.DateTime;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_ACTIVE_USER;
import static com.example.fmeimanager.MainActivity.DEFAULT_USER_ID;
import static com.example.fmeimanager.MainActivity.SHARED_MAIN_PROFILE_ID;

public class ProcessDashboardFragment extends Fragment {

    private static final String BUNDLE_FMEI_ID = "BUNDLE_FMEI_ID";
    private static final int CREATE_RISK_REQUEST_CODE = 110;
    private static final int UPDATE_PROCESSUS_LIST_REQUEST_CODE = 120;

    public static final String BUNDLE_KEY_LIST_PROCESSUS_ID = "BUNDLE_KEY_LIST_PROCESSUS_ID";
    public static final String BUNDLE_KEY_LIST_PROCESSUS_STEP = "BUNDLE_KEY_LIST_PROCESSUS_STEP";

    private View mView;
    private ProcessusViewModel mProcessusViewModel;

    private long mAdministratorId;
    private long mFmeiId;
    private ProcessusPanelCreator mProcessusPanelCreator;

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
        mAdministratorId = getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_ACTIVE_USER, DEFAULT_USER_ID);
        mFmeiId = getArguments().getLong(BUNDLE_FMEI_ID);
        mProcessusPanelCreator = new ProcessusPanelCreator();
        updateFragmentScreen(mFmeiId);
        this.configureOnClickRecyclerView();
        this.configureViewModel();
        this.getProcessusAboutFMEI(mFmeiId);
        return mView;
    }

    /**
     *  UI
     */

    private void updateFragmentScreen(long fmeiId){
        String string = getString(R.string.profile_section_fmea) + " " + fmeiId;
        mFmeiIndicator.setText(string);
    }

    private void updateRecycler(List<ProcessusPanel> processusPanelList){
        ProcessListAdapter adapter = new ProcessListAdapter(processusPanelList);
        this.mRecyclerView.setAdapter(adapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    //itemView click from RecyclerView
    private void configureOnClickRecyclerView(){
        RecyclerItemClickSupport.addTo(mRecyclerView, R.layout.fragment_process_recyclerview_item)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    if (this.mProcessusPanelCreator.getProcessusPanels().get(position).getRiskId() != 0) {
                        mCallback.processDashBoard_To_RiskFile(mView, this.mProcessusPanelCreator.getProcessusPanels().get(position).getRiskId(), mFmeiId,
                                this.mProcessusPanelCreator.getProcessusPanels().get(position).getProcessusStep());
                    }
                });
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface ItemClickedListener{
        void processDashBoard_To_RiskFile(View view, long riskId, long parentId, int procesusStep);
        void processDashBoard_To_ProcessusBuilder(View view, long parentId);
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

    private void getProcessusAboutFMEI(long fmeiId){ this.mProcessusViewModel.theFirstLiveData(fmeiId).observe(this, this::updateTheRisk); }

    //LAUNCH PROCESSUS BUILDER
    public void createProcessus(){
        mCallback.processDashBoard_To_ProcessusBuilder(mView, mFmeiId);
        Intent intent = new Intent(getActivity(), ProcessusBuilderActivity.class);
        intent.putExtra(ProcessDashboardActivity.PROCESS_DASHBOARD_TO_PROCESS_BUILDER, mFmeiId);
        startActivityForResult(intent, UPDATE_PROCESSUS_LIST_REQUEST_CODE);
    }

    //CREATE RISK
    public void startCreateRisk(){
        if (mProcessusPanelCreator.getProcessusPanels() != null) {
            if (!mProcessusPanelCreator.getProcessusPanels().isEmpty()) {
                Intent intent = new Intent(getActivity(), InsertRiskViewPagerActivity.class);
                ArrayList<String> listProcessusId = BusinnessProcessusTheme.getProcessusListId(mProcessusPanelCreator.getProcessusPanels());
                ArrayList<String> listProcessusStep = BusinnessProcessusTheme.getProcessusListStep(mProcessusPanelCreator.getProcessusPanels());
                intent.putStringArrayListExtra(BUNDLE_KEY_LIST_PROCESSUS_ID, listProcessusId);
                intent.putStringArrayListExtra(BUNDLE_KEY_LIST_PROCESSUS_STEP, listProcessusStep);
                startActivityForResult(intent, CREATE_RISK_REQUEST_CODE);
            } else {
                Toast.makeText(getContext(), "PAS DE PROCESSUS", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void CreateRiskFinalisation(long processusId){
        DateTime dt = new DateTime();
        Risk risk = new Risk(dt.toString("dd/MM/yyyy"), Utils.EMPTY, Utils.EMPTY, Utils.EMPTY, processusId, mAdministratorId);
        this.mProcessusViewModel.createRisk(risk);
    }

    /**
     *  CLASS DEFINITION
     */

    private void updateTheRisk(List<ProcessusPanel> panels){
        mProcessusPanelCreator.setProcessusPanels(panels);
        this.updateRecycler(panels);
    }

    /**
     *  On activity result
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (CREATE_RISK_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            long processusId = data.getIntExtra(InsertRiskViewPagerActivity.BUNDLE_RISK_PROCESSUS_ID, 0);
            Log.i(Utils.INFORMATION_LOG, ""+processusId);
            if (processusId != 0){
                CreateRiskFinalisation(processusId);
            }
        }
        if (UPDATE_PROCESSUS_LIST_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            Log.i(Utils.INFORMATION_LOG, ""+data.getLongExtra(ProcessusBuilderActivity.BUNDLE_PROCESSUS_BUILDER_FMEI_ID, 0));
        }
    }
}
