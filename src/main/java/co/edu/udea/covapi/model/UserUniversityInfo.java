package co.edu.udea.covapi.model;

import com.google.cloud.firestore.DocumentReference;

public class UserUniversityInfo extends FirebaseModel{

    private String universityRelation;
    private String detailUniversityRelation;
    private String buildingAndOffice;
    private DocumentReference unit;
    private DocumentReference location;
    private boolean belongToExtensionProject;
    private String extensionProjectName;
    private String transportationMode;

    public UserUniversityInfo() {
        // required for firestore
    }

    public String getUniversityRelation() {
        return universityRelation;
    }

    public void setUniversityRelation(String universityRelation) {
        this.universityRelation = universityRelation;
    }

    public String getDetailUniversityRelation() {
        return detailUniversityRelation;
    }

    public void setDetailUniversityRelation(String detailUniversityRelation) {
        this.detailUniversityRelation = detailUniversityRelation;
    }

    public String getBuildingAndOffice() {
        return buildingAndOffice;
    }

    public void setBuildingAndOffice(String buildingAndOffice) {
        this.buildingAndOffice = buildingAndOffice;
    }

    public DocumentReference getUnit() {
        return unit;
    }

    public void setUnit(DocumentReference unit) {
        this.unit = unit;
    }

    public DocumentReference getLocation() {
        return location;
    }

    public void setLocation(DocumentReference location) {
        this.location = location;
    }

    public boolean isBelongToExtensionProject() {
        return belongToExtensionProject;
    }

    public void setBelongToExtensionProject(boolean belongToExtensionProject) {
        this.belongToExtensionProject = belongToExtensionProject;
    }

    public String getExtensionProjectName() {
        return extensionProjectName;
    }

    public void setExtensionProjectName(String extensionProjectName) {
        this.extensionProjectName = extensionProjectName;
    }

    public String getTransportationMode() {
        return transportationMode;
    }

    public void setTransportationMode(String transportationMode) {
        this.transportationMode = transportationMode;
    }
}
