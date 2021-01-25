package co.edu.udea.covapi.dto.response;

import java.io.Serializable;

public class LocationResponseDTO extends BaseResponseDTO implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
