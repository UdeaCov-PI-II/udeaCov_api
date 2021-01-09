package co.edu.udea.covapi.service;

import co.edu.udea.covapi.model.Permission;
import org.springframework.stereotype.Service;

@Service
public class DefaultPermissionService extends DefaultBaseModelService<Permission> implements PermissionService{
    @Override
    public String getCollectionName() {
        return "permissions/";

    }

}
