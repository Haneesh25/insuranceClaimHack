package com.example.insuranceclaimhack;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.insuranceclaimhack.databinding.ActivityWriteClaimBinding;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class writeClaim extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final String TAG = "";
    private EditText nameTextView, claimNameTextView,claimDescTextView;
    private Spinner categorySpinner;
    private Button claimBtn;
    private ProgressBar progressBar;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_write_claim);
        db = FirebaseFirestore.getInstance();
        nameTextView = findViewById(R.id.name);
        claimNameTextView = findViewById(R.id.claimName);
        claimDescTextView = findViewById(R.id.claimDesc);

        categorySpinner = findViewById(R.id.category);
        String[] items = new String[]{"Pet", "Property", "Renter", "Life", "Business", "Auto"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        categorySpinner.setAdapter(adapter);


        claimBtn = findViewById(R.id.btnclaim);
        progressBar = findViewById(R.id.progressbar);
        claimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                addClaim();
            }
        });
    }

    private void addClaim() {
        progressBar.setVisibility(View.VISIBLE);
        String name, claimName, claimDesc, category;
        name = nameTextView.getText().toString();
        claimName = claimNameTextView.getText().toString();
        claimDesc = claimDescTextView.getText().toString();
        category = categorySpinner.getSelectedItem().toString();

        if (TextUtils.isEmpty(claimName)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter claim!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        Map<String, String> user = new HashMap<>();
        user.put("Name", name);
        user.put("Claim Name", claimName);
        user.put("Claim Description", claimDesc);
        user.put("Category", category);
        user.put("Solved", "No");

// Add a new document with a generated ID
        db.collection("Claims")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(getApplicationContext(),
                                        "Successfully Added",
                                        Toast.LENGTH_LONG)
                                .show();
                        progressBar.setVisibility(View.GONE);
                        Intent intent
                                = new Intent(writeClaim.this,
                                claimViewer.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),
                                        "Unsunc!!"+e,
                                        Toast.LENGTH_LONG)
                                .show();
                        Log.w(TAG, "Error adding document", e);
                        progressBar.setVisibility(View.GONE);
                    }
                });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}