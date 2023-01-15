package com.denivor.mafia.entity;

import com.denivor.mafia.converters.JSONHashMapConverter;
import com.denivor.mafia.converters.JSONListConverter;
import com.denivor.mafia.validators.StrongPassword;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_user")
@Setter
@Getter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Size(min=2, message = "Не меньше 2 знаков")
    private String username;
    @Size(min=8, message = "Не меньше 8 знаков")
    @StrongPassword
    private String password;
    @Transient
    private String passwordConfirm;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Convert(converter = JSONHashMapConverter.class)
    private HashMap<String, Object> gamePattern;
    @Convert(converter = JSONListConverter.class)
    private List<String> gamerList;

    @Transient
    private boolean enabled;

    public User(){}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
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
        return true;//this.enabled;
    }
}
