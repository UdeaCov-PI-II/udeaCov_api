package co.edu.udea.covapi.populator;

import co.edu.udea.covapi.dto.request.PermissionRequestDTO;
import co.edu.udea.covapi.dto.response.MediaResponseDTO;
import co.edu.udea.covapi.dto.response.PermissionResponseDTO;
import co.edu.udea.covapi.dto.response.UserResponseDTO;
import co.edu.udea.covapi.exception.PopulatorException;
import co.edu.udea.covapi.model.Location;
import co.edu.udea.covapi.model.Permission;
import co.edu.udea.covapi.model.PermissionStatus;
import co.edu.udea.covapi.model.User;
import co.edu.udea.covapi.service.LocationService;
import co.edu.udea.covapi.service.PermissionStatusService;
import co.edu.udea.covapi.service.UnitService;
import co.edu.udea.covapi.service.UserService;
import co.edu.udea.covapi.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
public class PermissionPopulator implements Populator<Permission, PermissionResponseDTO, PermissionRequestDTO> {

    @Autowired
    private UserPopulator userPopulator;

    @Autowired
    private MediaPopulator mediaPopulator;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionStatusService permissionStatusService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private LocationService locationService;

    @Override
    public void populate(Permission source, PermissionResponseDTO target) throws ExecutionException, InterruptedException {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userPopulator.populate(source.getUser().get().get().toObject(User.class),userResponseDTO);
        target.setUser(userResponseDTO);
        target.setStartTimeStr(DateUtil.convert(source.getStartTime()));
        target.setEndTimeStr(DateUtil.convert(source.getEndTime()));
        target.setStatus(permissionStatusService.getModel(source.getStatus(),PermissionStatus.class).getDisplayName());
        target.setId(source.getModelId());
        if(source.getMedias() != null && !source.getMedias().isEmpty()){
            target.setMedias(source.getMedias().stream().map(media ->{
                MediaResponseDTO mediaResponseDTO = new MediaResponseDTO();
                mediaPopulator.populate(media,mediaResponseDTO);
                return mediaResponseDTO;
            }).collect(Collectors.toList()));
        }
        target.setLocation(locationService.getModel(source.getLocation(), Location.class).getName());
    }

    @Override
    public void inverselyPopulate(PermissionRequestDTO source, Permission target) throws PopulatorException, ExecutionException, InterruptedException {
        target.setUser(userService.getReference(source.getUserId()));
        try {
            target.setStartTime(DateUtil.convert(source.getStartTimeStr()));
        } catch (ParseException e) {
            throw new PopulatorException("La fecha inicial no coincide con el patrón " + DateUtil.DATE_PATTERN);
        }

        try {
            target.setEndTime(DateUtil.convert(source.getEndTimeStr()));
        } catch (ParseException e) {
            throw new PopulatorException("La fecha final no coincide con el patrón " + DateUtil.DATE_PATTERN);
        }

        target.setStatus(permissionStatusService.getReference(source.getStatusId()));
        target.setManagerUnit(unitService.getReference(source.getManagerUnitId()));
        target.setReason(source.getReason());
        target.setLocation(locationService.getReference(source.getLocationId()));
        target.setBuilding(source.getBuilding());
        target.setOfficeNumber(source.getOfficeNumber());
        if(StringUtils.isEmpty(source.getLocationOutOfUniversity()) && StringUtils.isEmpty(source.getLocationId())){
            throw new PopulatorException("Se debe definir el lugar para el cual se solicita el permiso");
        }
        target.setRequestedDays(source.getRequestedDays());
        target.setRequestedWorkingDay(source.getRequestedWorkingDay());
    }
}
