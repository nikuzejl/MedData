package com.example.meddata;

public class Visit {
    private String date, hospital, doctor, tests, diagnosis, treatment, comments;

    public Visit(){}

    public Visit(String date, String hospital, String doctor, String tests, String diagnosis, String treatment, String comments){
        this.date = date;
        this.hospital = hospital;
        this.doctor = doctor;
        this.tests = tests;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.comments = comments;
    }
    public String getDate(){return date;}
    public String getHospital(){return hospital;}
    public String getDoctor(){return doctor;}
    public String getTests(){return tests;}
    public String getDiagnosis(){return diagnosis;}
    public String getTreatment(){return treatment;}
    public String getComments(){return comments;}

}
