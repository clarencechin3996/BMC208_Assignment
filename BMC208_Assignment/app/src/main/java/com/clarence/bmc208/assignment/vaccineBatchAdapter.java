package com.clarence.bmc208.assignment;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.clarence.bmc208.assignment.Class.Batch;
import com.clarence.bmc208.assignment.Class.Vaccine;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class vaccineBatchAdapter extends RecyclerView.Adapter<vaccineBatchAdapter.VaccineBatchViewHolder> {

    ArrayList<Batch> batchList;

    public vaccineBatchAdapter(ArrayList<Batch> batchList) {
        this.batchList = batchList;
    }

    @Override
    public int getItemCount() {
        return batchList.size();
    }

    @NonNull
    @Override
    public VaccineBatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vaccine_batch, parent, false);
        return new VaccineBatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccineBatchViewHolder holder, int position) {
        holder.bind(batchList.get(position));
    }


    public static class VaccineBatchViewHolder extends RecyclerView.ViewHolder {
        TextView batchNo;
        TextView vaccineName;
        TextView noOfPendingAppointment;
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        public VaccineBatchViewHolder(@NonNull View view) {
            super(view);
            batchNo = itemView.findViewById(R.id.text_view_batch_no);
            vaccineName = itemView.findViewById(R.id.text_view_vaccine_name);
            noOfPendingAppointment = itemView.findViewById(R.id.text_view_no_pending_appointment);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Batch batch) {
            batchNo.setText("Batch No: "+ batch.getBatchNo());

            DocumentReference docRef = db.collection("Vaccine").document(batch.getBatch_vaccineID());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            vaccineName.setText("Vaccine name: " +document.get("vaccine_name"));
                        }
                    }
                }
            });

            noOfPendingAppointment.setText("Number of pending appointment: "+ batch.getNumberOfPendingAppointment());
        }
    }
}