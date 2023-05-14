package com.example.palliativecareguidelines.Doctors;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.palliativecareguidelines.Adapters.MyAdapter;
import com.example.palliativecareguidelines.Adapters.MyAdapter2;
import com.example.palliativecareguidelines.R;
import com.google.android.material.tabs.TabLayout;

public class ChatDoctor extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_doctor);


        tabLayout = (TabLayout) findViewById(R.id.tabLayout2);
        viewPager = (ViewPager) findViewById(R.id.viewPager2);

        tabLayout.addTab(tabLayout.newTab().setText("Chats"));
        tabLayout.addTab(tabLayout.newTab().setText("Contact"));
        tabLayout.addTab(tabLayout.newTab().setText("Request"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter2 adapter = new MyAdapter2(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }}