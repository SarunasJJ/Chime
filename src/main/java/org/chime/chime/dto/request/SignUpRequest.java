package org.chime.chime.dto.request;

import jakarta.validation.constraints.*;

public record SignUpRequest (
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    String username,

    @NotBlank(message = "Email is required")
    @Size(max = 50, message = "Email must not exceed 50 characters")
    @Email(message = "Email should be valid")
    String email,

    @NotBlank(message = "Password is required")
    @Size(min = 10, message = "Password must be atleast 10 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, and one number")
    String password,

    @NotBlank(message = "Confirm password is required")
    @Size(min = 10, message = "Confirm password must be atleast 10 characters long")
    String confirmPassword
) {
    public boolean isPasswordMatching() {
        return password != null && password.equals(confirmPassword);
    }
}