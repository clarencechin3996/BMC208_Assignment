package com.clarence.bmc208.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.clarence.bmc208.assignment.Class.Batch;
import com.clarence.bmc208.assignment.Class.Vaccination;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class viewVaccineBatchInfo extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView recyclerView;
    ArrayList<Vaccination>  vaccinationList;
    Batch selectedBatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vaccine_batch_info);
        recyclerView = findViewById(R.id.recycler_view_Vaccination_List);

        selectedBatch = (Batch) getIntent().getSerializableExtra("batch");
        TextView expiryDate = findViewById(R.id.text_view_expiry_date);
        TextView numAvailable = findViewById(R.id.text_view_number_available);
        TextView numAdministered = findViewById(R.id.text_view_number_administered);
        TextView numPendingAppointment = findViewById(R.id.text_view_pending_appointment);

        expiryDate.setText("Expiry Date: "+selectedBatch.getExpiry_date());
        numAvailable.setText("Number Available: "+selectedBatch.getNumber_available());
        numAdministered.setText("Number Administered: "+selectedBatch.getNumber_administered());
        numPendingAppointment.setText("No of Pending Appointment: "+selectedBatch.getNumberOfPendingAppointment());

        displayVaccination();

    }

    private void displayVaccination(){
        db.collection("Vaccination")
                .whereEqualTo("vaccination_batchID", selectedBatch.getBatchID())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        vaccinationList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Vaccination vaccination = document.toObject(Vaccination.class);
                            vaccinationList.add(vaccination);
                        }
                        vaccinationAdapter VAdapter = new vaccinationAdapter(vaccinationList);
                        recyclerView.setAdapter(VAdapter);

                        recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(recyclerView, new RecyclerViewItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(viewVaccineBatchInfo.this, manageVaccinationAppointment.class);
                                intent.putExtra("vaccination",vaccinationList.get(position));
                                intent.putExtra("batch",selectedBatch);
                                startActivity(intent);
                            }
                        }));
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayVaccination();
    }
}