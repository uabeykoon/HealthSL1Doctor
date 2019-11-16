package com.example.healthsl1;

public class Patient {
    String patientId;
    String firstName;
    String lastName;


    public Patient(String patientId, String firstName, String lastName) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
