package fr.routardfilrouge.routard.security;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class Argon2 {
    private static final int SALT_LENGTH = 16;
    private static final int KEY_LENGTH = 32;
    private static final int ITERATIONS = 10;
    private static final int MEMORY = 2 ^ 24;
    private static final int PARALLELISM = 1;
    private static final String SUFFIX = "$argon2id$v=19$m=26,t=10,p=1$";

    private Argon2() {

    }

    public static String getHashedPassword(String rawPassword) {
        return getEncode(rawPassword).substring(SUFFIX.length());
    }

    private static String getEncode(String password) {
        Argon2PasswordEncoder argon2 = new Argon2PasswordEncoder(SALT_LENGTH,KEY_LENGTH,PARALLELISM,MEMORY,ITERATIONS);

        return argon2.encode(password);
    }

    public static boolean validate(String rawPassword, String hashedPassword) {
        hashedPassword = SUFFIX + hashedPassword;
        return new Argon2PasswordEncoder(SALT_LENGTH,KEY_LENGTH,PARALLELISM,MEMORY,ITERATIONS).matches(rawPassword, hashedPassword);
    }
}
