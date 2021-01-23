package co.edu.udea.covapi.model;

import com.google.cloud.firestore.DocumentReference;

import java.util.Date;
import java.util.List;

public class Permission extends FirebaseModel {

    private DocumentReference user;
    private Date startTime;
    private Date endTime;
    private DocumentReference status;
    private DocumentReference managerUnit;
    private String reason;
    private DocumentReference location;
    private String building;
    private String officeNumber;
    private String locationOutOfUniversity;
    private List<String> requestedDays;
    private List<Media> medias;
    private String requestedWorkingDay;

    public Permission() {
        // required for firestore
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

    public DocumentReference getStatus() {
        return status;
    }

    public void setStatus(DocumentReference status) {
        this.status = status;
    }

    public DocumentReference getManagerUnit() {
        return managerUnit;
    }

    public void setManagerUnit(DocumentReference managerUnit) {
        this.managerUnit = managerUnit;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public DocumentReference getLocation() {
        return location;
    }

    public void setLocation(DocumentReference location) {
        this.location = location;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getLocationOutOfUniversity() {
        return locationOutOfUniversity;
    }

    public void setLocationOutOfUniversity(String locationOutOfUniversity) {
        this.locationOutOfUniversity = locationOutOfUniversity;
    }

    public List<String> getRequestedDays() {
        return requestedDays;
    }

    public void setRequestedDays(List<String> requestedDays) {
        this.requestedDays = requestedDays;
    }

    public String getRequestedWorkingDay() {
        return requestedWorkingDay;
    }

    public void setRequestedWorkingDay(String requestedWorkingDay) {
        this.requestedWorkingDay = requestedWorkingDay;
    }

    public List<Media> getMedias() {
        return medias;
    }

    public void setMedias(List<Media> medias) {
        this.medias = medias;
    }
}
