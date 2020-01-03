package com.sample.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;

import com.mahdizareei.fileexplorer.MZFileExplorer;
import com.mahdizareei.fileexplorer.MZFileExplorer2;

public class MainActivity extends AppCompatActivity {

    private MZFileExplorer2 fileExplorer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileExplorer = findViewById(R.id.listView);
        fileExplorer.setUpDefaultListOfFile(this, Environment.getExternalStorageDirectory().getAbsolutePath());
    }
}
