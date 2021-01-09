package co.edu.udea.covapi.controller;

import co.edu.udea.covapi.dto.UserRequestDTO;
import co.edu.udea.covapi.dto.UserResponseDTO;
import co.edu.udea.covapi.model.User;
import co.edu.udea.covapi.populator.UserPopulator;
import co.edu.udea.covapi.service.UserService;
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
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserPopulator userPopulator;


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

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequestDTO userRequestDTO) throws ExecutionException, InterruptedException {
        User user = new User();
        userPopulator.inverselyPopulate(userRequestDTO,user);
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@PathVariable("id") final String id, @RequestBody UserRequestDTO userRequestDTO) throws ExecutionException, InterruptedException {
        User user = new User();
        userPopulator.inverselyPopulate(userRequestDTO,user);
        userService.update(id,user);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletUser(@PathVariable("id") final String id) throws ExecutionException, InterruptedException {
        userService.delete(id);
    }


}
