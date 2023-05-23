package lv.id.jc.hotel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
        title = "Hotel API",
        description = "API definitions of hotel reservation microservice",
        version = "1.0.0"
))
@SpringBootApplication
public class HotelReservationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelReservationSystemApplication.class, args);
    }
}
