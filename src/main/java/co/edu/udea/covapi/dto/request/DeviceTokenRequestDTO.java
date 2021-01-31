package co.edu.udea.covapi.dto.request;

import java.io.Serializable;

public class DeviceTokenRequestDTO implements Serializable {

    private String deviceToken;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
