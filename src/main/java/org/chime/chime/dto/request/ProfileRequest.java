package org.chime.chime.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProfileRequest(

        String firstName,
        String lastName,
        String profilePictureUrl,

        @Pattern(regexp = "^\\+370[6-9][0-9]{7}$", message = "Phone number must be in the format +370XXXXXXXXX")
        String phoneNumber,

        String country,
        String city

) {
}
