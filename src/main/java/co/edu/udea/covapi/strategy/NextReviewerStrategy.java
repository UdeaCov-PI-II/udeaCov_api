package co.edu.udea.covapi.strategy;

import co.edu.udea.covapi.exception.CovApiRuleException;
import co.edu.udea.covapi.model.Permission;
import co.edu.udea.covapi.model.PermissionStatus;
import co.edu.udea.covapi.model.User;

import java.util.concurrent.ExecutionException;

public interface NextReviewerStrategy {
    void assignNextReviewerIfItsPossible(Permission permission, PermissionStatus currentStatus, User user) throws CovApiRuleException;
    void assignFirstReviewer(Permission permission) throws  ExecutionException, InterruptedException;
    void assignFirstReviewerOfCurrentStatus(Permission permission) throws ExecutionException, InterruptedException;
}
