package co.edu.udea.covapi.populator;

import co.edu.udea.covapi.dto.request.PermissionRequestDTO;
import co.edu.udea.covapi.dto.response.*;
import co.edu.udea.covapi.exception.PopulatorException;
import co.edu.udea.covapi.model.*;
import co.edu.udea.covapi.service.*;
import co.edu.udea.covapi.util.DateUtil;
import com.google.cloud.firestore.DocumentReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
public class PermissionPopulator implements Populator<Permission, PermissionResponseDTO, PermissionRequestDTO> {

    @Autowired
    private UserPopulator userPopulator;

    @Autowired
    private MediaPopulator mediaPopulator;

    @Autowired
    private ApprovalPopulator approvalPopulator;

    @Autowired
    private StatusPopulator statusPopulator;

    @Autowired
    private EntrancePopulator entrancePopulator;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionStatusService permissionStatusService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private EntranceService entranceService;

    @Override
    public void populate(Permission source, PermissionResponseDTO target) throws ExecutionException, InterruptedException {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        User user = Optional.ofNullable(source.getUser()).map(DocumentReference::get).
                map(reference -> {
                    try {
                        return reference.get();
                    } catch (InterruptedException | ExecutionException e) {
                        Thread.currentThread().interrupt();
                    }
                    return null;
                }).map(snap -> snap.toObject(User.class)).orElse(null);
        if(user != null) {
            userPopulator.populate(user,userResponseDTO);
            target.setUser(userResponseDTO);
        }else{
            target.setUser(null);
        }
        target.setStartTimeStr(DateUtil.convert(source.getStartTime()));
        target.setEndTimeStr(DateUtil.convert(source.getEndTime()));
        StatusResponseDTO statusResponse = new StatusResponseDTO();
        statusPopulator.populate(permissionStatusService.getModel(source.getStatus(),PermissionStatus.class),statusResponse);
        target.setStatus(statusResponse);
        target.setId(source.getModelId());
        populateMedias(source, target);
        populateApprovals(source,target);
        populateEntrance(source,target);
        target.setReason(source.getReason());
        target.setLocation(locationService.getModel(source.getLocation(), Location.class).getName());
    }

    private void populateMedias(Permission source, PermissionResponseDTO target){
        if(!CollectionUtils.isEmpty(source.getMedias())){
            target.setMedias(source.getMedias().stream().map(media ->{
                MediaResponseDTO mediaResponseDTO = new MediaResponseDTO();
                mediaPopulator.populate(media,mediaResponseDTO);
                return mediaResponseDTO;
            }).collect(Collectors.toList()));
        }
    }

    private void populateApprovals(Permission source, PermissionResponseDTO target){
        if(!CollectionUtils.isEmpty(source.getApprovals())){
            target.setApprovals(source.getApprovals().stream().map(approval ->{
                ApprovalResponseDTO approvalResponse = new ApprovalResponseDTO();
                try {
                    approvalPopulator.populate(approval, approvalResponse);
                } catch (ExecutionException | InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return approvalResponse;
            }).collect(Collectors.toList()));
        }
    }

    private void populateEntrance(Permission source, PermissionResponseDTO target) throws ExecutionException, InterruptedException {
        if(source.getEntrance() != null){
            EntranceResponseDTO entranceResponse = new EntranceResponseDTO();
            Entrance entrance = entranceService.getModel(source.getEntrance(), Entrance.class);
            entrancePopulator.populate(entrance, entranceResponse);
            target.setEntrance(entranceResponse);
        }
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
