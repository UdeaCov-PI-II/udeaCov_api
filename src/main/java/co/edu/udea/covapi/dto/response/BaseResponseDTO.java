package co.edu.udea.covapi.dto.response;

import java.io.Serializable;

public class BaseResponseDTO implements Serializable {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
