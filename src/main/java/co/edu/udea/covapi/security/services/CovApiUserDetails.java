package co.edu.udea.covapi.security.services;

import co.edu.udea.covapi.model.Role;
import co.edu.udea.covapi.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CovApiUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String username;

    private String userId;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private CovApiUserDetails(String username, String password, String userId, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.authorities = authorities;
    }

    public static CovApiUserDetails build(User user) {
        List<GrantedAuthority> authorities;
        try {
            Role role = user.getRole().get().get().toObject(Role.class);
            if(role != null){
                authorities = List.of(new SimpleGrantedAuthority(role.getRoleId()));
            }else{
                authorities = Collections.emptyList();
            }
        }catch (InterruptedException | ExecutionException e) {
            authorities = Collections.emptyList();
            Thread.currentThread().interrupt();
        }
        return new CovApiUserDetails(user.getUsername(), user.getPassword(), user.getModelId(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }
}
