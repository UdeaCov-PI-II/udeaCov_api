package co.edu.udea.covapi.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class PermissionMediasRequestDTO implements Serializable {

    private MultipartFile coronAppEvidence;
    private MultipartFile medellinMeCuidaEvidence;

    public MultipartFile getCoronAppEvidence() {
        return coronAppEvidence;
    }

    public void setCoronAppEvidence(MultipartFile coronAppEvidence) {
        this.coronAppEvidence = coronAppEvidence;
    }

    public MultipartFile getMedellinMeCuidaEvidence() {
        return medellinMeCuidaEvidence;
    }

    public void setMedellinMeCuidaEvidence(MultipartFile medellinMeCuidaEvidence) {
        this.medellinMeCuidaEvidence = medellinMeCuidaEvidence;
    }
}
