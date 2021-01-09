package co.edu.udea.covapi.model;

public class Role extends FirebaseModel{
    private String roleId;
    private String displayName;

    public Role() {
    }

    public Role(String roleId, String displayName) {
        this.roleId = roleId;
        this.displayName = displayName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
