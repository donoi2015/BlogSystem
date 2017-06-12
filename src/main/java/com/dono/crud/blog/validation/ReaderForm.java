package com.dono.crud.blog.validation;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;


@Getter
@Setter
@PasswordsMatch(message = "{PasswordsMatch.readerForm}")
public class ReaderForm {

    @NotBlank
    @Unique(message = "{Unique.readerForm.username}")
    private String username;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
    private String password;
    @NotBlank
    private String confirmPassword;
    private boolean valid;

}
