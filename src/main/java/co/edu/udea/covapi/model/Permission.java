package co.edu.udea.covapi.model;

import com.google.cloud.firestore.DocumentReference;

import java.util.Date;

public class Permission extends FirebaseModel {
    private DocumentReference user;
    private Date startTime;
    private Date endTime;
    private String place;
    private DocumentReference status;

    public Permission() {
    }

    public DocumentReference getUser() {
        return user;
    }

    public void setUser(DocumentReference user) {
        this.user = user;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public DocumentReference getStatus() {
        return status;
    }

    public void setStatus(DocumentReference status) {
        this.status = status;
    }
}
