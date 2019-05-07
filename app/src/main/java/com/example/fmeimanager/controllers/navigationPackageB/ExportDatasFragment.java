package com.example.fmeimanager.controllers.navigationPackageB;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.controllers.navigationPackageB.adapters.FmeiExportAdapter;
import com.example.fmeimanager.database.CorrectiveAction;
import com.example.fmeimanager.database.Fmei;
import com.example.fmeimanager.database.Processus;
import com.example.fmeimanager.database.Risk;
import com.example.fmeimanager.database.TeamFmei;
import com.example.fmeimanager.injection.Injection;
import com.example.fmeimanager.injection.ViewModelFactory;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.utils.StorageUtils;
import com.example.fmeimanager.viewmodels.GeneralViewModel;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_ACTIVE_USER;
import static com.example.fmeimanager.MainActivity.DEFAULT_USER_ID;
import static com.example.fmeimanager.MainActivity.SHARED_MAIN_PROFILE_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExportDatasFragment extends Fragment implements ExportViewPagerFragment.ExportViewPagerListener {

    private View mView;
    private GeneralViewModel mGeneralViewModel;
    private String mFmeaTitle;
    private List<TeamFmei> mTeamFmea;
    private Fmei mFmei;
    private String mEmailAdress;
    private List<Participant> mParticipants;
    private List<Processus> mProcessusList;
    private List<Risk> mRiskList;
    private List<CorrectiveAction> mCorrectiveActionList;

    private long mFmeaId;

    @BindView(R.id.export_csv_btn) Button mCsvBtn;

    public ExportDatasFragment() {}

    public static ExportDatasFragment newInstance(){
        return new ExportDatasFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_export_datas, container, false);
        ButterKnife.bind(this, mView);

        mFmeaTitle = "";
        mFmeaId = 0;
        this.configureViewModel();
        this.getAdministrator(getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_ACTIVE_USER, DEFAULT_USER_ID));
        mCsvBtn.setVisibility(View.INVISIBLE);

        getAllFmei();
        getAllParticipant();
        getAllRisk();
        getAllCorrectives();

        return mView;
    }

    @Override
    public void LoadingFmea(String title, long id) {
        mCsvBtn.setVisibility(View.VISIBLE);
        mFmeaTitle = title;
        mFmeaId = id;
        String titleBtn = title + ".csv";
        mCsvBtn.setText(titleBtn);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface ExportDataListener{
        void updateExportDataNavHeader(Participant participant);
    }

    //callback for button clicked
    private ExportDataListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (ExportDataListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }

    /**
     *  ACTION
     */

    @OnClick(R.id.export_csv_btn)
    public void clickCsvBtn(){
        Toast.makeText(getContext(), ""+ mFmeaId, Toast.LENGTH_SHORT).show();
        getConcernedTeamFmea(mFmeaId);
        getConcernedProcessus(mFmeaId);
        getFmei(mFmeaId);
    }

    /**
     *  DATAS
     */

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(getContext());
        this.mGeneralViewModel = ViewModelProviders.of(this, viewModelFactory).get(GeneralViewModel.class);
        this.mGeneralViewModel.init(getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_ACTIVE_USER, DEFAULT_USER_ID));
    }

    private void getAdministrator(long id){
        this.mGeneralViewModel.getParticipant(id).observe(this, this::updateAdministrator);
    }

    //GET selected fmea
    private void getFmei(long fmeaId){
        mGeneralViewModel.getFmei(fmeaId).observe(this, this::updateSelectedFmea);
    }

    //GET all fmei
    private void getAllFmei(){
        mGeneralViewModel.getAllFmei().observe(this, this::updateAllFmei);
    }

    //GET all concerned TeamFmea about Fmea
    private void getConcernedTeamFmea(long fmeaId){
        mGeneralViewModel.getTeamsWithLinkFmei(fmeaId).observe(this, this::updateTeamFmea);
    }

    //GET all participant
    private void getAllParticipant(){
        mGeneralViewModel.getAllParticipant().observe(this, this::updateParticipant);
    }

    //GET all processus from fmea
    private void getConcernedProcessus(long fmeaId){
        mGeneralViewModel.getProcessussListForFmei(fmeaId).observe(this, this::updateProcessus);
    }

    //GET all risk
    private void getAllRisk(){
        mGeneralViewModel.getAllRisk().observe(this, this::upadteRisks);
    }

    //GET all corrective action
    private void getAllCorrectives(){
        mGeneralViewModel.getAllCorrectiveAction().observe(this, this::updateCorrectiveActions);
    }

    /**
     *  UI
     */

    private void updateAdministrator(Participant participant){
        mEmailAdress = participant.getMail();
        mCallback.updateExportDataNavHeader(participant);
    }

    //RECORD all fmei
    private void updateAllFmei(List<Fmei> fmeiList){
        ViewPager viewPager = mView.findViewById(R.id.export_data_viewpager);
        viewPager.setAdapter(new FmeiExportAdapter(getChildFragmentManager(), fmeiList));
    }

    //RECORD selected fmea
    private void updateSelectedFmea(Fmei fmei){
        mFmei = fmei;
        if (mTeamFmea != null && mFmei != null && mProcessusList != null){
            senEmail(BusinessExport.createReport(getContext(), mFmei, mTeamFmea, mParticipants, mProcessusList, mRiskList, mCorrectiveActionList), mFmei.getName());
        }
    }

    //RECORD all team fmea
    private void updateTeamFmea(List<TeamFmei> teamFmeiList){
        mTeamFmea = teamFmeiList;
    }

    //RECORD all participants
    private void updateParticipant(List<Participant> participants){
        mParticipants = participants;
    }

    //RECORD all processus
    private void updateProcessus(List<Processus> processusList){
        mProcessusList = processusList;
    }

    //RECORD all risk
    private void upadteRisks(List<Risk> risks){
        mRiskList = risks;
    }

    //RECORD all corrective action
    private void updateCorrectiveActions(List<CorrectiveAction> correctiveActions){
        mCorrectiveActionList = correctiveActions;
    }

    //SEND EMAIL REPORT
    private void senEmail(String body, String fmeaName){
        StorageUtils.setTextInStorage(getContext().getFilesDir(), getContext(), "MYFILE.csv", "MYFOLDER", body);

        File imagePath = new File(getContext().getFilesDir(), "MYFOLDER");
        File newFile = new File(imagePath, "MYFILE.csv");

        String mail[] = {mEmailAdress};
        String subject = getContext().getString(R.string.Export_email_report_partsubject) + fmeaName;
        String message = getContext().getString(R.string.Export_email_report_part_body) + fmeaName;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, mail);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getContext(),"com.example.fmeimanager", newFile));

        intent.setType("message/rcf822");
        startActivity(Intent.createChooser(intent, "choose"));

    }

}
