package co.edu.udea.covapi.dto.response;

import java.io.Serializable;

public class UnitResponseDTO extends BaseResponseDTO implements Serializable {

    private String name;
    private PersonResponseDTO manager;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonResponseDTO getManager() {
        return manager;
    }

    public void setManager(PersonResponseDTO manager) {
        this.manager = manager;
    }
}
