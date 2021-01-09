package co.edu.udea.covapi.model;

import com.google.cloud.firestore.DocumentReference;

public class User extends FirebaseModel {
    private String fullName;
    private int age;
    private String username;
    private String password;
    private DocumentReference role;

    public User() {
        // required for firestore
    }
    public User(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DocumentReference getRole() {
        return role;
    }

    public void setRole(DocumentReference role) {
        this.role = role;
    }

}
