package lv.id.jc.hotel.config;

import lv.id.jc.hotel.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
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
                .mvcMatchers("/api/**", "/schedule", "/statistics")
                .hasRole(User.Role.EMPLOYEE.name())

                .mvcMatchers(HttpMethod.GET, "/check/type")
                .hasAnyRole(User.Role.EMPLOYEE.name(), User.Role.CUSTOMER.name())

                .mvcMatchers("/employee")
                .hasAnyRole(User.Role.EMPLOYEE.name())

                .mvcMatchers(HttpMethod.POST, "/register")
                .permitAll()

                .mvcMatchers(HttpMethod.POST, "/book")
                .hasAnyRole(User.Role.EMPLOYEE.name(), User.Role.CUSTOMER.name())

                .mvcMatchers(HttpMethod.GET, "/check")
                .hasRole(User.Role.EMPLOYEE.name())

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
