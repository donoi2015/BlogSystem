package com.dono.crud.blog.validation;

import com.dono.crud.blog.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Unique, String> {

    private final ReaderService readerService;

    @Autowired
    public UsernameValidator(ReaderService readerService) {
        this.readerService = readerService;
    }

    @Override
    public void initialize(Unique constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (null==readerService.getOne(value));
    }
}
