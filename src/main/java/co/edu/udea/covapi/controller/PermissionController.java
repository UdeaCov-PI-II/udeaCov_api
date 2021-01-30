package co.edu.udea.covapi.controller;

import co.edu.udea.covapi.dto.request.ApprovalRequestDTO;
import co.edu.udea.covapi.dto.request.PermissionMediasRequestDTO;
import co.edu.udea.covapi.dto.request.PermissionRequestDTO;
import co.edu.udea.covapi.dto.response.MessageResponse;
import co.edu.udea.covapi.dto.response.PermissionItemListResponseDTO;
import co.edu.udea.covapi.dto.response.PermissionResponseDTO;
import co.edu.udea.covapi.exception.CovApiRuleException;
import co.edu.udea.covapi.exception.PopulatorException;
import co.edu.udea.covapi.exception.ServiceException;
import co.edu.udea.covapi.facade.PermissionFacade;
import co.edu.udea.covapi.model.Permission;
import co.edu.udea.covapi.populator.PermissionItemListPopulator;
import co.edu.udea.covapi.populator.PermissionPopulator;
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
@RequestMapping(value = "/permissions")
public class PermissionController {

    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionFacade permissionFacade;

    @Autowired
    private PermissionPopulator permissionPopulator;

    @Autowired
    private PermissionItemListPopulator permissionItemListPopulator;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<PermissionItemListResponseDTO>> getAllPermissions() throws ExecutionException, InterruptedException {
        List<Permission> permissionsModelList = permissionService.getAll(Permission.class);
        List<PermissionItemListResponseDTO> permissions = permissionsModelList.stream().map(permission ->{
            PermissionItemListResponseDTO permissionResponse = new PermissionItemListResponseDTO();
            try {
                permissionItemListPopulator.populate(permission,permissionResponse);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            return permissionResponse;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(permissions,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PermissionResponseDTO> getPermission(@PathVariable("id") final String id) throws ExecutionException, InterruptedException {

        Permission permission = permissionService.get(id,Permission.class);
        if(permission == null){
            return new ResponseEntity<>(new PermissionResponseDTO(), HttpStatus.OK);
        }
        PermissionResponseDTO permissionResponseDTO = new PermissionResponseDTO();
        permissionPopulator.populate(permission,permissionResponseDTO);
        return new ResponseEntity<>(permissionResponseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> savePermission(@RequestBody PermissionRequestDTO permissionRequestDTO) throws ExecutionException, InterruptedException {
        Permission permission = new Permission();
        try {
            permissionPopulator.inverselyPopulate(permissionRequestDTO,permission);
            return new ResponseEntity<>(permissionFacade.createPermission(permission), HttpStatus.OK);
        } catch (Exception e) {
            if(e instanceof PopulatorException){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
            }else{
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se puede registrar el permiso en estos momentos", e);
            }
        }
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@PathVariable("id") final String id, @RequestBody PermissionRequestDTO permissionRequestDTO) throws ExecutionException, InterruptedException {
        Permission permission = new Permission();
        try {
            permissionPopulator.inverselyPopulate(permissionRequestDTO,permission);
        } catch (PopulatorException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        permissionService.update(id,permission);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/medias", consumes = MediaType.MULTIPART_FORM_DATA_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PermissionResponseDTO> updateMediasForPermission(@PathVariable("id") final String id,
                                                            @ModelAttribute ("permissionRequest") PermissionMediasRequestDTO permissionRequest)
            throws ExecutionException, InterruptedException {
        PermissionResponseDTO permissionResponseDTO = new PermissionResponseDTO();
        try {
            Permission permission = permissionService.updateMediasForPermission(id,permissionRequest);
            permissionPopulator.populate(permission,permissionResponseDTO);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return new ResponseEntity<>(permissionResponseDTO,HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/approval" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PermissionResponseDTO> createApprovalForPermission(@PathVariable("id") final String id,
                                                                             @RequestBody ApprovalRequestDTO approvalRequest){
        try {
            return new ResponseEntity<>(permissionFacade.createApproval(id, approvalRequest),HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (CovApiRuleException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletUser(@PathVariable("id") final String id) throws ExecutionException, InterruptedException {
        permissionService.delete(id);
    }

}
