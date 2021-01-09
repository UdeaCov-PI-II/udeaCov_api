package co.edu.udea.covapi.model;

public class PermissionStatus extends FirebaseModel{
    private String statusId;
    private String displayName;

    public PermissionStatus() {
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
}
