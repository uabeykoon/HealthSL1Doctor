package com.example.healthsl1;

public class MakeChannal {

    String channelingId;
    String userId;
    String doctorId;
    String doctorName;
    String channelNumber;
    String patientName;

    public MakeChannal(String channelingId, String userId, String doctorId, String doctorName,String channelNumber,String patientName) {
        this.channelingId = channelingId;
        this.userId = userId;
        this.doctorName = doctorName;
        this.channelNumber=channelNumber;
        this.doctorId= doctorId;
        this.patientName= patientName;
    }
    public MakeChannal(){}

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getChannelNumber() {
        return channelNumber;
    }

    public String getChannelingId() {
        return channelingId;
    }

    public String getUserId() {
        return userId;
    }

    public String getDoctorName() {
        return doctorName;
    }
}


