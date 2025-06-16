package org.chime.chime.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        String message,
        String error,
        int status,
        LocalDateTime timestamp,
        String path,
        List<String> details
) {
    public static ErrorResponse of(String message, String error, int status, String path) {
        return new ErrorResponse(message, error, status, LocalDateTime.now(), path, null);
    }

    public static ErrorResponse of(String message, String error, int status, String path, List<String> details) {
        return new ErrorResponse(message, error, status, LocalDateTime.now(), path, details);
    }
}