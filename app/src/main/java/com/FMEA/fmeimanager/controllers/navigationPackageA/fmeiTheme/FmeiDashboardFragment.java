package com.FMEA.fmeimanager.controllers.navigationPackageA.fmeiTheme;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.controllers.navigationPackageA.fmeiTheme.adapters.FmeiListAdapter;
import com.FMEA.fmeimanager.database.TeamFmei;
import com.FMEA.fmeimanager.injection.Injection;
import com.FMEA.fmeimanager.injection.ViewModelFactory;
import com.FMEA.fmeimanager.database.Fmei;
import com.FMEA.fmeimanager.database.Participant;
import com.FMEA.fmeimanager.models.FmeiPanel;
import com.FMEA.fmeimanager.models.FmeiPanelCreator;
import com.FMEA.fmeimanager.utils.RecyclerItemClickSupport;
import com.FMEA.fmeimanager.viewmodels.GeneralViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;
import static com.FMEA.fmeimanager.MainActivity.BUNDLE_KEY_ACTIVE_USER;
import static com.FMEA.fmeimanager.MainActivity.BUNDLE_KEY_HIGH_VALUE;
import static com.FMEA.fmeimanager.MainActivity.BUNDLE_KEY_LOW_VALUE;
import static com.FMEA.fmeimanager.MainActivity.BUNDLE_KEY_MEDIUM_VALUE;
import static com.FMEA.fmeimanager.MainActivity.DEFAULT_USER_ID;
import static com.FMEA.fmeimanager.MainActivity.SHARED_HIGH_SCORE;
import static com.FMEA.fmeimanager.MainActivity.SHARED_LOW_SCORE;
import static com.FMEA.fmeimanager.MainActivity.SHARED_MAIN_PROFILE_ID;
import static com.FMEA.fmeimanager.MainActivity.SHARED_MEDIUM_SCORE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FmeiDashboardFragment extends Fragment {

    private View mView;
    private GeneralViewModel mGeneralViewModel;
    private long mAdministratorId;
    private FmeiPanelCreator mFmeiPanelCreator;

    @BindView(R.id.fragment_fmei_recycler_view) RecyclerView mRecyclerView;

    public FmeiDashboardFragment() {}

    public static FmeiDashboardFragment newInstance(){
        return new FmeiDashboardFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_fmei_dashboard, container, false);
        ButterKnife.bind(this, mView);
        mFmeiPanelCreator = new FmeiPanelCreator();
        this.configureOnClickRecyclerView();
        this.configureViewModel();
        this.getAdministrator(getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_ACTIVE_USER, DEFAULT_USER_ID));
        this.getAllFmea();
        return mView;
    }

    //update recyclerView after other thread finalisation
    private void updateRecycler(List<FmeiPanel> fmeiPanelList){
        FmeiListAdapter adapter = new FmeiListAdapter(fmeiPanelList);
        this.mRecyclerView.setAdapter(adapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_fall_down);
        this.mRecyclerView.setLayoutAnimation(controller);
        this.mRecyclerView.scheduleLayoutAnimation();
    }

    //itemView click from RecyclerView
    private void configureOnClickRecyclerView(){
        RecyclerItemClickSupport.addTo(mRecyclerView, R.layout.fragment_fmei_recyclerview_item)
                .setOnItemClickListener((recyclerView, position, v) -> mCallback.fmeiDashBoard_To_ProcessDashboard(mView, position+1));
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface ItemClickedListener{
        void fmeiDashBoard_To_ProcessDashboard(View view, long fmeiId);
        void updateNavHeader(Participant participant);
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
        this.mGeneralViewModel = ViewModelProviders.of(this, viewModelFactory).get(GeneralViewModel.class);
        this.mGeneralViewModel.init(getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_ACTIVE_USER, DEFAULT_USER_ID));
    }

    //GET user administrator
    private void getAdministrator(long id){
        this.mGeneralViewModel.getParticipant(id).observe(this, this::updateAdministrator);
    }

    //GET all fmea
    private void getAllFmea(){
        this.mGeneralViewModel.getMediator_fmea(getActivity().getSharedPreferences(SHARED_HIGH_SCORE, MODE_PRIVATE).getInt(BUNDLE_KEY_HIGH_VALUE, 200),
                getActivity().getSharedPreferences(SHARED_MEDIUM_SCORE, MODE_PRIVATE).getInt(BUNDLE_KEY_MEDIUM_VALUE, 150),
                getActivity().getSharedPreferences(SHARED_LOW_SCORE, MODE_PRIVATE).getInt(BUNDLE_KEY_LOW_VALUE, 1)).observe(this, this::getMediator);
    }

    //CREATE FMEI
    public void createFmea(){
        Fmei fmei = new Fmei();
        fmei.setName(requireContext().getString(R.string.profile_section_fmea) + " " + (mFmeiPanelCreator.getFmeiListSize() + 1));
        fmei.setTeamLeader(mAdministratorId);
        TeamFmei teamFmei = new TeamFmei((mFmeiPanelCreator.getFmeiListSize() + 1), mAdministratorId);
        this.mGeneralViewModel.createFmei(fmei, teamFmei);
        Snackbar.make(mView, getContext().getString(R.string.Fmei_dashboard_snacbar_create) + fmei.getName(), Snackbar.LENGTH_SHORT).show();
    }

    /**
     *  UI
     */

    //SHOW user hardware
    private void updateAdministrator(Participant participant){
        mAdministratorId = participant.getId();
        mCallback.updateNavHeader(participant);
    }

    private void getMediator(List<FmeiPanel> panels){
        if (panels != null && panels.size() != 0) {
            mFmeiPanelCreator.clear();
            mFmeiPanelCreator.setFmeiPanelsWithFmeiLivedata(panels);
            this.updateRecycler(mFmeiPanelCreator.getFmeiPanels());
        }
    }

}
