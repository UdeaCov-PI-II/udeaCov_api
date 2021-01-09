package co.edu.udea.covapi.populator;

import co.edu.udea.covapi.dto.PermissionRequestDTO;
import co.edu.udea.covapi.dto.PermissionResponseDTO;
import co.edu.udea.covapi.dto.UserResponseDTO;
import co.edu.udea.covapi.exception.PopulatorException;
import co.edu.udea.covapi.model.Permission;
import co.edu.udea.covapi.model.PermissionStatus;
import co.edu.udea.covapi.model.User;
import co.edu.udea.covapi.service.PermissionStatusService;
import co.edu.udea.covapi.service.UserService;
import co.edu.udea.covapi.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.concurrent.ExecutionException;

@Component
public class PermissionPopulator implements Populator<Permission, PermissionResponseDTO, PermissionRequestDTO> {

    @Autowired
    private UserPopulator userPopulator;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionStatusService permissionStatusService;


    @Override
    public void populate(Permission source, PermissionResponseDTO target) throws ExecutionException, InterruptedException {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userPopulator.populate(source.getUser().get().get().toObject(User.class),userResponseDTO);
        target.setUser(userResponseDTO);
        target.setStartTimeStr(DateUtil.convert(source.getStartTime()));
        target.setEndTimeStr(DateUtil.convert(source.getEndTime()));
        target.setPlace(source.getPlace());
        target.setStatus(source.getStatus().get().get().toObject(PermissionStatus.class));
        target.setId(source.getModelId());
    }

    @Override
    public void inverselyPopulate(PermissionRequestDTO source, Permission target) throws PopulatorException, ExecutionException, InterruptedException {
        target.setUser(userService.getReference(source.getUserId()));
        try {
            target.setStartTime(DateUtil.convert(source.getStartTimeStr()));
        } catch (ParseException e) {
            throw new PopulatorException("The start date is not matched with the pattern " + DateUtil.DATE_PATTERN);
        }

        try {
            target.setEndTime(DateUtil.convert(source.getEndTimeStr()));
        } catch (ParseException e) {
            throw new PopulatorException("The end date is not matched with the pattern " + DateUtil.DATE_PATTERN);
        }

        target.setPlace(source.getPlace());
        target.setStatus(permissionStatusService.getReference(source.getStatusId()));

    }
}
