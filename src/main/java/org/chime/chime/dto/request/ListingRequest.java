package org.chime.chime.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ListingRequest (

        @NotBlank(message = "Title is required")
        @Size(max = 100, message = "Title must be less than 100 characters")
        String title,

        @NotBlank(message = "Description is required")
        @Size(max = 500, message = "Description must be less than 500 characters")
        String description,

        @NotBlank(message = "Category is required")
        String category,

        @NotBlank(message = "Location is required")
        String location,

        @Pattern(regexp = "^(\\d+)(\\.\\d{1,2})?$", message = "Buyout price must be a valid number with up to two decimal places")
        String buyoutPrice,

        @Pattern(regexp = "^(\\d+)(\\.\\d{1,2})?$", message = "Starting bid price must be a valid number with up to two decimal places")
        String startingBidPrice,

        String imageUrl,

        @NotBlank(message = "Auction end time is required")
        LocalDateTime endTime,

        String userProfileId

){}
