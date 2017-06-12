package com.dono.crud.blog.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
@Documented
public @interface Unique {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
