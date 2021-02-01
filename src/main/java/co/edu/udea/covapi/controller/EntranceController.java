package co.edu.udea.covapi.controller;

import co.edu.udea.covapi.dto.request.EntranceRequestDTO;
import co.edu.udea.covapi.dto.response.EntranceResponseDTO;
import co.edu.udea.covapi.dto.response.MessageResponse;
import co.edu.udea.covapi.exception.PopulatorException;
import co.edu.udea.covapi.exception.ServiceException;
import co.edu.udea.covapi.facade.EntranceFacade;
import co.edu.udea.covapi.model.Entrance;
import co.edu.udea.covapi.model.Permission;
import co.edu.udea.covapi.populator.EntrancePopulator;
import co.edu.udea.covapi.service.EntranceService;
import co.edu.udea.covapi.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/entrances")
public class EntranceController {

    private static final Logger LOG = LoggerFactory.getLogger(EntranceController.class);

    @Autowired
    private EntranceService entranceService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private EntrancePopulator entrancePopulator;

    @Autowired
    private EntranceFacade entranceFacade;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<EntranceResponseDTO>> getEntrances() throws ExecutionException, InterruptedException {
        List<Entrance> entranceModelList  = entranceService.getAll(Entrance.class);
        List<EntranceResponseDTO> entrances = entranceModelList.stream().map(entrance ->{
            EntranceResponseDTO entranceResponse = new EntranceResponseDTO();
            try {
                entrancePopulator.populate(entrance,entranceResponse);
            } catch (Exception e) {
                LOG.error(e.getMessage(),e);
            }
            return entranceResponse;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(entrances, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> createEntrance(@RequestBody EntranceRequestDTO entranceRequest){
        try {
            Permission permission = permissionService.get(entranceRequest.getPermissionId(),Permission.class);
            if(permission == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("No se pudo crear la entrada, porque el permiso %s no existe",entranceRequest.getPermissionId()));
            }
            if(entranceRequest.isEntry()){
                entranceFacade.registerEntrance(permission, entranceRequest);
                return new ResponseEntity<>(new MessageResponse("La entrada se registro exitosamente"),HttpStatus.OK);
            }

            entranceFacade.registerDeparture(permission, entranceRequest);
            return new ResponseEntity<>(new MessageResponse("La salida se registro exitosamente"),HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            if (LOG.isErrorEnabled()){
                LOG.error(String.format("No se puede actualizar el registro para el permiso %s ", entranceRequest.getPermissionId()),e);
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se puede actualizar el registro para el permiso");
        } catch (PopulatorException | ServiceException e) {
            if(LOG.isErrorEnabled()){
                LOG.error(String.format("No se puede crear la entrada para el permiso %s ", entranceRequest.getPermissionId()),e);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
