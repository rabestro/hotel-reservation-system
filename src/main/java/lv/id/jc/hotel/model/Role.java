package lv.id.jc.hotel.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
    ADMIN, EMPLOYEE, CUSTOMER;

    public GrantedAuthority authority() {
        return new SimpleGrantedAuthority("ROLE_" + name());
    }
}
