package com.crud.library.security;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/v1/users/sign_up";

    @Value("${expiration_time}")
    public static long EXPIRATION_TIME;

//    @Value("$(signup_url)")
//    public static String SIGN_UP_URL;
}
