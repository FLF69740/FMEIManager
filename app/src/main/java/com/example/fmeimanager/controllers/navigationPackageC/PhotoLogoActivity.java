package com.example.fmeimanager.controllers.navigationPackageC;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
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

public class PhotoLogoActivity extends AppCompatActivity {

    @BindView(R.id.logo_photo) ImageView mImageViewPhoto;

    private static final String IMAGE_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final int RC_LOGO_IMAGE_PERMS = 20100;
    private static final int RC_LOGO_CHOOSE_PHOTO = 20200;

    private static final String BUNDLE_LOGO_PHOTO_RESTORE = "BUNDLE_LOGO_PHOTO_RESTORE";

    private Uri mUriPicture;
    private boolean mIsPhotoSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_logo);
        mIsPhotoSelected = false;
        ButterKnife.bind(this);
        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(BUNDLE_LOGO_PHOTO_RESTORE)){
                mIsPhotoSelected = true;
                mUriPicture = Uri.parse(savedInstanceState.getString(BUNDLE_LOGO_PHOTO_RESTORE));
                Glide.with(this).load(mUriPicture).apply(RequestOptions.centerInsideTransform()).into(mImageViewPhoto);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mIsPhotoSelected) {
            outState.putString(BUNDLE_LOGO_PHOTO_RESTORE, mUriPicture.toString());
        }
    }

    //PERMISSION
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @OnClick(R.id.logo_cancel_btn)
    public void cancelButtonClicked(){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @OnClick(R.id.logo_validate_btn)
    public void validateButtonClicked(){

        if (mIsPhotoSelected) {
            String fileName = "LOGO";

            if (BitmapStorage.isFileExist(this, fileName)){
                BitmapStorage.deleteImage(this, fileName);
            }

            BitmapStorage.saveImageInternalStorage(this, fileName, mUriPicture);
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    /**
     *  CHOOSE IMAGE MEDIASTORE
     */

    @OnClick(R.id.logo_photo_button_modify)
    @AfterPermissionGranted(RC_LOGO_IMAGE_PERMS)
    public void onClickAddPicture(){
        if (!EasyPermissions.hasPermissions(this, IMAGE_PERMISSION)){
            EasyPermissions.requestPermissions(this, "OK", RC_LOGO_IMAGE_PERMS, IMAGE_PERMISSION);
            return;
        }
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RC_LOGO_CHOOSE_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_LOGO_CHOOSE_PHOTO){
            if (resultCode == RESULT_OK){
                this.mUriPicture = data.getData();
                mIsPhotoSelected = true;
                Glide.with(this).load(this.mUriPicture).apply(RequestOptions.centerInsideTransform()).into(mImageViewPhoto);
            }
        }
    }
}
