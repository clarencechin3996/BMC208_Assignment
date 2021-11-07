package com.clarence.bmc208.assignment.Class;

public class Administrator {

    private String administratorID;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String staffID;
    private String healthcare_center;

    public String getAdministratorID() {
        return administratorID;
    }

    public void setAdministratorID(String administratorID) {
        this.administratorID = administratorID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getHealthcare_center() {
        return healthcare_center;
    }

    public void setHealthcare_center(String healthcare_center) {
        this.healthcare_center = healthcare_center;
    }
}
