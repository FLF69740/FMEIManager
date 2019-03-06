package com.example.fmeimanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.fmeimanager.controllers.activities.FmeiDashboard;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton imageButton = findViewById(R.id.run_touch_screen);
        imageButton.setOnClickListener(v -> startFirstActivity());

    }

    private void startFirstActivity(){
        startActivity(new Intent(this, FmeiDashboard.class));
    }
}
