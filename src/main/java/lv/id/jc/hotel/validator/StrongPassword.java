package lv.id.jc.hotel.validator;

import lombok.NonNull;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@NonNull
@Size(min = 8)
@Pattern(regexp = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*\\d.*\\d)(?=.*[a-z].*[a-z].*[a-z]).+$")
public @interface StrongPassword {
    String message() default "{password.error}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
