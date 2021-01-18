package co.edu.udea.covapi.model;

import com.google.cloud.firestore.DocumentReference;

public class Unit extends FirebaseModel{

    private String name;
    private DocumentReference manager;

    public Unit(){
        // required for firestore
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocumentReference getManager() {
        return manager;
    }

    public void setManager(DocumentReference manager) {
        this.manager = manager;
    }
}
