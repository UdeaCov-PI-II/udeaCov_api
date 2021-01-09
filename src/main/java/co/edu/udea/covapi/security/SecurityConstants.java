package co.edu.udea.covapi.security;

public class SecurityConstants {

    public static final String AUTH_TOKEN_PREFIX = "Bearer ";
    public static final String AUTH_HEADER = "Authorization";

    private SecurityConstants() {
        throw new IllegalStateException("Utility class");
    }
}