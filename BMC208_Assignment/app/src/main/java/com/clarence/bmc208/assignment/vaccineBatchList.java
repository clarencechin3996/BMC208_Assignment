package com.clarence.bmc208.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.clarence.bmc208.assignment.Class.Batch;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class vaccineBatchList extends AppCompatActivity {

    ConstraintLayout constraintLayoutNotes;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ListView listViewBatch;
    List<Batch> batchArray;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_batch_list);

        listViewBatch = findViewById(R.id.list_view_vaccine_batch);
        constraintLayoutNotes = findViewById(R.id.layout_vaccine_batch);
        getVaccineBatch();

        listViewBatch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(vaccineBatchList.this, viewVaccineBatchInfo.class);
                Batch batch = batchArray.get(position);
                intent.putExtra("Batch", batch);
                startActivity(intent);
            }
        });
    }

    private void getVaccineBatch(){
        db.collection("Batch")
                .whereEqualTo("centreName", "HMC Hospital")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    batchArray = new ArrayList<>();
                    for(QueryDocumentSnapshot document : task.getResult()){
                        Batch batch = document.toObject(Batch.class);
                        batchArray.add(batch);
                    }
                    adapter = new ArrayAdapter<Batch>(vaccineBatchList.this,
                            android.R.layout.simple_list_item_1, batchArray);
                    listViewBatch.setAdapter(adapter);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getVaccineBatch();
    }
}