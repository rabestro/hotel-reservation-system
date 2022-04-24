package lv.id.jc.hotel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class WandooHotelReservationSystemApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(new BCryptPasswordEncoder().encode("password"));
    }

}
