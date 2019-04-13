package com.example.fmeimanager.controllers.navigationPackageF;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.fmeimanager.R;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.utils.BitmapStorage;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import static android.content.Context.MODE_PRIVATE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_TEAM_LEADER_CATALOG_VIEWPAGER;
import static com.example.fmeimanager.MainActivity.DEFAULT_USER_ID;
import static com.example.fmeimanager.MainActivity.SHARED_MAIN_PROFILE_ID;


public class CatalogParticipantFragment extends Fragment implements View.OnClickListener {

    private ArrayList<String> mSelectedParticipant;
    private ArrayList<Participant> mParticipantList;

    private static final String KEY_PARTICIPANT_LIST = "KEY_PARTICIPANT_LIST";
    private static final String KEY_SELECTED_PARTICIPANT_LIST = "KEY_SELECTED_PARTICIPANT_LIST";
    private static final String KEY_POSITION = "KEY_POSITION";

    @BindView(R.id.participant_catalog_photo_1) ImageView mPhoto1;
    @BindView(R.id.participant_catalog_name_1) TextView mName1;
    @BindView(R.id.participant_catalog_photo_2) ImageView mPhoto2;
    @BindView(R.id.participant_catalog_name_2) TextView mName2;
    @BindView(R.id.participant_catalog_photo_3) ImageView mPhoto3;
    @BindView(R.id.participant_catalog_name_3) TextView mName3;
    @BindView(R.id.participant_catalog_photo_4) ImageView mPhoto4;
    @BindView(R.id.participant_catalog_name_4) TextView mName4;
    @BindView(R.id.participant_catalog_photo_5) ImageView mPhoto5;
    @BindView(R.id.participant_catalog_name_5) TextView mName5;
    @BindView(R.id.participant_catalog_photo_6) ImageView mPhoto6;
    @BindView(R.id.participant_catalog_name_6) TextView mName6;
    @BindView(R.id.participant_catalog_photo_7) ImageView mPhoto7;
    @BindView(R.id.participant_catalog_name_7) TextView mName7;
    @BindView(R.id.participant_catalog_photo_8) ImageView mPhoto8;
    @BindView(R.id.participant_catalog_name_8) TextView mName8;
    @BindView(R.id.participant_catalog_photo_9) ImageView mPhoto9;
    @BindView(R.id.participant_catalog_name_9) TextView mName9;

    public CatalogParticipantFragment() {}

    public static CatalogParticipantFragment newInstance(ArrayList<Participant> participantList, ArrayList<String> selectedParticipant, int position){
        CatalogParticipantFragment catalogParticipantFragment = new CatalogParticipantFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_PARTICIPANT_LIST, participantList);
        bundle.putStringArrayList(KEY_SELECTED_PARTICIPANT_LIST, selectedParticipant);
        bundle.putInt(KEY_POSITION, position);
        catalogParticipantFragment.setArguments(bundle);

        return catalogParticipantFragment;
    }

    private ImageView[] mImageViews;
    private TextView[] mNameAera;
    private View mView;
    private int mPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_team_fmei_participant_catalog_page, container, false);
        ButterKnife.bind(this, mView);

        mPosition = getArguments().getInt(KEY_POSITION, 0);
        mParticipantList = getArguments().getParcelableArrayList(KEY_PARTICIPANT_LIST);
        mSelectedParticipant = getArguments().getStringArrayList(KEY_SELECTED_PARTICIPANT_LIST);

        mImageViews = getTable();
        mNameAera = getNameAera();

        this.globalStateVerification(mView);

        return mView;
    }

    //Verification of state of all image aera
    private void globalStateVerification(View view){
        for (int i = 0 ; i < 9 ; i++) {
            if (mParticipantList.size() > i) {
                if (BitmapStorage.isFileExist(view.getContext(), "P" + mParticipantList.get(i).getId())) {
                    mImageViews[i].setImageBitmap(BitmapStorage.loadImage(view.getContext(), "P" + mParticipantList.get(i).getId()));
                } else {
                    mImageViews[i].setImageResource(R.drawable.blank_profile_picture);
                }
                this.imageStateVerification(mImageViews[i], mParticipantList.get(i),
                        getActivity().getSharedPreferences(SHARED_MAIN_PROFILE_ID, MODE_PRIVATE).getLong(BUNDLE_KEY_TEAM_LEADER_CATALOG_VIEWPAGER, DEFAULT_USER_ID),
                        mSelectedParticipant);
                String fullName = mParticipantList.get(i).getForname() + " " + mParticipantList.get(i).getName();
                mNameAera[i].setText(fullName);
                mImageViews[i].setOnClickListener(this);
            }else {
                mImageViews[i].setVisibility(View.INVISIBLE);
                mImageViews[i].setClickable(false);
                mNameAera[i].setVisibility(View.INVISIBLE);
            }
        }
    }

    //Verification of single image aera
    private void imageStateVerification(ImageView imageView, Participant participant, long teamLeaderId, List<String> participantSelected){
        for (int i = 0 ; i < participantSelected.size() ; i++) {
            if (participant.getId() == Long.valueOf(participantSelected.get(i))){
                imageView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.skin_accent_drak_border));
            }
        }
        if (participant.getId() == teamLeaderId){
            imageView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.skin_holo_border));
        }
    }

    //Initialise imageView into TABLE
    private ImageView[] getTable(){
        ImageView[] imageViews = new ImageView[9];
        imageViews[0] = mPhoto1;
        imageViews[1] = mPhoto2;
        imageViews[2] = mPhoto3;
        imageViews[3] = mPhoto4;
        imageViews[4] = mPhoto5;
        imageViews[5] = mPhoto6;
        imageViews[6] = mPhoto7;
        imageViews[7] = mPhoto8;
        imageViews[8] = mPhoto9;
        return imageViews;
    }

    // initialise textView into table
    private TextView[] getNameAera(){
        TextView[] textViews = new TextView[9];
        textViews[0] = mName1;
        textViews[1] = mName2;
        textViews[2] = mName3;
        textViews[3] = mName4;
        textViews[4] = mName5;
        textViews[5] = mName6;
        textViews[6] = mName7;
        textViews[7] = mName8;
        textViews[8] = mName9;
        return textViews;
    }

    //When imageView is clicked
    @Override
    public void onClick(View v) {
        String result = v.getTag().toString();
        mCallback.catalogSendTag(v, mParticipantList.get(Integer.valueOf(result)), mPosition);
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface CatalogParticipantListener{
        void catalogSendTag(View view, Participant participant, int position);
    }

    //callback for button clicked
    private CatalogParticipantListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (CatalogParticipantListener) getParentFragment();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }
}
