package lv.id.jc.hotel.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Target({PARAMETER, METHOD, FIELD})
@NotNull
@Size(min = 1, max = 20)
public @interface RoomNumber {
    String message() default "Room number can contain from 1 to 20 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
