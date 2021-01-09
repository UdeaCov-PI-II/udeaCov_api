package co.edu.udea.covapi.dto;

import java.io.Serializable;

public class BaseResponseDTO implements Serializable {
    private String id;

    public BaseResponseDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
