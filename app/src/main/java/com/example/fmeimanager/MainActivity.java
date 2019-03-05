package com.example.fmeimanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.fmeimanager.controllers.activities.FmeiDashboard;

public class MainActivity extends AppCompatActivity {

    private ImageButton mImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageButton = findViewById(R.id.run_touch_screen);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFirstActivity();
            }
        });
    }

    private void startFirstActivity(){
        startActivity(new Intent(this, FmeiDashboard.class));
    }
}
