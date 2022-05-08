package lv.id.jc.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@SuppressWarnings("ALL")
public class User extends AbstractAuditable<User, Long> implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    public enum Role {
        EMPLOYEE, CUSTOMER;

        GrantedAuthority authority() {
            return new SimpleGrantedAuthority("ROLE_" + name());
        }
    }

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private User.Role role;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(columnDefinition = "CHAR(60)")
    private String password;

    @Column(nullable = false, columnDefinition = "BOOLEAN default TRUE")
    private boolean enabled;

    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role.authority());
    }

    @Override
    public String getUsername() {
        return getEmail();
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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}