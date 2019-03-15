package com.example.fmeimanager.controllers.navigationPackage1.correctiveActionTheme;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.models.Participant;
import com.example.fmeimanager.viewmodels.RiskViewModel;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CorrectiveActionFragment extends Fragment {

    private static final String BUNDLE_RISK_ID = "BUNDLE_RISK_ID";

    private View mView;
    private RiskViewModel mRiskViewModel;
    private long mRiskId;

    public CorrectiveActionFragment() {}

    public static CorrectiveActionFragment newInstance(long riskId){
        CorrectiveActionFragment correctiveActionFragment = new CorrectiveActionFragment();
        Bundle bundle = new Bundle(1);
        bundle.putLong(BUNDLE_RISK_ID, riskId);
        correctiveActionFragment.setArguments(bundle);
        return correctiveActionFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_corrective_action, container, false);
        ButterKnife.bind(this, mView);
        mRiskId = getArguments().getLong(BUNDLE_RISK_ID, 100);

        this.configureViewModel();
        this.getAdministrator(1);

        return mView;
    }

    @OnClick(R.id.corrective_action_first_link)
    public void correctiveAction_To_riskFile(){
        mCallback.correctiveAction_To_riskFile(mView, mRiskId);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface CorrectiveActionItemClickedListener{
        void correctiveAction_To_riskFile(View view, long riskId);
    }

    //callback for button clicked
    private CorrectiveActionItemClickedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (CorrectiveActionItemClickedListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }

    /**
     *  DATAS
     */

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(getContext());
        this.mRiskViewModel = ViewModelProviders.of(this, viewModelFactory).get(RiskViewModel.class);
        this.mRiskViewModel.init(1);
    }

    private void getAdministrator(long id){
        this.mRiskViewModel.getParticipant(id).observe(this, this::updateAdministrator);
    }

    /**
     *  UI
     */

    private void updateAdministrator(Participant participant){
        Toast.makeText(getContext(), participant.getForname() + " " + participant.getName(), Toast.LENGTH_SHORT).show();
    }

}
