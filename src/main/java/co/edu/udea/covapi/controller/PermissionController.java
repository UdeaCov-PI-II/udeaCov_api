package co.edu.udea.covapi.controller;

import co.edu.udea.covapi.dto.PermissionRequestDTO;
import co.edu.udea.covapi.dto.PermissionResponseDTO;
import co.edu.udea.covapi.exception.PopulatorException;
import co.edu.udea.covapi.model.Permission;
import co.edu.udea.covapi.populator.PermissionPopulator;
import co.edu.udea.covapi.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private PermissionPopulator permissionPopulator;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<PermissionResponseDTO>> getAllPermissions() throws ExecutionException, InterruptedException {
        List<Permission> permissionsModelList = permissionService.getAll(Permission.class);
        List<PermissionResponseDTO> permissions = permissionsModelList.stream().map(permission ->{
            PermissionResponseDTO permissionResponseDTO = new PermissionResponseDTO();
            try {
                permissionPopulator.populate(permission,permissionResponseDTO);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            return permissionResponseDTO;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(permissions,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PermissionResponseDTO> getPermission(@PathVariable("id") final String id) throws ExecutionException, InterruptedException {

        Permission permission = permissionService.get(id,Permission.class);
        PermissionResponseDTO permissionResponseDTO = new PermissionResponseDTO();
        permissionPopulator.populate(permission,permissionResponseDTO);
        return new ResponseEntity<>(permissionResponseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> savePermission(@RequestBody PermissionRequestDTO permissionRequestDTO) throws ExecutionException, InterruptedException {
        Permission permission = new Permission();
        try {
            permissionPopulator.inverselyPopulate(permissionRequestDTO,permission);
        } catch (PopulatorException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(permissionService.save(permission), HttpStatus.OK);
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

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletUser(@PathVariable("id") final String id) throws ExecutionException, InterruptedException {
        permissionService.delete(id);
    }

}
