package com.example.healthsl1;

public class Chanalling {

    String chanallingId;
    String startTime;
    String endTime;
    String maxPatients;
    String doctorID;
    String doctorName;
    String selectedDayOfWeek;
    String specification;

        public Chanalling(){

        }

        public Chanalling(String chanallingId,String selectedDayOfWeek,String doctorID,String doctorName, String startTime,String endTime,String maxPatients){
            this.chanallingId=chanallingId;
            this.startTime=startTime;
            this.endTime=endTime;
            this.doctorID=doctorID;
            this.doctorName=doctorName;
            this.selectedDayOfWeek = selectedDayOfWeek;
            this.maxPatients = maxPatients;


        }



    public String getSelectedDayOfWeek() {
        return selectedDayOfWeek;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getChanallingId() {
        return chanallingId;
    }

    @Override
    public String toString() {
        return "Chanalling{" +
                "chanallingId='" + chanallingId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", maxPatients='" + maxPatients + '\'' +
                ", doctorID='" + doctorID + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", selectedDayOfWeek='" + selectedDayOfWeek + '\'' +
                ", specification='" + specification + '\'' +
                '}';
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getMaxPatients() {
        return maxPatients;
    }


}
