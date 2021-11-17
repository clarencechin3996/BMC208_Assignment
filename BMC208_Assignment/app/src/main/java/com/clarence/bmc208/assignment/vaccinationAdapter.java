package com.clarence.bmc208.assignment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.clarence.bmc208.assignment.Class.Vaccination;


import java.util.ArrayList;

public class vaccinationAdapter extends RecyclerView.Adapter<vaccinationAdapter.VaccinationViewHolder>{
    ArrayList<Vaccination> vaccinationList;
    public vaccinationAdapter(ArrayList<Vaccination> vaccinationList) {
        this.vaccinationList = vaccinationList;
    }

    @Override
    public int getItemCount() {
        return vaccinationList.size();
    }

    @NonNull
    @Override
    public vaccinationAdapter.VaccinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vaccination_list, parent, false);
        return new vaccinationAdapter.VaccinationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vaccinationAdapter.VaccinationViewHolder holder, int position) {
        holder.bind(vaccinationList.get(position));
    }


    public static class VaccinationViewHolder extends RecyclerView.ViewHolder {
        TextView status;
        TextView appointmentDate;

        public VaccinationViewHolder(@NonNull View view) {
            super(view);
            status = view.findViewById(R.id.text_view_vaccination_status);
            appointmentDate = view.findViewById(R.id.text_view_vaccination_appointment_date);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Vaccination vaccination) {
            status.setText("Status: "+vaccination.getStatus());
            appointmentDate.setText("Appointment Date: "+vaccination.getAppointmentDate());
        }
    }
}
