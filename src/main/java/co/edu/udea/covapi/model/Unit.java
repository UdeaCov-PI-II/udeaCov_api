package co.edu.udea.covapi.model;


public class Unit extends FirebaseModel {

    private String name;
    private Person manager;

    public Unit() {
        // required for firestore
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }
}
