package com.example.palliativecareguidelines.Pationts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.palliativecareguidelines.Doctors.Doctorlogin;
import com.example.palliativecareguidelines.R;

public class chooseScreen extends AppCompatActivity {
    CardView patientCard;
    CardView doctorCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_screen);

        patientCard = findViewById(R.id.patientCard);
        doctorCard = findViewById(R.id.doctorCard);

        doctorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(chooseScreen.this, Doctorlogin.class));

            }
        });

        patientCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(chooseScreen.this, PationtLogin.class));

            }
        });
    }
}