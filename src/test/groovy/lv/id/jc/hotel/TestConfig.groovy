package lv.id.jc.hotel

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@TestConfiguration
class TestConfig {
    @Bean
    PasswordEncoder getEncoder() {
        NoOpPasswordEncoder.getInstance()
    }

}
