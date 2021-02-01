package co.edu.udea.covapi.facade;

import co.edu.udea.covapi.dto.request.EntranceRequestDTO;
import co.edu.udea.covapi.exception.PopulatorException;
import co.edu.udea.covapi.exception.ServiceException;
import co.edu.udea.covapi.model.Permission;

import java.util.concurrent.ExecutionException;

public interface EntranceFacade {

    void registerEntrance(final Permission permission, final EntranceRequestDTO entranceRequest) throws InterruptedException, ExecutionException, PopulatorException;
    void registerDeparture(final Permission permission, final EntranceRequestDTO entranceRequest) throws ExecutionException, InterruptedException, PopulatorException, ServiceException;

}
