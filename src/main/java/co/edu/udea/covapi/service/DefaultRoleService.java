package co.edu.udea.covapi.service;

import co.edu.udea.covapi.model.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DefaultRoleService extends DefaultBaseModelService<Role> implements RoleService{
    @Override
    public String getCollectionName() {
        return "roles/";
    }

    @Override
    public String getUserRoleId() throws ExecutionException, InterruptedException {
        List<Role> roles= this.getByAttribute(Role.class,"roleId", "ROLE_USER");
        return roles.isEmpty() ? StringUtils.EMPTY : roles.get(0).getModelId() ;
    }

    @Override
    public String getRoleId(final String role) throws ExecutionException, InterruptedException {
        List<Role> roles= this.getByAttribute(Role.class,"roleId", role);
        return roles.isEmpty() ? StringUtils.EMPTY : roles.get(0).getModelId() ;
    }
}
