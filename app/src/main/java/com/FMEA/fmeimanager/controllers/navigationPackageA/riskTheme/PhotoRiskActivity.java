package com.FMEA.fmeimanager.controllers.navigationPackageA.riskTheme;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.FMEA.fmeimanager.R;
import com.FMEA.fmeimanager.utils.BitmapStorage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.FMEA.fmeimanager.utils.BitmapStorage.PHOTO_JUNCTION_TITLE_URI;

public class PhotoRiskActivity extends AppCompatActivity {

    @BindView(R.id.camera_button_modify)ImageView mCameraButtonModify;
    @BindView(R.id.photo_button_modify)ImageView mPhotoButtonModify;
    @BindView(R.id.validate_btn) Button mButtonValidate;
    @BindView(R.id.photo) ImageView mImageViewPhoto;
    @BindView(R.id.Photo_name) EditText mPhotoName;

    private static final String IMAGE_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final int RC_IMAGE_PERMS = 101;
    private static final int RC_CHOOSE_PHOTO = 102;
    private static final int RC_CAMERA_CAPTURE = 103;

    public static final String BUNDLE_PHOTO_UPDATE = "BUNDLE_PHOTO_UPDATE";
    public static final String BUNDLE_NAME_UPDATE = "BUNDLE_NAME_UPDATE";
    private static final String BUNDLE_PHOTO_RESTORE = "BUNDLE_PHOTO_RESTORE";
    private static final String BUNDLE_NAME_RESTORE = "BUNDLE_NAME_RESTORE";

    private Uri mUriPicture;
    private List<String> mItemListString;
    private boolean mIsPhotoSelected;
    private long mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_risk);
        Intent intent = getIntent();
        mItemListString = intent.getStringArrayListExtra(RiskFileDescriptionFragment.BUNDLE_PHOTO_LIST_RISK);
        mId = intent.getLongExtra(RiskFileDescriptionFragment.BUNDLE_PHOTO_RISK_ID,0);
        mIsPhotoSelected = false;
        ButterKnife.bind(this);
        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(BUNDLE_PHOTO_RESTORE)){
                mIsPhotoSelected = true;
                mUriPicture = Uri.parse(savedInstanceState.getString(BUNDLE_PHOTO_RESTORE));
                Glide.with(this).load(mUriPicture).apply(RequestOptions.centerInsideTransform()).into(mImageViewPhoto);
            }
            mPhotoName.setText(savedInstanceState.getString(BUNDLE_NAME_RESTORE));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_NAME_RESTORE, mPhotoName.getText().toString());
        if (mIsPhotoSelected) {
            outState.putString(BUNDLE_PHOTO_RESTORE, mUriPicture.toString());
        }
    }

    //PERMISSION
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @OnClick(R.id.cancel_btn)
    public void cancelButtonClicked(){
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_PHOTO_UPDATE, mUriPicture);
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @OnClick(R.id.validate_btn)
    public void validateButtonClicked(){
        Boolean incorrectValidation = false;
        if (!mPhotoName.getText().toString().isEmpty()) {
            if (mPhotoName.getText().toString().contains("/") || mPhotoName.getText().toString().contains("!")){
                incorrectValidation = true;
                Toast.makeText(this, getString(R.string.activity_photo_modifier_advertising_forbidden_characteres), Toast.LENGTH_SHORT).show();
            }
            for (int i = 0; i < mItemListString.size(); i++) {
                if (mPhotoName.getText().toString().equals(mItemListString.get(i))) {
                    incorrectValidation = true;
                    Toast.makeText(this, getString(R.string.activity_photo_modifier_advertising_photo_name), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            incorrectValidation = true;
            Toast.makeText(this, getString(R.string.activity_photo_modifier_advertising_empty_edittext), Toast.LENGTH_LONG).show();
        }

        if (!incorrectValidation && mIsPhotoSelected) {
            String fileName = String.valueOf(mId) + PHOTO_JUNCTION_TITLE_URI + mPhotoName.getText().toString();
            BitmapStorage.saveImageInternalStorage(this, fileName, mUriPicture);
            Intent intent = new Intent();
            intent.putExtra(BUNDLE_PHOTO_UPDATE, fileName);
            intent.putExtra(BUNDLE_NAME_UPDATE, mPhotoName.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    /**
     *  CHOOSE CAMERA CAPTURE
     */

    @OnClick(R.id.camera_button_modify)
    public void onClickCaptureCamera(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, RC_CAMERA_CAPTURE);
    }

    /**
     *  CHOOSE IMAGE MEDIASTORE
     */

    @OnClick(R.id.photo_button_modify)
    @AfterPermissionGranted(RC_IMAGE_PERMS)
    public void onClickAddPicture(){
        if (!EasyPermissions.hasPermissions(this, IMAGE_PERMISSION)){
            EasyPermissions.requestPermissions(this, "OK", RC_IMAGE_PERMS, IMAGE_PERMISSION);
            return;
        }
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RC_CHOOSE_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_CHOOSE_PHOTO){
            if (resultCode == RESULT_OK){
                this.mUriPicture = data.getData();
                mIsPhotoSelected = true;
                Glide.with(this).load(this.mUriPicture).apply(RequestOptions.centerInsideTransform()).into(mImageViewPhoto);
            }
        } else if (requestCode == RC_CAMERA_CAPTURE){
            if (resultCode == RESULT_OK){
                Bundle extra = data.getExtras();
                Bitmap bitmap = (Bitmap) extra.get("data");
                mUriPicture = BitmapStorage.getImageUri(this, bitmap, mId);
                mIsPhotoSelected = true;
                Glide.with(this).load(mUriPicture).apply(RequestOptions.centerInsideTransform()).into(mImageViewPhoto);
            }
        }
    }


}
