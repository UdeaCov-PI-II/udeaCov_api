package co.edu.udea.covapi.strategy;

import co.edu.udea.covapi.model.Permission;
import co.edu.udea.covapi.model.PermissionStatus;

import java.util.concurrent.ExecutionException;

public interface StatusTransitionStrategy {

    void changeStateIfItsPossible(final Permission permission, final PermissionStatus currentStatus) throws ExecutionException, InterruptedException;
}
