package com.FMEA.fmeimanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

public class EndDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_end_dialog);

        Button validate = findViewById(R.id.backover_ok_btn);
        validate.setOnClickListener(v -> {
            int pid = android.os.Process.myPid();
            android.os.Process.killProcess(pid);
        });

        Button cancel = findViewById(R.id.backover_cancel_btn);
        cancel.setOnClickListener(v -> finish());
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
