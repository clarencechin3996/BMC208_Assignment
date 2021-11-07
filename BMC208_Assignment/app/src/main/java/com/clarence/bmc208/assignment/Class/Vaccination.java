package com.clarence.bmc208.assignment.Class;

import java.util.Date;

public class Vaccination {

    private String vaccinationID;
    private Date appointmentDate;
    private String status;
    private String remark;
    private String vaccination_patientID;
    private String vaccination_batchNo;

    public String getVaccinationID() {
        return vaccinationID;
    }

    public void setVaccinationID(String vaccinationID) {
        this.vaccinationID = vaccinationID;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
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

    public String getVaccination_batchNo() {
        return vaccination_batchNo;
    }

    public void setVaccination_batchNo(String vaccination_batchNo) {
        this.vaccination_batchNo = vaccination_batchNo;
    }
}
