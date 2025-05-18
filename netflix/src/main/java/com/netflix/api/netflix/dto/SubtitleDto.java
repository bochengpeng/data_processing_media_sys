package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.ContentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubtitleDto
{

//    @Min(value = 1, message = "ID must be a positive integer")
    private int id;

    @NotNull(message = "Content ID is required")
    @Min(value = 1, message = "Content ID must be a positive number")
    private Integer contentId;

    @NotBlank(message = "Language is required")
    @Size(max = 50, message = "Language must be less than 50 characters")
    private String language;

    @NotBlank(message = "File path is required")
    @Size(max = 255, message = "File path must be less than 255 characters")
    private String filePath;

    @NotNull(message = "Content type is required")
    private ContentType type;
}