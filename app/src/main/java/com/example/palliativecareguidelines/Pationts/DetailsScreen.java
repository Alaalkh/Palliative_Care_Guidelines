package com.example.palliativecareguidelines.Pationts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.palliativecareguidelines.R;

public class DetailsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);
        String title=getIntent().getStringExtra("title");

    }
}