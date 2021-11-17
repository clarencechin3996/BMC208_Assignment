package com.clarence.bmc208.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.clarence.bmc208.assignment.Class.Batch;
import com.clarence.bmc208.assignment.Class.Vaccination;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class manageVaccinationAppointment extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Vaccination selectedVaccination;
    Batch selectedBatch;
    TextView fullName;
    TextView ic_passport;
    TextView batchNo;
    TextView expiryDate;
    TextView manufacturer;
    TextView vaccineName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_vaccination_appointment);

        selectedVaccination = (Vaccination) getIntent().getSerializableExtra("vaccination");
        selectedBatch = (Batch) getIntent().getSerializableExtra("batch");
        fullName = findViewById(R.id.text_view_fullname);
        ic_passport = findViewById(R.id.text_view_ic_passport);
        batchNo = findViewById(R.id.text_view_Batch_No);
        expiryDate = findViewById(R.id.text_view_Expiry_Date);
        manufacturer = findViewById(R.id.text_view_Manufacturer);
        vaccineName = findViewById(R.id.text_view_Vaccine_Name);


        DocumentReference docRef = db.collection("Patient").document(selectedVaccination.getVaccination_patientID());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        fullName.setText("FullName: " + document.get("fullName"));
                        ic_passport.setText("IC/Passport: "+ document.get("ic_passport"));
                    }
                }
            }
        });
        DocumentReference DocRef = db.collection("Batch").document(selectedVaccination.getVaccination_batchID());
        DocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        batchNo.setText("Batch No: "+ document.get("batchNo"));
                        expiryDate.setText("Expiry Date: "+document.get("expiry_date"));


                    }
                }
            }
        });

        DocumentReference DR = db.collection("Vaccine").document(selectedBatch.getBatch_vaccineID());
        DR.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        manufacturer.setText("Manufacturer: " + document.get("manufacturer"));
                        vaccineName.setText("Vaccine Name: "+ document.get("vaccine_name"));
                    }
                }
            }
        });

    }
}