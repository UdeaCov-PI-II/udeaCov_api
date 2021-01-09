package co.edu.udea.covapi.security.services;

import co.edu.udea.covapi.model.User;
import co.edu.udea.covapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultCovApiUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.getByUsername(username);
            return CovApiUserDetails.build(user);
        }catch (Exception e){
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
    }
}
