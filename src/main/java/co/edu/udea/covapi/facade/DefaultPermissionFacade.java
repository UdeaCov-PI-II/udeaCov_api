package co.edu.udea.covapi.facade;

import co.edu.udea.covapi.dto.request.ApprovalRequestDTO;
import co.edu.udea.covapi.dto.response.MessageResponse;
import co.edu.udea.covapi.dto.response.PermissionItemListResponseDTO;
import co.edu.udea.covapi.exception.CovApiRuleException;
import co.edu.udea.covapi.exception.ServiceException;
import co.edu.udea.covapi.model.*;
import co.edu.udea.covapi.populator.ApprovalPopulator;
import co.edu.udea.covapi.populator.PermissionItemListPopulator;
import co.edu.udea.covapi.service.PermissionService;
import co.edu.udea.covapi.service.PermissionStatusService;
import co.edu.udea.covapi.service.RoleService;
import co.edu.udea.covapi.service.UserService;
import co.edu.udea.covapi.strategy.NextReviewerStrategy;
import co.edu.udea.covapi.strategy.StatusTransitionStrategy;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Component
public class DefaultPermissionFacade implements PermissionFacade {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultPermissionFacade.class);

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionStatusService permissionStatusService;

    @Autowired
    private ApprovalPopulator approvalPopulator;

    @Autowired
    private NextReviewerStrategy nextReviewerStrategy;

    @Autowired
    private StatusTransitionStrategy statusTransitionStrategy;

    @Autowired
    private PermissionItemListPopulator permissionItemListPopulator;

    @Override
    public MessageResponse createApproval(String permissionId, ApprovalRequestDTO approvalRequest) throws ServiceException, CovApiRuleException {
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
            return new MessageResponse("La aprobación se registro exitosamente");
        } catch (ExecutionException | InterruptedException e) {
            LOG.error("Error creating the approvals",e);
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
            return new MessageResponse(permissionId);
        }catch (ExecutionException | InterruptedException e) {
            LOG.error("Error creating the permission",e);
            Thread.currentThread().interrupt();
            throw new ServiceException("No es posible crear un permiso en estos momentos. Inténtelo más tarde");
        }
    }

    @Override
    public List<PermissionItemListResponseDTO> getPermissions(String userId, String nextReviewer, String docType, String docNumber, boolean showOnlyApproved)
            throws ServiceException {
        List<Permission> permissions;
        Map<String,Object> queries = new HashMap<>();
        try {

            if(StringUtils.isNotEmpty(userId)){
                queries.put("user", userService.getReference(userId));
            }

            if(StringUtils.isNotEmpty(nextReviewer)){
                queries.put("nextReviewer", roleService.getReference(nextReviewer));
            }

            if(StringUtils.isNotEmpty(docType) && StringUtils.isNotEmpty(docNumber)){
                User user = userService.getByDocNumberAndDocType(docType,docNumber);
                if(user != null){
                    queries.put("user", userService.getReference(user.getModelId()));
                }
            }
            if(showOnlyApproved){
                PermissionStatus finalStatus = permissionStatusService.getFinalStatus();
                queries.put("status", permissionStatusService.getReference(finalStatus.getModelId()));
            }
            permissions = permissionService.getByAttributes(Permission.class,queries);
        }catch (ExecutionException | InterruptedException e){
            LOG.error("Error getting the permissions",e);
            Thread.currentThread().interrupt();
            throw new ServiceException("No es posible obtener los permisos estos momentos. Inténtelo más tarde");
        }
        return permissionItemListPopulator.getPermissionItemList(permissions);

    }


}
