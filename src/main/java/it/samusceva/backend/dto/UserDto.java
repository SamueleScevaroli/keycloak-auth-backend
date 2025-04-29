package it.samusceva.backend.dto;

import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record UserDto(UUID publicId,
                      String firstName,
                      String lastName,
                      String email,
                      String imageUrl,
                      Set<String> authorities) {
}
