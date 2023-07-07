package dev.djigit.phonecontacts.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneCollectionValidator.class)
@Documented
public @interface PhoneCollection {
    String message() default "Invalid Phone";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
