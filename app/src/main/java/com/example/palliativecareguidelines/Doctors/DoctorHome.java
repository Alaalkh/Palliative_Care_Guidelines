//package com.example.palliativecareguidelines.Doctors;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.annotation.SuppressLint;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.example.palliativecareguidelines.R;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.util.ArrayList;
//
//
//public class DoctorHome extends AppCompatActivity implements Doctorshow.ItemClickListener,Doctorshow.ItemClickListener2 {
//FloatingActionButton fba;
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    ArrayList<ShowDoctor> items;
//    Doctorshow[] myListData;
//    Doctorshow adapter;
//    EditText Updatetitle;
//    EditText Updatenote;
//    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//    RecyclerView rv;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_doctor_home);
//        fba=findViewById(R.id.fab);
//        rv = findViewById(R.id.recyclerview);
//        items = new ArrayList<ShowDoctor>();
//        adapter =new Doctorshow(this,items,this,this);
//        getTopics();
//        fba.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(DoctorHome.this, AddTopicsScreen.class));
//
//            }
//        });
//    }
//
//    public  void getTopics(){
//        db.collection("Topics").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @SuppressLint("NotifyDataSetChanged")
//                    @Override
//                    public void onSuccess(QuerySnapshot documentSnapshots) {
//                        if (documentSnapshots.isEmpty()) {
//                            Log.d("alaa", "onSuccess: LIST EMPTY");
//                            return;
//                        } else {
//                            for (DocumentSnapshot documentSnapshot : documentSnapshots) {
//                                if (documentSnapshot.exists()) {
//                                    String id = documentSnapshot.getId();
//                                    String title = documentSnapshot.getString("topic_title");
//
//
//                                    ShowDoctor note = new ShowDoctor(id, title );
//                                    items.add(note);
//
//                                    rv.setLayoutManager(layoutManager);
//                                    rv.setHasFixedSize(true);
//                                    rv.setAdapter(adapter);
//                                    ;
//                                    adapter.notifyDataSetChanged();
//                                    Log.e("LogDATA", items.toString());
//
//                                }
//                            }
//                        }
//
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e("LogDATA", "get failed with ");
//
//
//                    }
//                });
//    }
//    public  void Delete(final ShowDoctor showDoctor){
//        AlertDialog.Builder alertDialogBuilderLabelDelete = new AlertDialog.Builder(this);
//        alertDialogBuilderLabelDelete.create();
//        alertDialogBuilderLabelDelete.setIcon(R.drawable.trash);
//        alertDialogBuilderLabelDelete.setCancelable(false);
//        alertDialogBuilderLabelDelete.setTitle("Delete label");
//        alertDialogBuilderLabelDelete.setMessage("Are you sure to delete?");
//        alertDialogBuilderLabelDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialogBox, int id) {
//                db.collection("Topics").document(showDoctor.getId())
//                        .delete()
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                items.remove(showDoctor);
//                                Toast.makeText(DoctorHome.this, " Removed Successfully", Toast.LENGTH_SHORT).show();
//
//                                adapter.notifyDataSetChanged();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.e("logData","get failed with delete");
//                            }
//                        });
//
//            }
//        });
//        alertDialogBuilderLabelDelete.setNegativeButton("No",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialogBox, int id) {
//                        dialogBox.cancel();
//                    }
//                });        alertDialogBuilderLabelDelete.show();
//    }
//
//    @Override
//    public void onItemClick(int position, String id) {
//      Delete(items.get(position));
//    }
//
//    @Override
//    public void onItemClick2(int position, String id) {
//        update();
//
//    }
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.profiledoctor, menu);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.profiledoctor:
//                startActivity(new Intent(DoctorHome.this, DoctorProfile.class));
//
//                break;
//            case R.id.chatdoctor:
//                startActivity(new Intent(DoctorHome.this, ChatDoctor.class));
//
//                break;
//
//        }
//
//        return true;
//    }
//}