package co.edu.udea.covapi.dto.response;


import java.io.Serializable;
import java.util.List;

public class PermissionResponseDTO extends BaseResponseDTO implements Serializable {

    private UserResponseDTO user;
    private String startTimeStr;
    private String endTimeStr;
    private String location;
    private StatusResponseDTO status;
    private List<MediaResponseDTO> medias;
    private List<ApprovalResponseDTO> approvals;


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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public StatusResponseDTO getStatus() {
        return status;
    }

    public void setStatus(StatusResponseDTO status) {
        this.status = status;
    }

    public List<MediaResponseDTO> getMedias() {
        return medias;
    }

    public void setMedias(List<MediaResponseDTO> medias) {
        this.medias = medias;
    }

    public List<ApprovalResponseDTO> getApprovals() {
        return approvals;
    }

    public void setApprovals(List<ApprovalResponseDTO> approvals) {
        this.approvals = approvals;
    }
}
