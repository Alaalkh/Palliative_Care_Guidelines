package com.example.palliativecareguidelines.Doctors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.palliativecareguidelines.Adapters.DoctorAdapter;
import com.example.palliativecareguidelines.R;
import com.example.palliativecareguidelines.modules.Topics;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class DoctorHome extends AppCompatActivity implements DoctorAdapter.ItemClickListener ,DoctorAdapter.ItemClickListener2 {
FloatingActionButton fba;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Topics> items;
    DoctorAdapter[] myListData;
    DoctorAdapter adapter;
    EditText Updatetitle;
    EditText Updatenote;
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        fba=findViewById(R.id.fab);
        rv = findViewById(R.id.recyclerview);
        items = new ArrayList<Topics>();
        adapter =new DoctorAdapter(this,items,this,this);
       getTopics();
        fba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorHome.this, AddTopicsScreen.class));

            }
        });
    }

    public  void getTopics(){
        db.collection("Topics").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d("alaa", "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                                if (documentSnapshot.exists()) {
                                    String id = documentSnapshot.getId();
                                    String title = documentSnapshot.getString("topic_title");
                                    String content = documentSnapshot.getString("topic_content");
                                    String video = documentSnapshot.getString("topic_image");
                                    String image = documentSnapshot.getString("topic_video");


                                    Topics note = new Topics( title ,content,image,video);
                                    items.add(note);

                                    rv.setLayoutManager(layoutManager);
                                    rv.setHasFixedSize(true);
                                    rv.setAdapter(adapter);
                                    ;
                                    adapter.notifyDataSetChanged();
                                    Log.e("LogDATA", items.toString());

                                }
                            }
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("LogDATA", "get failed with ");


                    }
                });
    }
    public  void DeleteTopic(final Topics topics){
        AlertDialog.Builder alertDialogBuilderLabelDelete = new AlertDialog.Builder(this);
        alertDialogBuilderLabelDelete.create();
        alertDialogBuilderLabelDelete.setIcon(R.drawable.trash);
        alertDialogBuilderLabelDelete.setCancelable(false);
        alertDialogBuilderLabelDelete.setTitle("Delete label");
        alertDialogBuilderLabelDelete.setMessage("Are you sure to delete?");
        alertDialogBuilderLabelDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogBox, int id) {
                db.collection("Topics").document(topics.getId())
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                items.remove(topics);
                                Toast.makeText(DoctorHome.this, " Removed Successfully", Toast.LENGTH_SHORT).show();

                                adapter.notifyDataSetChanged();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("logData","get failed with delete");
                            }
                        });

            }
        });
        alertDialogBuilderLabelDelete.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
                    }
                });        alertDialogBuilderLabelDelete.show();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.profiledoctor, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profiledoctor:
                startActivity(new Intent(DoctorHome.this, DoctorProfile.class));

                break;
            case R.id.chatdoctor:
                startActivity(new Intent(DoctorHome.this, ChatDoctor.class));

                break;

        }

        return true;
    }

    @Override
    public void onItemClick(int position, String id) {

    }

    @Override
    public void onItemClick2(int position, String id) {
            DeleteTopic(items.get(position));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}