package co.edu.udea.covapi.service;

import co.edu.udea.covapi.model.PermissionStatus;
import org.springframework.stereotype.Service;

@Service
public class DefaultPermissionStatusService extends DefaultBaseModelService<PermissionStatus> implements PermissionStatusService {
    @Override
    public String getCollectionName() {
        return "permissionStatus/";

    }
}
