package com.clarence.bmc208.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.clarence.bmc208.assignment.Class.Vaccine;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class admin_home_page extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button recordVaccineBatch;
    Button viewVaccineBatchInfoBtn;
    Button addVaccineBtn;
    ArrayList<String> vaccineList;

    ConstraintLayout constraintLayout;
    Vaccine vaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        constraintLayout = findViewById(R.id.constraint_layout_admin_home_page);
        viewVaccineBatchInfoBtn = findViewById(R.id.button_view_vaccine_batch_info);
        addVaccineBtn = findViewById(R.id.button_view_add_vaccine);

        viewVaccineBatchInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_home_page.this, vaccineBatchList.class));
            }
        });


        addVaccineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(admin_home_page.this);
                builder.setTitle("Add Vaccine");
                builder.setCancelable(true);
                builder.setMessage("Enter vaccine name and manufacturer");

                final EditText vaccineNameInput = new EditText(admin_home_page.this);
                vaccineNameInput.setHint("  Vaccine Name:");
                final EditText manufacturerInput = new EditText(admin_home_page.this);
                manufacturerInput.setHint("  Manufacturer:");
                LinearLayout layout = new LinearLayout(getApplicationContext());
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(vaccineNameInput);
                layout.addView(manufacturerInput);
                builder.setView(layout);

                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String vaccineName = vaccineNameInput.getText().toString();
                        String manufacturer = manufacturerInput.getText().toString();
                        if(vaccineName.isEmpty()){
                            Snackbar snackbar = Snackbar.make(constraintLayout,"Please enter vaccine name !",Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }else if(manufacturer.isEmpty()){
                            Snackbar snackbar = Snackbar.make(constraintLayout,"Please enter manufacturer !",Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }else{
                            db.collection("Vaccine")
                                    .whereEqualTo("vaccine_name", vaccineName)
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                String t ="";
                                                vaccineList = new ArrayList<>();
                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    Vaccine vaccine = document.toObject(Vaccine.class);
                                                    vaccineList.add(vaccine.getVaccine_name());
                                                    t = vaccine.getVaccine_name();
                                                }
                                                    if(vaccineName.equalsIgnoreCase(t)){
                                                        Snackbar snackbar = Snackbar.make(constraintLayout,"Duplicate Vaccine Name!",Snackbar.LENGTH_SHORT);
                                                        snackbar.show();
                                                    }else{
                                                        DocumentReference vaccineDocRef = db.collection("Vaccine").document();
                                                        vaccine = new Vaccine();
                                                        vaccine.setVaccineID(vaccineDocRef.getId());
                                                        vaccine.setVaccine_name(vaccineName);
                                                        vaccine.setManufacturer(manufacturer);

                                                        db.collection("Vaccine")
                                                                .document()
                                                                .set(vaccine)
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        Snackbar snackbar = Snackbar.make(constraintLayout,"Vaccine successfully added !",Snackbar.LENGTH_SHORT);
                                                                        snackbar.show();
                                                                    }
                                                                });
                                                    }
                                            }
                                        }
                                    });
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });
                builder.show();
            }
        });



    }
}