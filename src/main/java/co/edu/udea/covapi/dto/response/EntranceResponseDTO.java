package co.edu.udea.covapi.dto.response;

import java.io.Serializable;

public class EntranceResponseDTO extends BaseResponseDTO implements Serializable {

    private String permissionId;
    private String entryTimeStr;
    private String entryTemperature;
    private String departureTimeStr;
    private String departureTemperature;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getEntryTimeStr() {
        return entryTimeStr;
    }

    public void setEntryTimeStr(String entryTimeStr) {
        this.entryTimeStr = entryTimeStr;
    }

    public String getEntryTemperature() {
        return entryTemperature;
    }

    public void setEntryTemperature(String entryTemperature) {
        this.entryTemperature = entryTemperature;
    }

    public String getDepartureTimeStr() {
        return departureTimeStr;
    }

    public void setDepartureTimeStr(String departureTimeStr) {
        this.departureTimeStr = departureTimeStr;
    }

    public String getDepartureTemperature() {
        return departureTemperature;
    }

    public void setDepartureTemperature(String departureTemperature) {
        this.departureTemperature = departureTemperature;
    }
}
