package com.skblab.project.account_service.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_account")
public class Account implements UserDetails, Serializable {
    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private UUID accountId;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String password;

    @Email
    private String email;

    @NotNull
    private String phone;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private LoyaltyCard loyaltyCard;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return phone;
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
        return enabled;
    }
}
