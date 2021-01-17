package co.edu.udea.covapi.dto;


import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class UserRequestDTO implements Serializable {

    @NotEmpty(message = "El usuario no puede ser vacio")
    private String username;
    @NotEmpty(message = "La contraseña puede ser vacio")
    private String password;
    @NotEmpty(message = "El correo no puede ser vacio")
    @Email(message = "El correo es inválido")
    private String email;
    @NotEmpty(message = "El tipo de documento no puede ser vacio")
    private String documentType;
    @NotEmpty(message = "El número de documento no puede ser vacio")
    private String documentNumber;
    @NotEmpty(message = "El nombre no puede ser vacio")
    private String fullName;
    @NotEmpty(message = "La fecha de nacimiento no puede ser vacio")
    private String birthday;
    private String personalEmail;
    private String arlName;
    @NotEmpty(message = "El municipio de residencia no puede ser vacio")
    private String town;
    private @Valid UserUniversityInfoDTO universityInfo;
    private @Valid UserHealthInfoDTO healthInfo;
    private String roleId;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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

    public UserUniversityInfoDTO getUniversityInfo() {
        return universityInfo;
    }

    public void setUniversityInfo(UserUniversityInfoDTO universityInfo) {
        this.universityInfo = universityInfo;
    }

    public UserHealthInfoDTO getHealthInfo() {
        return healthInfo;
    }

    public void setHealthInfo(UserHealthInfoDTO healthInfo) {
        this.healthInfo = healthInfo;
    }
}
