package com.example.palliativecareguidelines.Pationts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.palliativecareguidelines.R;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;
    String [] namelist={"مرض الزهايمر","أمراض العصبية المستعصية","أمراض السكتة الدماغية","أمراض التليف الكيسي","مرض باركنسون"};

    @SuppressLint({"MissingInflatedId", "CommitTransaction"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        searchView = findViewById(R.id.searchview);
        listView = findViewById(R.id.lv1);


        adapter = new ArrayAdapter<>(HomeScreen.this, android.R.layout.simple_list_item_1, android.R.id.text1, namelist);
        listView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                adapter.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.chatitem, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chat:
                startActivity(new Intent(HomeScreen.this, ChatActivity.class));

                break;
            case R.id.profile:
                startActivity(new Intent(HomeScreen.this, ProfilePAtiont.class));

                break;

        }

           return true;
    }
}
