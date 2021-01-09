package co.edu.udea.covapi.service;

import co.edu.udea.covapi.model.Role;
import org.springframework.stereotype.Service;

@Service
public class DefaultRoleService extends DefaultBaseModelService<Role> implements RoleService{
    @Override
    public String getCollectionName() {
        return "roles/";
    }
}
