package co.edu.udea.covapi.service;

import co.edu.udea.covapi.dto.request.PermissionMediasRequestDTO;
import co.edu.udea.covapi.exception.ServiceException;
import co.edu.udea.covapi.model.Permission;

public interface PermissionService extends BaseModelService<Permission>{
    void updateMediasForPermission(final String permissionId, final PermissionMediasRequestDTO permissionMediasRequest) throws ServiceException;
}
