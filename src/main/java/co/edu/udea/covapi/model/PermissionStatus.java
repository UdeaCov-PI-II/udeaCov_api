package co.edu.udea.covapi.model;

import com.google.cloud.firestore.DocumentReference;

import java.util.List;

public class PermissionStatus extends FirebaseModel{
    private String statusId;
    private String displayName;
    private List<DocumentReference> reviewersRoles;
    private String action;
    private boolean isInitial;
    private boolean isFinal;
    private DocumentReference nextStatus;

    public PermissionStatus() {
        //required for firestore
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

    public List<DocumentReference> getReviewersRoles() {
        return reviewersRoles;
    }

    public void setReviewersRoles(List<DocumentReference> reviewersRoles) {
        this.reviewersRoles = reviewersRoles;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isInitial() {
        return isInitial;
    }

    public void setIsInitial(boolean initial) {
        isInitial = initial;
    }

    public DocumentReference getNextStatus() {
        return nextStatus;
    }

    public void setNextStatus(DocumentReference nextStatus) {
        this.nextStatus = nextStatus;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setIsFinal(boolean aFinal) {
        isFinal = aFinal;
    }
}
