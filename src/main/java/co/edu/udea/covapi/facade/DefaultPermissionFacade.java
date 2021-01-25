package co.edu.udea.covapi.facade;

import co.edu.udea.covapi.dto.request.ApprovalRequestDTO;
import co.edu.udea.covapi.dto.response.MessageResponse;
import co.edu.udea.covapi.dto.response.PermissionResponseDTO;
import co.edu.udea.covapi.exception.CovApiRuleException;
import co.edu.udea.covapi.exception.ServiceException;
import co.edu.udea.covapi.model.*;
import co.edu.udea.covapi.populator.ApprovalPopulator;
import co.edu.udea.covapi.populator.PermissionPopulator;
import co.edu.udea.covapi.service.PermissionService;
import co.edu.udea.covapi.service.PermissionStatusService;
import co.edu.udea.covapi.service.UserService;
import co.edu.udea.covapi.strategy.NextReviewerStrategy;
import co.edu.udea.covapi.strategy.StatusTransitionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class DefaultPermissionFacade implements PermissionFacade {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultPermissionFacade.class);

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionStatusService permissionStatusService;

    @Autowired
    private ApprovalPopulator approvalPopulator;

    @Autowired
    private NextReviewerStrategy nextReviewerStrategy;

    @Autowired
    private PermissionPopulator permissionPopulator;

    @Autowired
    private StatusTransitionStrategy statusTransitionStrategy;

    @Override
    public PermissionResponseDTO createApproval(String permissionId, ApprovalRequestDTO approvalRequest) throws ServiceException, CovApiRuleException {
        try {
            Permission permission = permissionService.get(permissionId,Permission.class);
            User user = userService.get(approvalRequest.getUserId(),User.class);
            PermissionStatus currentStatus = permissionStatusService.getModel(permission.getStatus(),PermissionStatus.class);
            if(!approvalRequest.getAction().equalsIgnoreCase(currentStatus.getAction())){
                if(LOG.isErrorEnabled()){
                    LOG.error(String.format("La acción %s no coincide con el estado %s",approvalRequest.getAction(),currentStatus.getDisplayName()));
                }
                throw new CovApiRuleException("El usuario no puede aprobar el permiso");
            }
            if(!user.getRole().getId().equalsIgnoreCase(permission.getNextReviewer().getId())){
                if(LOG.isErrorEnabled()){
                    LOG.error(String.format("El usuario %s no es el próximo a aprobar el permiso %s",user.getUsername(),permission.getModelId()));
                }
                throw new CovApiRuleException("El usuario no puede aprobar el permiso");
            }
            nextReviewerStrategy.assignNextReviewerIfItsPossible(permission, currentStatus, user);
            Approval approval = new Approval();
            approvalPopulator.inverselyPopulate(approvalRequest,approval);
            approval.setCreationTime(new Date());
            if(permission.getApprovals() != null){
                permission.getApprovals().add(approval);
            }else{
                List<Approval> approvals = new ArrayList<>();
                approvals.add(approval);
                permission.setApprovals(approvals);
            }
            //@TODO It's missing to handle the unapproved permission
            statusTransitionStrategy.changeStateIfItsPossible(permission,currentStatus);
            permissionService.update(permissionId,permission);
            PermissionResponseDTO permissionResponseDTO = new PermissionResponseDTO();
            permissionPopulator.populate(permission,permissionResponseDTO);
            return permissionResponseDTO;
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServiceException("No es posible crear una aprobación en estos momentos. Inténtelo más tarde");
        }
    }

    @Override
    public MessageResponse createPermission(Permission permission) throws ServiceException {
        try {
            String permissionId = permissionService.save(permission);
            permission.setModelId(permissionId);
            nextReviewerStrategy.assignFirstReviewer(permission);
            permissionService.update(permissionId,permission);
            return new MessageResponse("El permiso se creó exitosamente con el id " + permissionId);
        }catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServiceException("No es posible crear un permiso en estos momentos. Inténtelo más tarde");
        }
    }

}
