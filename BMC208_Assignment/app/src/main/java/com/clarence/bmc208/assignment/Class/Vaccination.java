package com.clarence.bmc208.assignment.Class;

import java.io.Serializable;
import java.util.Date;

public class Vaccination implements Serializable {

    private String vaccinationID;
    private String appointmentDate;
    private String status;
    private String remark;
    private String vaccination_patientID;
    private String vaccination_batchID;

    public String getVaccinationID() {
        return vaccinationID;
    }

    public void setVaccinationID(String vaccinationID) {
        this.vaccinationID = vaccinationID;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVaccination_patientID() {
        return vaccination_patientID;
    }

    public void setVaccination_patientID(String vaccination_patientID) {
        this.vaccination_patientID = vaccination_patientID;
    }

    public String getVaccination_batchID() {
        return vaccination_batchID;
    }

    public void setVaccination_batchID(String vaccination_batchID) {
        this.vaccination_batchID = vaccination_batchID;
    }
}
