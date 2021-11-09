package com.clarence.bmc208.assignment.Class;

import java.io.Serializable;
import java.util.Date;

public class Batch implements Serializable {

    private String batchNo;
    private Date expiry_date;
    private int numberOfPendingAppointment;
    private int number_available;
    private int number_administered;
    private String batch_vaccineID;
    private String batch_healthcare_centre_name;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public int getNumberOfPendingAppointment() {
        return numberOfPendingAppointment;
    }

    public void setNumberOfPendingAppointment(int numberOfPendingAppointment) {
        this.numberOfPendingAppointment = numberOfPendingAppointment;
    }

    public int getNumber_available() {
        return number_available;
    }

    public void setNumber_available(int number_available) {
        this.number_available = number_available;
    }

    public int getNumber_administered() {
        return number_administered;
    }

    public void setNumber_administered(int number_administered) {
        this.number_administered = number_administered;
    }

    public String getBatch_vaccineID() {
        return batch_vaccineID;
    }

    public void setBatch_vaccineID(String batch_vaccineID) {
        this.batch_vaccineID = batch_vaccineID;
    }

    public String getBatch_healthcare_centre_name() {
        return batch_healthcare_centre_name;
    }

    public void setBatch_healthcare_centre_name(String batch_healthcare_centre_name) {
        this.batch_healthcare_centre_name = batch_healthcare_centre_name;
    }

    @Override
    public String toString() {
        return "Number of Pending Appointment: " + numberOfPendingAppointment +
                "\nVaccineID: " + batch_vaccineID ;
    }
}
