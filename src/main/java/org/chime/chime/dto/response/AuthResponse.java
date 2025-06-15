package org.chime.chime.dto.response;

public record AuthResponse(
        boolean success,
        String message,
        UserResponse userResponse,
        String token
) {
    public static AuthResponse success(String message, UserResponse userResponse, String token) {
        return new AuthResponse(true, message, userResponse, token);
    }

    public static AuthResponse success(String message, UserResponse userResponse) {
        return new AuthResponse(true, message, userResponse, null);
    }

    public static AuthResponse failure(String message) {
        return new AuthResponse(false, message, null, null);
    }
}
