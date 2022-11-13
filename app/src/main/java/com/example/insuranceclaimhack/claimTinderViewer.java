package com.example.insuranceclaimhack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class claimTinderViewer extends AppCompatActivity {
    private FirebaseFirestore db;
    private TextView nameView,claimNameView,claimDescView, claimCategory, claimNumber;
    private  String category;
    private Button btnNo, btnYes;
    private String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_tinder_viewer);
        Bundle extras = getIntent().getExtras();

        nameView = (TextView) findViewById(R.id.name);
        claimNameView = (TextView) findViewById(R.id.claimName);
        claimCategory = (TextView) findViewById(R.id.category);
        claimDescView = (TextView) findViewById(R.id.claimDesc);
        btnNo = (Button) findViewById(R.id.Button02);
        btnYes = (Button) findViewById(R.id.Button03);
        claimNumber = (TextView) findViewById(R.id.connectNumber);

        if (extras != null) {
            category = extras.getString("category");

        }

        db = FirebaseFirestore.getInstance();

        db.collection("Claims").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are
                            // hiding our progress bar and adding
                            // our data in a list.
                            //loadingPB.setVisibility(View.GONE);
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing
                                // that list to our object class.
                                //Courses c = d.toObject(Courses.class);
                                if(category.equals(d.get("Category").toString()) && d.get("Solved").toString().equals("Yes")) {
                                    nameView.setText((d.get("Name").toString()));
                                    claimNameView.setText( d.get("Claim Name").toString());
                                    claimDescView.setText(d.get("Claim Description").toString());
                                    claimCategory.setText(d.get("Category").toString());
                                    //id = d.getId();
                                    number = d.get("Phone").toString();

                                }
                                // and we will pass this object class
                                // inside our arraylist which we have
                                // created for recycler view.
                                //coursesArrayList.add(c);
                            }
                            Toast.makeText(getApplicationContext(),
                                            "Success!!",
                                            Toast.LENGTH_LONG)
                                    .show();
                            // after adding the data to recycler view.
                            // we are calling recycler view notifuDataSetChanged
                            // method to notify that data has been changed in recycler view.
                            //courseRVAdapter.notifyDataSetChanged();
                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            //Toast.makeText( "No data found in Database", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),
                                            "No Data!!",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // if we do not get any data or any error we are displaying
                        // a toast message that we do not get any data
                        //Toast.makeText(CourseDetails.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),
                                        "Fail!!",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayNumber();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayStateFarm();
            }
        });

    }

    private void displayStateFarm() {
        claimNumber.setText("Contact State Farm");
        claimNumber.setVisibility(View.VISIBLE);
    }

    private void displayNumber() {
        claimNumber.setText(number);
        claimNumber.setVisibility(View.VISIBLE);
    }

}