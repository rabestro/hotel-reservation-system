package lv.id.jc.hotel.validator;

import lv.id.jc.hotel.validator.impl.RoomTypeValidator;

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
@Constraint(validatedBy = {RoomTypeValidator.class})
@Target({PARAMETER, METHOD, FIELD})
@NotNull
public @interface RoomTypeId {
    String message() default "room type id must be present in the database";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
