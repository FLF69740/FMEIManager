package com.FMEA.fmeimanager.controllers.navigationPackageF;


import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFmeiBuilderFragment extends Fragment {
        //implements CatalogParticipantFragment.CatalogParticipantListener {
/*
    private View mView;
    private TeamViewModel mTeamViewModel;
    private ArrayList<String> mParticipantListID;
    private long mTeamLeaderId;
    private Fmei mFmei;
    private List<TeamFmei> mTeamFmeiList;
    private ArrayList<ArrayList<Participant>> mCatalog;
    private CatalogParticipantAdapter mAdapter;
    private int mPosition = 0;

    @BindView(R.id.activity_team_fmei_participant_count) TextView mParticipantCoutnTitle;

    private static final String BUNDLE_TEAM_FMEI_ID = "BUNDLE_TEAM_FMEI_ID";
    private static final String BUNDLE_TEAM_PARTICIPANT_ID = "BUNDLE_TEAM_PARTICIPANT_ID";
    private static final String BUNDLE_TEAM_TEAM_LEADER_ID = "BUNDLE_TEAM_TEAM_LEADER_ID";

    public TeamFmeiBuilderFragment() {}

    public static TeamFmeiBuilderFragment newInstance(long position, ArrayList<String> participantList, long teamLeaderId){
        TeamFmeiBuilderFragment teamFmeiBuilderFragment = new TeamFmeiBuilderFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(BUNDLE_TEAM_FMEI_ID, position);
        bundle.putStringArrayList(BUNDLE_TEAM_PARTICIPANT_ID, participantList);
        bundle.putLong(BUNDLE_TEAM_TEAM_LEADER_ID, teamLeaderId);
        teamFmeiBuilderFragment.setArguments(bundle);
        return teamFmeiBuilderFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_team_fmei_builder, container, false);
        ButterKnife.bind(this, mView);
        long teamFmeaId = getArguments().getLong(BUNDLE_TEAM_FMEI_ID, 1000);

        mCatalog = new ArrayList<>();
        mParticipantListID = new ArrayList<>();

        mAdapter = new CatalogParticipantAdapter(getChildFragmentManager(), mCatalog, mParticipantListID);
        ViewPager viewPager = mView.findViewById(R.id.team_fmei_builder_viewpager);
        viewPager.setAdapter(mAdapter);

        this.configureViewModel();
        this.getFmea(teamFmeaId);

        return mView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
     //   outState.putParcelableArrayList("CATALOG", mCatalog.get(0));
     //   outState.putStringArrayList("LIST", mParticipantListID);
        outState.putInt("POS", mPosition);
    }

    //GTE textView countdown information
    private String getCountDownTitle(){
        int value = 12 - mParticipantListID.size();
        return getString(R.string.team_fmei_dashboard_title) + " : " + mParticipantListID.size() + " " +
                getString(R.string.team_fmei_builder_still) + " " + value + ")";
    }

    /**
     *  ACTION
     */
/*
    public void saveNewParticipant(){
        ArrayList<String> teamFmeiIdToDelete = new ArrayList<>();
        for (int i = 0 ; i < mTeamFmeiList.size() ; i++){
            teamFmeiIdToDelete.add(String.valueOf(mTeamFmeiList.get(i).getTeamFmeiId()));
        }

        ArrayList<String> packageNewFmeiId = new ArrayList<>();
        ArrayList<String> packageNewParticipantId = new ArrayList<>();
        for (int i = 0 ; i < mParticipantListID.size() ; i++){
            packageNewFmeiId.add(String.valueOf(mFmei.getId()));
            packageNewParticipantId.add(mParticipantListID.get(i));
        }
        mCallback.updateTeamFmeiDatas(packageNewFmeiId, packageNewParticipantId, teamFmeiIdToDelete);
        Snackbar.make(mView, "SAVED", Snackbar.LENGTH_SHORT).show();
    }
*/
    /**
     *  Callback
     */
/*
    // interface for button clicked
    public interface TeamFmeiBuilderListener{
        void updateTeamFmeiDatas(ArrayList<String> packageNewFmeiId, ArrayList<String> packageNewParticipantId, ArrayList<String> teamFmeiIdToDelete);
    }

    //callback for button clicked
    private TeamFmeiBuilderListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (TeamFmeiBuilderListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }
*/
    /**
     *  DATAS
     */
/*
    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(getContext());
        this.mTeamViewModel = ViewModelProviders.of(this, viewModelFactory).get(TeamViewModel.class);
        this.mTeamViewModel.init(1);
    }

    //GET fmea about team concerned
    private void getFmea(long id){
        this.mTeamViewModel.getFmei(id).observe(this, this::updateTeamLeaderName);
    }

    //GET all team fmea
    private void getConcernedTeamFmea(long fmeiId){
        this.mTeamViewModel.getTeamsWithLinkFmei(fmeiId).observe(this, this::updateTeamFmea);
    }

    //GET all participant
    private void getAllParticipant(){
        this.mTeamViewModel.getAllParticipant().observe(this, this::updateAllParticipant);
    }
*/
    /**
     *  UI
     */
/*
    //RECORD team leader about fmea
    private void updateTeamLeaderName(Fmei fmei){
        mFmei = fmei;
        mTeamLeaderId = fmei.getTeamLeader();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE);
        sharedPreferences.edit().putLong(BUNDLE_KEY_TEAM_LEADER_CATALOG_VIEWPAGER, mTeamLeaderId).apply();
        getConcernedTeamFmea(fmei.getId());
    }

    //RECORD All team fmea about concerned fmea
    private void updateTeamFmea(List<TeamFmei> teamFmeiList){
        mTeamFmeiList = teamFmeiList;
        for (int i = 0 ; i < mTeamFmeiList.size() ; i++){
            mParticipantListID.add(String.valueOf(mTeamFmeiList.get(i).getParticipantId()));
        }
        mParticipantCoutnTitle.setText(getCountDownTitle());
        getAllParticipant();
    }

    //RECORD all participant
    private void updateAllParticipant(List<Participant> participants){
        ArrayList<Participant> participantArrayList = new ArrayList<>(participants);
        mCatalog = (ArrayList<ArrayList<Participant>>) BusinnessTeamFmei.getCatalog(participantArrayList);
        mAdapter.updateInformation(mCatalog, mParticipantListID);
        mAdapter.notifyDataSetChanged();
    }

    // callback from child fragment
    @Override
    public void catalogSendTag(View view, Participant participant, int position) {
        if (mCatalog.size() != 1){
            mParticipantListID = BusinnessTeamFmei.participantListRealoaded(participant, mParticipantListID, mTeamLeaderId);
        }
        mParticipantCoutnTitle.setText(getCountDownTitle());
        mAdapter.notifyDataSetChanged();
    }

*/
}
