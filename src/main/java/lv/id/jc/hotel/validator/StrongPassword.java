package lv.id.jc.hotel.validator;

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
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Pattern(regexp = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*\\d.*\\d)(?=.*[a-z].*[a-z].*[a-z]).{8}$")
public @interface StrongPassword {
    String message() default """
            The password should be at least 8 characters length and it should include:
            2 letters in Upper Case
            1 Special Character (!@#$&*)
            2 numerals (0-9)
            3 letters in Lower Case
            """;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
