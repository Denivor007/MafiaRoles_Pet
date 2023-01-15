package com.denivor.mafia.entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "t_role")
@Setter
@Getter
public class Role  implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private Long id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public String getAuthority() {
        return getName();
    }
}
