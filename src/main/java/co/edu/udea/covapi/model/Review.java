package co.edu.udea.covapi.model;

import com.google.cloud.firestore.DocumentReference;

public class Review extends FirebaseModel{
    private String comments;
    private User reviewer;
    private ReviewStatus status;
    private DocumentReference permission;


    public Review() {
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public ReviewStatus getStatus() {
        return status;
    }

    public void setStatus(ReviewStatus status) {
        this.status = status;
    }

    public DocumentReference getPermission() {
        return permission;
    }

    public void setPermission(DocumentReference permission) {
        this.permission = permission;
    }
}
