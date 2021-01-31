package co.edu.udea.covapi.populator;

import co.edu.udea.covapi.dto.response.PermissionItemListResponseDTO;
import co.edu.udea.covapi.dto.response.UserInfoResponseDTO;
import co.edu.udea.covapi.model.Location;
import co.edu.udea.covapi.model.Permission;
import co.edu.udea.covapi.model.User;
import co.edu.udea.covapi.service.LocationService;
import co.edu.udea.covapi.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
public class PermissionItemListPopulator implements Populator <Permission,PermissionItemListResponseDTO, PermissionItemListResponseDTO> {

    private static final Logger LOG = LoggerFactory.getLogger(PermissionItemListPopulator.class);

    @Autowired
    private UserInfoPopulator userInfoPopulator;

    @Autowired
    private LocationService locationService;

    @Override
    public void populate(Permission source, PermissionItemListResponseDTO target) throws ExecutionException, InterruptedException {
        target.setId(source.getModelId());
        target.setStartTimeStr(DateUtil.convert(source.getStartTime()));
        target.setEndTimeStr(DateUtil.convert(source.getEndTime()));
        target.setLocation(locationService.getModel(source.getLocation(), Location.class).getName());
        UserInfoResponseDTO userInfoResponse = new UserInfoResponseDTO();
        userInfoPopulator.populate(source.getUser().get().get().toObject(User.class),userInfoResponse);
        target.setUser(userInfoResponse);
    }

    @Override
    public void inverselyPopulate(PermissionItemListResponseDTO source, Permission target){
        throw new UnsupportedOperationException("The method is not implemented yet");
    }

    public List<PermissionItemListResponseDTO> getPermissionItemList(List<Permission> permissionsModelList) {
        return permissionsModelList.stream().map( p ->{
            PermissionItemListResponseDTO response = new PermissionItemListResponseDTO();
            try {
                this.populate(p,response);
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
            return response;
        }).collect(Collectors.toList());
    }
}
