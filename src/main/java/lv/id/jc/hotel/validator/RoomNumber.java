package lv.id.jc.hotel.validator;

import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
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
@Length(min = 1, max = 20)
public @interface RoomNumber {
    String message() default "{room.number.error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

