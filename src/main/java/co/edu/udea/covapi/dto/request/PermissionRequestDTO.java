package co.edu.udea.covapi.dto.request;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

public class PermissionRequestDTO implements Serializable {

    @NotEmpty(message = "El usuario no puede ser vacio")
    private String userId;
    @NotEmpty(message = "La fecha inicial no puede ser vacia")
    private String startTimeStr;
    @NotEmpty(message = "La fecha final no puede ser vacia")
    private String endTimeStr;
    private String statusId;
    @NotEmpty(message = "La información del decano no puede ser vacia")
    private String managerUnitId;
    @NotEmpty(message = "El motivo de ingreso no puede ser vacio")
    private String reason;
    private String locationId;
    private String building;
    private String officeNumber;
    private String locationOutOfUniversity;
    @NotEmpty(message = "Los días de ingreso y permanencia no pueden ser vacíos")
    private List<String> requestedDays;
    @NotEmpty(message = "La jornada de permanencia no puede ser vacia")
    private String requestedWorkingDay;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getManagerUnitId() {
        return managerUnitId;
    }

    public void setManagerUnitId(String managerUnitId) {
        this.managerUnitId = managerUnitId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
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
}
