package co.edu.udea.covapi.dto.response;

import java.io.Serializable;

public class MediaResponseDTO extends BaseResponseDTO implements Serializable {

    private String originalName;
    private String name;
    private String dowloadUrl;
    private String type;

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDowloadUrl() {
        return dowloadUrl;
    }

    public void setDowloadUrl(String dowloadUrl) {
        this.dowloadUrl = dowloadUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
