package co.edu.udea.covapi.dto.response;

import java.io.Serializable;

public class PermissionItemListResponseDTO extends BaseResponseDTO implements Serializable {

    private UserInfoResponseDTO user;
    private String startTimeStr;
    private String endTimeStr;
    private String location;

    public UserInfoResponseDTO getUser() {
        return user;
    }

    public void setUser(UserInfoResponseDTO user) {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
