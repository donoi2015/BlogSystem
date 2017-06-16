package com.dono.crud.blog.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordMatchingValidator implements ConstraintValidator<PasswordsMatch, ReaderForm>{

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
    }

    @Override
    public boolean isValid(ReaderForm value, ConstraintValidatorContext context) {
        return (value.getPassword().equals(value.getConfirmPassword()));
    }

}
