package co.edu.udea.covapi.service;

import co.edu.udea.covapi.model.Permission;

import java.util.concurrent.ExecutionException;

public interface PermissionNotificationService {

    void notifyNextReviewer(Permission permission) throws ExecutionException, InterruptedException;

    void notifyApprovedPermission(Permission permission) throws ExecutionException, InterruptedException;

    void notifyRejectedPermission(Permission permission) throws ExecutionException, InterruptedException;
    
}
