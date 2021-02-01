package co.edu.udea.covapi.model;

import com.google.cloud.firestore.DocumentReference;

import java.util.Date;

public class Entrance extends FirebaseModel {

    private DocumentReference permission;
    private Date entryTime;
    private String entryTemperature;
    private Date departureTime;
    private String departureTemperature;


    public Entrance(){
        //required for firestore
    }

    public DocumentReference getPermission() {
        return permission;
    }

    public void setPermission(DocumentReference permission) {
        this.permission = permission;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getEntryTemperature() {
        return entryTemperature;
    }

    public void setEntryTemperature(String entryTemperature) {
        this.entryTemperature = entryTemperature;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureTemperature() {
        return departureTemperature;
    }

    public void setDepartureTemperature(String departureTemperature) {
        this.departureTemperature = departureTemperature;
    }
}
