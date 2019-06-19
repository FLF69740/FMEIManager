package com.FMEA.fmeimanager.controllers.navigationPackageD;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.FMEA.fmeimanager.R;
import com.github.barteksc.pdfviewer.PDFView;

public class HelpActivity extends AppCompatActivity{

    private static final String BUNDLE_KEY_PDF_LINK = "helpsection.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        PDFView pdfView = findViewById(R.id.pdfview);
        pdfView.fromAsset(BUNDLE_KEY_PDF_LINK).load();
    }



}
