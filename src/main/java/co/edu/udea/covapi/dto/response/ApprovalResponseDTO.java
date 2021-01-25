package co.edu.udea.covapi.dto.response;

public class ApprovalResponseDTO extends BaseResponseDTO {

    private UserResponseDTO user;
    private boolean isApproved;
    private String reason;

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
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
}
