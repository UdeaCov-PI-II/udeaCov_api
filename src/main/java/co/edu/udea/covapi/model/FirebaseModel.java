package co.edu.udea.covapi.model;

import java.util.Date;

public class FirebaseModel {
    private String modelId;
    private Date creationTime;

    public FirebaseModel(){
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
