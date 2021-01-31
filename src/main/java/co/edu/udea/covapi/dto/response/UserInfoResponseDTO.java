package co.edu.udea.covapi.dto.response;

import java.io.Serializable;

public class UserInfoResponseDTO extends BaseResponseDTO implements Serializable {

    private String fullName;
    private String documentType;
    private String documentNumber;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
