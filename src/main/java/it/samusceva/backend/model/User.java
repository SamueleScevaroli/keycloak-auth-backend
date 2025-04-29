package it.samusceva.backend.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.Instant;
import java.util.*;

@Builder
@AllArgsConstructor
@Data
public class User {

    @NotNull
    private String lastName;

    @NotNull
    private String firstName;

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Setter
    private UUID publicId;

    @Size(max = 1000)
    private String imageUrl;

    private Instant lastModifiedDate;
    private Instant createdDate;

    @NotNull
    private Set<String> authorities;

    private Long dbId;
    private Instant lastSeen;

    public void updateFromUser(User user) {
        this.email = user.email;
        this.imageUrl = user.imageUrl;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
    }

    public static User fromTokenAttributes(Map<String, Object> attributes, List<String> rolesFromAccessToken) {
        UserBuilder userBuilder = User.builder();

        String sub = String.valueOf(attributes.get("sub"));

        String username = null;

        if (attributes.containsKey("preferred_username")) {
            username = attributes.get("preferred_username").toString().toLowerCase();
        }

        if (attributes.containsKey("given_name")) {
            userBuilder.firstName(attributes.get("given_name").toString());
        } else if (attributes.containsKey("nickname")) {
            userBuilder.firstName(attributes.get("nickname").toString());
        }

        if (attributes.containsKey("family_name")) {
            userBuilder.lastName(attributes.get("family_name").toString());
        }

        if (attributes.containsKey("email")) {
            userBuilder.email(attributes.get("email").toString());
        } else if (sub.contains("|") && (username != null && username.contains("@"))) {
            userBuilder.email(username);
        } else {
            userBuilder.email(sub);
        }

        if (attributes.containsKey("image_url")) {
            userBuilder.imageUrl(attributes.get("image_url").toString());
        }

        Set<String> authorities = new HashSet<>(rolesFromAccessToken);

        userBuilder.authorities(authorities);

        return userBuilder.build();
    }

    public void initFieldForSignup() {
        this.lastSeen = Instant.now();
    }
}

