package lv.id.jc.hotel.validator;

import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@NonNull
@Length(min = 8, max = 32)
@Pattern(regexp = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*\\d.*\\d)(?=.*[a-z].*[a-z].*[a-z]).+$")
public @interface StrongPassword {
    String message() default "{password.error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
