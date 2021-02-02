package co.edu.udea.covapi.service;

import co.edu.udea.covapi.model.PermissionStatus;

import java.util.concurrent.ExecutionException;

public interface PermissionStatusService extends BaseModelService<PermissionStatus>{
    PermissionStatus getInitialStatus() throws ExecutionException, InterruptedException;
    PermissionStatus getFinalStatus() throws ExecutionException, InterruptedException;

}
