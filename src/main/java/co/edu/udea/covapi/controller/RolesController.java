package co.edu.udea.covapi.controller;

import co.edu.udea.covapi.dto.response.PermissionItemListResponseDTO;
import co.edu.udea.covapi.model.Permission;
import co.edu.udea.covapi.model.Role;
import co.edu.udea.covapi.populator.PermissionItemListPopulator;
import co.edu.udea.covapi.service.PermissionService;
import co.edu.udea.covapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/roles")
public class RolesController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionItemListPopulator permissionItemListPopulator;

    @Autowired
    private RoleService roleService;


    @GetMapping(value = "/{id}/permissions",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<PermissionItemListResponseDTO>> getPermissionsByRole(@PathVariable("id") final String id) throws ExecutionException, InterruptedException {
        Role role  = roleService.get(id, Role.class);
        if(!"ROLE_USER".equalsIgnoreCase(role.getRoleId())){
            List<Permission> permissionsModelList ;
            permissionsModelList = permissionService.getByAttribute(Permission.class,"nextReviewer", roleService.getReference(id));
            List<PermissionItemListResponseDTO> userPermissions = permissionItemListPopulator.getPermissionItemList(permissionsModelList);
            return new ResponseEntity<>(userPermissions, HttpStatus.OK);
        }
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
    }
}
