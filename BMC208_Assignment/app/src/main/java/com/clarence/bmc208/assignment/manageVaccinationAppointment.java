package com.clarence.bmc208.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.clarence.bmc208.assignment.Class.Batch;
import com.clarence.bmc208.assignment.Class.Vaccination;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
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
    Button confirmBtn;
    Button rejectBth;
    Button recordBtn;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_vaccination_appointment);

        constraintLayout = findViewById(R.id.constraint_layout_vaccination);
        selectedVaccination = (Vaccination) getIntent().getSerializableExtra("vaccination");
        selectedBatch = (Batch) getIntent().getSerializableExtra("batch");

        fullName = findViewById(R.id.text_view_fullname);
        ic_passport = findViewById(R.id.text_view_ic_passport);
        batchNo = findViewById(R.id.text_view_Batch_No);
        expiryDate = findViewById(R.id.text_view_Expiry_Date);
        manufacturer = findViewById(R.id.text_view_Manufacturer);
        vaccineName = findViewById(R.id.text_view_Vaccine_Name);

        confirmBtn = findViewById(R.id.button_confirm_appointment);
        rejectBth = findViewById(R.id.button_view_reject_appointment);
        recordBtn = findViewById(R.id.button_view_record_appointment);

        displayPatient();
        displayBatch();
        displayVaccine();

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = db.collection("Vaccination").document(selectedVaccination.getVaccinationID());
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                if(document.get("status").equals("CONFIRMED")){
                                    Snackbar snackbar = Snackbar.make(constraintLayout,"Vaccination Appointment had been confirmed",Snackbar.LENGTH_SHORT);
                                    snackbar.show();
                                }else{
                                    DocumentReference updateBatchDB = db.collection("Batch").document(selectedBatch.getBatchID());
                                    updateBatchDB.update(
                                            "numberOfPendingAppointment",selectedBatch.getNumberOfPendingAppointment()-1
                                    ).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            DocumentReference updateVaccinationDB = db.collection("Vaccination").document(selectedVaccination.getVaccinationID());
                                            updateVaccinationDB.update(
                                                    "status","CONFIRMED"
                                            ).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Snackbar snackbar = Snackbar.make(constraintLayout,"Vaccination Appointment Confirmed",Snackbar.LENGTH_SHORT);
                                                    snackbar.show();
                                                }
                                            });
                                        }
                                    });
                                }
                            }
                        }
                    }
                });


            }
        });

        

    }

    private void displayVaccine(){
        DocumentReference vaccineDocRef = db.collection("Vaccine").document(selectedBatch.getBatch_vaccineID());
        vaccineDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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
    };

    private void displayBatch(){
        DocumentReference batchDocRef = db.collection("Batch").document(selectedVaccination.getVaccination_batchID());
        batchDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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
    };

    private void displayPatient(){
        DocumentReference patientDocRef = db.collection("Patient").document(selectedVaccination.getVaccination_patientID());
        patientDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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
    };
}