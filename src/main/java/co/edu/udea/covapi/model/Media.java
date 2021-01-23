package co.edu.udea.covapi.model;

public class Media extends FirebaseModel{

    private String originalName;
    private String name;
    private String dowloadUrl;
    private String type;

    public Media(){
        // required for firestore
    }

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
