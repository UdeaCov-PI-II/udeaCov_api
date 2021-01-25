package co.edu.udea.covapi.facade;

import co.edu.udea.covapi.dto.request.ApprovalRequestDTO;
import co.edu.udea.covapi.dto.response.MessageResponse;
import co.edu.udea.covapi.dto.response.PermissionResponseDTO;
import co.edu.udea.covapi.exception.CovApiRuleException;
import co.edu.udea.covapi.exception.ServiceException;
import co.edu.udea.covapi.model.Permission;

public interface PermissionFacade {

    PermissionResponseDTO createApproval(final String permissionId, final ApprovalRequestDTO approvalRequest) throws ServiceException, CovApiRuleException;
    MessageResponse createPermission(final Permission permission) throws ServiceException;

}
