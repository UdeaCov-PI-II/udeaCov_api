package co.edu.udea.covapi.strategy;

import co.edu.udea.covapi.exception.CovApiRuleException;
import co.edu.udea.covapi.model.Permission;
import co.edu.udea.covapi.model.PermissionStatus;
import co.edu.udea.covapi.model.User;
import co.edu.udea.covapi.service.PermissionNotificationService;
import co.edu.udea.covapi.service.PermissionStatusService;
import com.google.cloud.firestore.DocumentReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
public class DefaultNextReviewerStrategy implements NextReviewerStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultNextReviewerStrategy.class);

    @Autowired
    private PermissionStatusService permissionStatusService;

    @Autowired
    private PermissionNotificationService permissionNotificationService;


    @Override
    public void assignNextReviewerIfItsPossible(Permission permission, PermissionStatus currentStatus, User user) throws CovApiRuleException {
        List<DocumentReference> rolesReferences = currentStatus.getReviewersRoles();
        List<String> roles = rolesReferences.stream().map(DocumentReference::getId).collect(Collectors.toList());
        int pos = roles.indexOf(user.getRole().getId());
        if (pos < 0) {
            if (LOG.isErrorEnabled()) {
                LOG.error(String.format("El usuario %s no tiene el rol permitido para crear una aprobación", user.getUsername()));
            }
            throw new CovApiRuleException("El usuario no tiene el rol permitido para crear una aprobación");
        }
        if (pos < roles.size() - 1) {
            permission.setNextReviewer(rolesReferences.get(pos + 1));
        }
        if (pos == roles.size() - 1) {
            permission.setNextReviewer(null);
        }
    }

    @Override
    public void assignFirstReviewer(Permission permission) throws ExecutionException, InterruptedException {
        PermissionStatus initialStatus = permissionStatusService.getInitialStatus();
        if (initialStatus == null) {
            LOG.error("El permiso no puede iniciar el proceso de aprobación, porque no existe un estado definido como inicial");
            return;
        }
        permission.setStatus(permissionStatusService.getReference(initialStatus.getModelId()));
        List<DocumentReference> reviewers = initialStatus.getReviewersRoles();
        if (CollectionUtils.isEmpty(reviewers)) {
            LOG.error("El permiso no puede iniciar el proceso de aprobación, porque no existen roles asignados al estado inicial");
            return;
        }
        permission.setNextReviewer(reviewers.get(0));
        if (permission.getNextReviewer() != null) {
            permissionNotificationService.notifyNextReviewer(permission);
        }
    }

    @Override
    public void assignFirstReviewerOfCurrentStatus(Permission permission) throws ExecutionException, InterruptedException {
        if (permission.getStatus() == null) {
            LOG.error("El permiso no puede continuar el proceso de aprobación, porque no existe un estado definido");
        }
        PermissionStatus currentStatus = permissionStatusService.getModel(permission.getStatus(), PermissionStatus.class);
        if (currentStatus.isFinal()) {
            permission.setNextReviewer(null);
            return;
        }
        List<DocumentReference> reviewers = currentStatus.getReviewersRoles();
        if (CollectionUtils.isEmpty(reviewers)) {
            LOG.error("El permiso no puede continuar el proceso de aprobación, porque no existen roles asignados al estado");
            return;
        }
        permission.setNextReviewer(reviewers.get(0));
    }

}
