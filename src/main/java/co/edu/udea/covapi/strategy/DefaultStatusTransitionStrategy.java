package co.edu.udea.covapi.strategy;

import co.edu.udea.covapi.model.*;
import co.edu.udea.covapi.service.PermissionNotificationService;
import co.edu.udea.covapi.service.PermissionStatusService;
import co.edu.udea.covapi.service.UserService;
import com.google.cloud.firestore.DocumentReference;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
public class DefaultStatusTransitionStrategy implements StatusTransitionStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultStatusTransitionStrategy.class);

    @Autowired
    private UserService userService;

    @Autowired
    private NextReviewerStrategy nextReviewerStrategy;

    @Autowired
    private PermissionNotificationService permissionNotificationService;

    @Autowired
    private PermissionStatusService permissionStatusService;


    @Override
    public void changeStateIfItsPossible(final Permission permission, final PermissionStatus currentStatus) throws ExecutionException, InterruptedException {
        List<DocumentReference> rolesReferences = currentStatus.getReviewersRoles();
        List<String> roles = rolesReferences.stream().map(DocumentReference::getId).collect(Collectors.toList());
        List<Approval> approvals = permission.getApprovals();
        List<String> rolesWhoseMadeApprovals = approvals.stream().map(approval -> {
            try {
                User user = userService.getModel(approval.getReviewer(), User.class);
                return user.getRole().getId();
            } catch (ExecutionException | InterruptedException e) {
                Thread.currentThread().interrupt();
                LOG.error("Error obteniendo las aprobaciones del permiso ",e);
            }
            return StringUtils.EMPTY;
        }).collect(Collectors.toList());
        if(rolesWhoseMadeApprovals.containsAll(roles)){
            permission.setStatus(currentStatus.getNextStatus());
            PermissionStatus newStatus = permissionStatusService.getModel(permission.getStatus(),PermissionStatus.class);
            if(newStatus.isFinal()){
                permissionNotificationService.notifyApprovedPermission(permission);
            }
            nextReviewerStrategy.assignFirstReviewerOfCurrentStatus(permission);
            if(permission.getNextReviewer() != null){
                permissionNotificationService.notifyNextReviewer(permission);
            }
        }
    }

}
