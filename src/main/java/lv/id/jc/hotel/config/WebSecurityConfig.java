package lv.id.jc.hotel.config;

import lv.id.jc.hotel.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/employee")
                .hasAnyRole(Role.ADMIN.name(), Role.EMPLOYEE.name())

                .mvcMatchers("/api/**", "/room", "/schedule", "/statistics")
                .hasRole(Role.EMPLOYEE.name())

                .mvcMatchers(HttpMethod.GET, "/check/type")
                .hasAnyRole(Role.EMPLOYEE.name(), Role.CUSTOMER.name())

                .mvcMatchers(HttpMethod.POST, "/register", "/api")
                .permitAll()

                .mvcMatchers(HttpMethod.POST, "/reservation")
                .hasAnyRole(Role.EMPLOYEE.name(), Role.CUSTOMER.name())

                .mvcMatchers(HttpMethod.GET, "/reservation/check")
                .hasRole(Role.EMPLOYEE.name())

                .mvcMatchers(HttpMethod.GET, "/check")
                .hasRole(Role.EMPLOYEE.name())

                .mvcMatchers("/", "/public", "/hello").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();
    }
}
