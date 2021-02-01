package co.edu.udea.covapi.dto.request;

import java.io.Serializable;

public class EntranceRequestDTO implements Serializable {

    private String permissionId;
    private String temperature;
    private boolean isEntry;


    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public boolean isEntry() {
        return isEntry;
    }

    public void setEntry(boolean entry) {
        isEntry = entry;
    }
}
