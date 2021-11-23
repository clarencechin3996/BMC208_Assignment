package com.clarence.bmc208.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.clarence.bmc208.assignment.Class.Batch;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class vaccineBatchList extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Batch> batchList;
    RecyclerView recyclerView;
    TextView healthcareName;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_batch_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        healthcareName = findViewById(R.id.text_view_healthcare_centre);
        recyclerView = findViewById(R.id.recycler_view_vaccine_batch_list);

        healthcareName.setText("HMC Hospital");
        displayVaccineBatch();

    }


    private void displayVaccineBatch(){
        db.collection("Batch")
                .whereEqualTo("batch_healthcare_centre_name", "HMC Hospital")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            batchList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Batch batch = document.toObject(Batch.class);
                                batchList.add(batch);
                            }
                            vaccineBatchAdapter VBAdapter = new vaccineBatchAdapter(batchList);
                            recyclerView.setAdapter(VBAdapter);
                            recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(recyclerView, new RecyclerViewItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Intent intent = new Intent(vaccineBatchList.this, viewVaccineBatchInfo.class);

                                    intent.putExtra("batch", batchList.get(position));
                                    startActivity(intent);
                                }
                            }));
                        }
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        displayVaccineBatch();
    }
}