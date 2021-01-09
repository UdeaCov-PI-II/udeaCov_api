package co.edu.udea.covapi.dto;

import co.edu.udea.covapi.model.PermissionStatus;

import java.io.Serializable;

public class PermissionResponseDTO extends BaseResponseDTO implements Serializable {

    private UserResponseDTO user;
    private String startTimeStr;
    private String endTimeStr;
    private String place;
    private PermissionStatus status;

    public PermissionResponseDTO() {
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public PermissionStatus getStatus() {
        return status;
    }

    public void setStatus(PermissionStatus status) {
        this.status = status;
    }
}
