package co.edu.udea.covapi.controller;

import co.edu.udea.covapi.dto.request.DeviceTokenRequestDTO;
import co.edu.udea.covapi.dto.request.UserRequestDTO;
import co.edu.udea.covapi.dto.response.MessageResponse;
import co.edu.udea.covapi.dto.response.PermissionItemListResponseDTO;
import co.edu.udea.covapi.dto.response.UserResponseDTO;
import co.edu.udea.covapi.exception.PopulatorException;
import co.edu.udea.covapi.model.Permission;
import co.edu.udea.covapi.model.User;
import co.edu.udea.covapi.populator.PermissionItemListPopulator;
import co.edu.udea.covapi.populator.UserPopulator;
import co.edu.udea.covapi.service.PermissionService;
import co.edu.udea.covapi.service.RoleService;
import co.edu.udea.covapi.service.UserService;
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
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserPopulator userPopulator;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionItemListPopulator permissionItemListPopulator;

    @Autowired
    private RoleService roleService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() throws ExecutionException, InterruptedException {
        List<User> usersModelList = userService.getAll(User.class);
        List<UserResponseDTO> users = usersModelList.stream().map(user ->{
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            try {
                userPopulator.populate(user,userResponseDTO);
            } catch (Exception e) {
               logger.error(e.getMessage());
            }
            return userResponseDTO;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(users,HttpStatus.OK);
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") final String id) throws ExecutionException, InterruptedException {

        User user = userService.get(id,User.class);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userPopulator.populate(user,userResponseDTO);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@PathVariable("id") final String id, @RequestBody UserRequestDTO userRequestDTO) throws ExecutionException, InterruptedException {
        User user = new User();
        try {
            userPopulator.inverselyPopulate(userRequestDTO,user);
        } catch (PopulatorException e) {
            logger.error(e.getMessage());
        }
        userService.update(id,user);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletUser(@PathVariable("id") final String id) throws ExecutionException, InterruptedException {
        userService.delete(id);
    }


    @PutMapping(value = "/{id}/deviceToken", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updateDeviceToken (@PathVariable("id") final String id,
                                                              @RequestBody DeviceTokenRequestDTO deviceTokenRequest) throws ExecutionException, InterruptedException {
        User user = userService.get(id, User.class);
        if(user != null){
            user.setDeviceToken(deviceTokenRequest.getDeviceToken());
            userService.update(id,user);
            return new ResponseEntity<>(new MessageResponse("Se actualizó el token exitosamente"),HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("El usuario con id %s no existe",id));

    }


    @GetMapping(value = "/{id}/permissions",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<PermissionItemListResponseDTO>> getPermissionsByUser(@PathVariable("id") final String id) throws ExecutionException, InterruptedException {

        List<Permission> permissionsModelList = permissionService.getByAttribute(Permission.class,"user", userService.getReference(id));
        List<PermissionItemListResponseDTO> userPermissions = permissionItemListPopulator.getPermissionItemList(permissionsModelList);
        return new ResponseEntity<>(userPermissions,HttpStatus.OK);
    }




}
