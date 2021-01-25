package co.edu.udea.covapi.model;

import com.google.cloud.firestore.DocumentReference;

public class Approval extends FirebaseModel {

    private DocumentReference reviewer;
    private boolean isApproved;
    private String reason;

    public Approval() {
        //required for firestore
    }

    public DocumentReference getReviewer() {
        return reviewer;
    }

    public void setReviewer(DocumentReference reviewer) {
        this.reviewer = reviewer;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
