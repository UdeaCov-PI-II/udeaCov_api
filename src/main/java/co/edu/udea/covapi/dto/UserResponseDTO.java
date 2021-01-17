package co.edu.udea.covapi.dto;

import co.edu.udea.covapi.model.Role;

import java.io.Serializable;

public class UserResponseDTO extends BaseResponseDTO implements Serializable {
    private String fullName;
    private String username;
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
