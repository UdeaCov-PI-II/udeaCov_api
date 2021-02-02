package co.edu.udea.covapi.service;

import co.edu.udea.covapi.model.PermissionStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DefaultPermissionStatusService extends DefaultBaseModelService<PermissionStatus> implements PermissionStatusService {
    @Override
    public String getCollectionName() {
        return "permissionStatus/";
    }

    @Override
    public PermissionStatus getInitialStatus() throws ExecutionException, InterruptedException {
        List<PermissionStatus> status = this.getByAttribute(PermissionStatus.class, "isInitial", true);
        return CollectionUtils.isEmpty(status) ? null : status.get(0);
    }

    @Override
    public PermissionStatus getFinalStatus() throws ExecutionException, InterruptedException {
        List<PermissionStatus> status = this.getByAttribute(PermissionStatus.class, "isFinal", true);
        return CollectionUtils.isEmpty(status) ? null : status.get(0);
    }

}
