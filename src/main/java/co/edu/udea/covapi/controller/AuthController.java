package co.edu.udea.covapi.controller;

import co.edu.udea.covapi.dto.AuthRequestDTO;
import co.edu.udea.covapi.dto.AuthResponseDTO;
import co.edu.udea.covapi.dto.MessageResponse;
import co.edu.udea.covapi.dto.UserRequestDTO;
import co.edu.udea.covapi.exception.PopulatorException;
import co.edu.udea.covapi.model.User;
import co.edu.udea.covapi.populator.UserPopulator;
import co.edu.udea.covapi.security.jwt.JwtHelper;
import co.edu.udea.covapi.security.services.CovApiUserDetails;
import co.edu.udea.covapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserPopulator userPopulator;

    @Autowired
    private JwtHelper jwtHelper;


    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO request){
        Authentication auth = authenticationManager.
                authenticate( new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtHelper.createJwtToken(auth);
        CovApiUserDetails userDetails = (CovApiUserDetails) auth.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new AuthResponseDTO(token, userDetails.getUsername(), userDetails.getUserId(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDTO signUpRequest){
        try {
            if (userService.getByUsername(signUpRequest.getUsername()) != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario ya existe");
            }
            User user = new User();
            userPopulator.inverselyPopulate(signUpRequest,user);
            userService.save(user);
            return ResponseEntity.ok(new MessageResponse("Usuario se registró satisfactoriamente"));
        } catch (Exception e) {
            if(e instanceof PopulatorException){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede registrar el usuario en estos momentos", e);
            }

        }

    }
}
