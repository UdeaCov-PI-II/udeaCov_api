package co.edu.udea.covapi.dto.request;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ApprovalRequestDTO implements Serializable {

    @NotEmpty(message = "El usuario no puede ser vacio")
    private String userId;
    private boolean isApproved;
    private String reason;
    @NotEmpty(message = "La acción no puede ser vacia")
    private String action;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
