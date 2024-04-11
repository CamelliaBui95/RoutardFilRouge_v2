package fr.routardfilrouge.routard.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean validate(User other) {
        if(other == null || !this.role.equalsIgnoreCase("admin"))
            return false;

        String rawPassword = other.password;

        return Argon2.validate(rawPassword, this.password);
    }
}
