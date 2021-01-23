package co.edu.udea.covapi.dto.request;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

public class UserUniversityInfoDTO implements Serializable {

    @NotEmpty(message = "El vínculo con la Universidad no puede ser vacio")
    private List<String> universityRelation;
    private String detailUniversityRelation;
    @NotEmpty(message = "El bloque y oficina no puede ser vacio")
    private String buildingAndOffice;
    @NotEmpty(message = "La unidad acádemica/administrativa no puede ser vacio")
    private String unitId;
    @NotEmpty(message = "La ubicación de la sede/edificio no puede ser vacio")
    private String locationId;
    private boolean belongToExtensionProject;
    private String extensionProjectName;
    @NotEmpty(message = "El medio de transporte no puede ser vacio")
    private String transportationMode;
    private String occupation;

    public List<String> getUniversityRelation() {
        return universityRelation;
    }

    public void setUniversityRelation(List<String> universityRelation) {
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

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
