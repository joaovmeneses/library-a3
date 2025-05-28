package com.library_a3.library_a3.domains;

import com.library_a3.library_a3.shared.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name="credentials")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Credentials implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String email;
    @Column
    private String pass;

    private Role role;

    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    public Credentials(String email, String pass, Role role) {
        this.email = email;
        this.pass = pass;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.role = role;
    }
    public Credentials(String email, String pass) {
        this.email = email;
        this.pass = pass;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == Role.EMPLOYEE) return List.of(new SimpleGrantedAuthority("employee"));
        else return List.of(new SimpleGrantedAuthority("student"));
    }

    @Override
    public String getPassword() {
        return this.pass;
    }

    @Override
    public String getUsername() {
        return this.email;
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
}