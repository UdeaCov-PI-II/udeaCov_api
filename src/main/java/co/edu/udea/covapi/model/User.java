package co.edu.udea.covapi.model;

import com.google.cloud.firestore.DocumentReference;

import java.util.Date;

public class User extends FirebaseModel {

    private String username;
    private String password;
    private String email;
    private String documentType;
    private String documentNumber;
    private String fullName;
    private Date birthday;
    private String personalEmail;
    private String arlName;
    private String town;
    private UserUniversityInfo universityInfo;
    private UserHealthInfo healthInfo;
    private DocumentReference role;

    public User() {
        // required for firestore
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getArlName() {
        return arlName;
    }

    public void setArlName(String arlName) {
        this.arlName = arlName;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public UserUniversityInfo getUniversityInfo() {
        return universityInfo;
    }

    public void setUniversityInfo(UserUniversityInfo universityInfo) {
        this.universityInfo = universityInfo;
    }

    public UserHealthInfo getHealthInfo() {
        return healthInfo;
    }

    public void setHealthInfo(UserHealthInfo healthInfo) {
        this.healthInfo = healthInfo;
    }

    public DocumentReference getRole() {
        return role;
    }

    public void setRole(DocumentReference role) {
        this.role = role;
    }
}
