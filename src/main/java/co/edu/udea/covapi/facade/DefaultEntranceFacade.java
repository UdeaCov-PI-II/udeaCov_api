package co.edu.udea.covapi.facade;

import co.edu.udea.covapi.dto.request.EntranceRequestDTO;
import co.edu.udea.covapi.exception.PopulatorException;
import co.edu.udea.covapi.exception.ServiceException;
import co.edu.udea.covapi.model.Entrance;
import co.edu.udea.covapi.model.Permission;
import co.edu.udea.covapi.populator.EntrancePopulator;
import co.edu.udea.covapi.service.EntranceService;
import co.edu.udea.covapi.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class DefaultEntranceFacade implements EntranceFacade {

    @Autowired
    private EntranceService entranceService;

    @Autowired
    private EntrancePopulator entrancePopulator;

    @Autowired
    private PermissionService permissionService;

    @Override
    public void registerEntrance(Permission permission, EntranceRequestDTO entranceRequest)
            throws InterruptedException, ExecutionException, PopulatorException {
        Entrance entrance = new Entrance();
        entrancePopulator.inverselyPopulate(entranceRequest, entrance);
        String entranceId = entranceService.save(entrance);
        if(StringUtils.isNotEmpty(entranceId)){
            permission.setEntrance(entranceService.getReference(entranceId));
            permissionService.update(entranceRequest.getPermissionId(), permission);
        }
    }

    @Override
    public void registerDeparture(Permission permission, EntranceRequestDTO entranceRequest)
            throws ExecutionException, InterruptedException, PopulatorException, ServiceException {

        if(permission.getEntrance() == null || entranceService.getModel(permission.getEntrance(), Entrance.class) == null){
            throw new ServiceException(
                    String.format("No se puede crear la salida, ya que no existe registro de entrada oara el permiso %S",
                            permission.getModelId()));
        }
        Entrance entrance = entranceService.getModel(permission.getEntrance(), Entrance.class);
        entrancePopulator.inverselyPopulate(entranceRequest, entrance);
        entranceService.update(entrance.getModelId(), entrance);
    }
}
