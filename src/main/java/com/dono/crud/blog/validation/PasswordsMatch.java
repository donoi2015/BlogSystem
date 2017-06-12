package com.dono.crud.blog.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchingValidator.class)
@Documented
public @interface PasswordsMatch {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
