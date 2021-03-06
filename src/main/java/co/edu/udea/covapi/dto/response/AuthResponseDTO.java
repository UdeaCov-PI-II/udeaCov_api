package co.edu.udea.covapi.dto.response;

import java.io.Serializable;
import java.util.List;

public class AuthResponseDTO implements Serializable {

    private String token;
    private String type ;
    private String userId;
    private String username;
    private List<String> roles;

    public AuthResponseDTO(String token, String username, String userId, List<String> roles) {
        this.token = token;
        this.type = "Bearer";
        this.username = username;
        this.userId = userId;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
