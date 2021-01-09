package co.edu.udea.covapi.model;

import com.google.cloud.firestore.DocumentReference;

public class ReviewStatus extends FirebaseModel {
    private String statusId;
    private String displayName;
    private DocumentReference status;


    public ReviewStatus() {
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public DocumentReference getStatus() {
        return status;
    }

    public void setStatus(DocumentReference status) {
        this.status = status;
    }
}
