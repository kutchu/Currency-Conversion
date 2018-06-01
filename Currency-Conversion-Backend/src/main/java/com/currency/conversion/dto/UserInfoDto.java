package com.currency.conversion.dto;

import com.currency.conversion.validator.FieldMatch;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Created by ksrivas on 6/1/2018.
 */
@Data
@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatch(first = "emailId", second = "confirmEmailId", message = "The email fields must match")
})
public class UserInfoDto {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @Email
    @NotEmpty
    private String emailId;

    @Email
    @NotEmpty
    private String confirmEmailId;

    @AssertTrue
    private Boolean terms;
}
