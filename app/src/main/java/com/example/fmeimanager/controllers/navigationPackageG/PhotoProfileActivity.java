package com.example.fmeimanager.controllers.navigationPackageG;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.fmeimanager.R;
import com.example.fmeimanager.utils.BitmapStorage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class PhotoProfileActivity extends AppCompatActivity {

    @BindView(R.id.profile_camera_button_modify) ImageView mCameraButtonModify;
    @BindView(R.id.profile_photo_button_modify)ImageView mPhotoButtonModify;
    @BindView(R.id.profile_validate_btn) Button mButtonValidate;
    @BindView(R.id.profile_photo) ImageView mImageViewPhoto;

    private static final String IMAGE_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final int RC_PROFILE_IMAGE_PERMS = 10100;
    private static final int RC_PROFILE_CHOOSE_PHOTO = 10200;
    private static final int RC_PROFILE_CAMERA_CAPTURE = 10300;

    public static final String BUNDLE_PROFILE_PHOTO_UPDATE = "BUNDLE_PHOTO_UPDATE";
    private static final String BUNDLE_PROFILE_PHOTO_RESTORE = "BUNDLE_PHOTO_RESTORE";

    private Uri mUriPicture;
    private boolean mIsPhotoSelected;
    private long mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_profile);
        Intent intent = getIntent();
        mId = intent.getLongExtra(ProfileFragment.BUNDLE_PHOTO_PROFILE_ID,0);
        mIsPhotoSelected = false;
        ButterKnife.bind(this);
        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(BUNDLE_PROFILE_PHOTO_RESTORE)){
                mIsPhotoSelected = true;
                mUriPicture = Uri.parse(savedInstanceState.getString(BUNDLE_PROFILE_PHOTO_RESTORE));
                Glide.with(this).load(mUriPicture).apply(RequestOptions.centerInsideTransform()).into(mImageViewPhoto);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mIsPhotoSelected) {
            outState.putString(BUNDLE_PROFILE_PHOTO_RESTORE, mUriPicture.toString());
        }
    }

    //PERMISSION
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @OnClick(R.id.profile_cancel_btn)
    public void cancelButtonClicked(){
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_PROFILE_PHOTO_UPDATE, mUriPicture);
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @OnClick(R.id.profile_validate_btn)
    public void validateButtonClicked(){

        if (mIsPhotoSelected) {
            String fileName = "P" + String.valueOf(mId);

            if (BitmapStorage.isFileExist(this, fileName)){
                BitmapStorage.deleteImage(this, fileName);
            }

            BitmapStorage.saveImageInternalStorage(this, fileName, mUriPicture);
            Intent intent = new Intent();
            intent.putExtra(BUNDLE_PROFILE_PHOTO_UPDATE, fileName);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    /**
     *  CHOOSE CAMERA CAPTURE
     */

    @OnClick(R.id.profile_camera_button_modify)
    public void onClickCaptureCamera(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, RC_PROFILE_CAMERA_CAPTURE);
    }

    /**
     *  CHOOSE IMAGE MEDIASTORE
     */

    @OnClick(R.id.profile_photo_button_modify)
    @AfterPermissionGranted(RC_PROFILE_IMAGE_PERMS)
    public void onClickAddPicture(){
        if (!EasyPermissions.hasPermissions(this, IMAGE_PERMISSION)){
            EasyPermissions.requestPermissions(this, "OK", RC_PROFILE_IMAGE_PERMS, IMAGE_PERMISSION);
            return;
        }
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RC_PROFILE_CHOOSE_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PROFILE_CHOOSE_PHOTO){
            if (resultCode == RESULT_OK){
                this.mUriPicture = data.getData();
                mIsPhotoSelected = true;
                Glide.with(this).load(this.mUriPicture).apply(RequestOptions.centerInsideTransform()).into(mImageViewPhoto);
            }
        } else if (requestCode == RC_PROFILE_CAMERA_CAPTURE){
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