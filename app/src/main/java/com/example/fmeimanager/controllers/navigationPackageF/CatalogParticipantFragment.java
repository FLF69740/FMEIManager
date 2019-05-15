package com.example.fmeimanager.controllers.navigationPackageF;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fmeimanager.R;
import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.utils.BitmapStorage;
import com.example.fmeimanager.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import static android.content.Context.MODE_PRIVATE;
import static com.example.fmeimanager.MainActivity.BUNDLE_KEY_TEAM_LEADER_CATALOG_VIEWPAGER;
import static com.example.fmeimanager.MainActivity.DEFAULT_USER_ID;
import static com.example.fmeimanager.MainActivity.SHARED_MAIN_PROFILE_ID;


public class CatalogParticipantFragment extends Fragment implements View.OnClickListener {
    
    private static final String KEY_PARTICIPANT_LIST = "KEY_PARTICIPANT_LIST";
    private static final String KEY_SELECTED_PARTICIPANT_LIST = "KEY_SELECTED_PARTICIPANT_LIST";
    private static final String KEY_POSITION = "KEY_POSITION";
    private static final String KEY_TEAM_LEADER_LIST = "KEY_TEAM_LEADER_LIST";

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

    public static CatalogParticipantFragment newInstance(ArrayList<Participant> participantList, ArrayList<String> selectedParticipant, long teamLeaderId, int position){
        CatalogParticipantFragment catalogParticipantFragment = new CatalogParticipantFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_PARTICIPANT_LIST, participantList);
        bundle.putStringArrayList(KEY_SELECTED_PARTICIPANT_LIST, selectedParticipant);
        bundle.putLong(KEY_TEAM_LEADER_LIST, teamLeaderId);
        bundle.putInt(KEY_POSITION, position);
        catalogParticipantFragment.setArguments(bundle);

        return catalogParticipantFragment;
    }

    private long mTeamLeaderId;
    private ImageView[] mImageViews;
    private TextView[] mNameAera;
    private int mPosition;
    private ArrayList<String> mSelectedParticipant;
    private ArrayList<Participant> mParticipantList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_fmei_participant_catalog_page, container, false);
        ButterKnife.bind(this, view);

        mPosition = getArguments().getInt(KEY_POSITION, 0);
        mParticipantList = getArguments().getParcelableArrayList(KEY_PARTICIPANT_LIST);
        mSelectedParticipant = getArguments().getStringArrayList(KEY_SELECTED_PARTICIPANT_LIST);
        mTeamLeaderId = getArguments().getLong(KEY_TEAM_LEADER_LIST);

        mImageViews = getTable();
        mNameAera = getNameAera();

        this.globalStateVerification(view);

        return view;
    }

    //Verification of state of all image aera
    private void globalStateVerification(View view){
        
        ArrayList<Participant> pageParticipant = new ArrayList<>();
        for (int i = 0 ; i < 9 ; i++){
            if (mPosition != 0){
                int j = mPosition * 9;
                if (mParticipantList.size() > (j + i)) {
                    pageParticipant.add(mParticipantList.get(j + i));
                }
            }else {
                if (mParticipantList.size() > i) {
                    pageParticipant.add(mParticipantList.get(i));
                }
            }
        }
        
        for (int i = 0 ; i < 9 ; i++) {
            
            if (pageParticipant.size() > i) {
                
                if (BitmapStorage.isFileExist(view.getContext(), "P" + pageParticipant.get(i).getId())) {
                    mImageViews[i].setImageBitmap(BitmapStorage.loadImage(view.getContext(), "P" + pageParticipant.get(i).getId()));
                } else {
                    mImageViews[i].setImageResource(R.drawable.blank_profile_picture);
                }
                this.imageStateVerification(mImageViews[i], pageParticipant.get(i), mTeamLeaderId, mSelectedParticipant);
                String fullName = pageParticipant.get(i).getForname() + " " + pageParticipant.get(i).getName();
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
        int result = Integer.valueOf(v.getTag().toString());

        if (mPosition != 0){
            result += (mPosition * 9);
        }


        int tempOld = mSelectedParticipant.size();
        Log.i(Utils.INFORMATION_LOG, ""+result + " - OLD " + mSelectedParticipant.size());

        ArrayList<String> newList = BusinnessTeamFmei.participantListRealoaded(mParticipantList.get(result), mSelectedParticipant, mTeamLeaderId);

        Log.i(Utils.INFORMATION_LOG, ""+result + " - " + newList.size() + " -> OLD " + tempOld);

        boolean isParticipantAdded = newList.size() > tempOld;

        if (mParticipantList.get(result).getId() != mTeamLeaderId) {
            mCallback.catalogSendTag(mParticipantList.get(result), mPosition, isParticipantAdded);
        }
    }

    /**
     *  Callback
     */

    // interface for button clicked
    public interface CatalogParticipantListener{
        void catalogSendTag(Participant participant, int position, boolean addParticipant);
    }

    //callback for button clicked
    private CatalogParticipantListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (CatalogParticipantListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemClickedListener");
        }
    }

}
