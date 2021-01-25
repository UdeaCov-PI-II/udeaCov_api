package co.edu.udea.covapi.service;

import co.edu.udea.covapi.enums.MediaTypeEnum;
import co.edu.udea.covapi.dto.request.PermissionMediasRequestDTO;
import co.edu.udea.covapi.exception.ServiceException;
import co.edu.udea.covapi.model.Media;
import co.edu.udea.covapi.model.Permission;
import co.edu.udea.covapi.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultPermissionService extends DefaultBaseModelService<Permission> implements PermissionService{


    @Autowired
    private StorageService storageService;

    @Override
    public String getCollectionName() {
        return "permissions/";

    }

    @Override
    public Permission updateMediasForPermission(final String permissionId, final PermissionMediasRequestDTO permissionMediasRequest) throws ServiceException {
        try {
            Media coronaAppMedia = storageService.uploadFile(permissionMediasRequest.getCoronAppEvidence());
            coronaAppMedia.setType(MediaTypeEnum.CORONA_APP_MEDIA.toString());
            Media medellinMeCuidaMedia = storageService.uploadFile(permissionMediasRequest.getMedellinMeCuidaEvidence());
            medellinMeCuidaMedia.setType(MediaTypeEnum.MEDELLIN_ME_CUIDA_MEDIA.toString());
            Permission permission = this.get(permissionId,Permission.class);
            List<Media> medias = List.of(medellinMeCuidaMedia,coronaAppMedia);
            permission.setMedias(medias);
            this.update(permissionId, permission);
            return permission;
        }catch (Exception e){
            throw new ServiceException("Los archivos nos pudieron ser adjuntados al permiso");
        }
    }
}
