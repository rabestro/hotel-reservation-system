package lv.id.jc.hotel.config;

import lv.id.jc.hotel.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

/**
 * Configuration class for auditing and security.
 */
@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
public class AuditSecurityConfig {

    /**
     * Returns an instance of AuditorAware<User>.
     *
     * @return an instance of AuditorAware<User> that can provide the currently logged-in user as the auditor.
     */
    @Bean
    AuditorAware<User> auditorAware() {
       return this::getCurrentUser;
    }

   /**
    * Lookup User instance corresponding to logged-in user
    *
    * @return Currently logged-in user or {@literal Optional#empty()} if none found.
    */
   private Optional<User> getCurrentUser() {
      return Optional.ofNullable(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication)
            .filter(Authentication::isAuthenticated)
            .map(Authentication::getPrincipal)
            .filter(User.class::isInstance)
            .map(User.class::cast);
   }
}