package co.edu.udea.covapi.dto.response;

import java.io.Serializable;

public class StatusResponseDTO extends BaseResponseDTO implements Serializable {

    private String statusId;
    private String displayName;
    private String action;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
