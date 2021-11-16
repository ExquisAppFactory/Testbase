package com.exquis.app.user.constant;

public interface Generic {
    static String ISSUER = "exquifactory";
    // reg expression
    String DIGITS_REG_EXT = "\\d+";
    String PHONE_REG_EX = "\\A[0-9]{11}\\z"; //"^\\+?\\d{1,3}?\\s?[0-9]{6,18}";
    String EMAIL_REG_EX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$";
    public static final String SIGNING_KEY = "abjfoijefli2o3ur2839ru02h03f0904j4of0490j094775i75i5ki5";
    public static final String AUTHORITIES_KEY = "scopes";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    static int PASSWORD_LENGTH = 7;
    static int PHONENUMBER_LENGTH = 11;
    static String ALL_ALPHANUMERIC = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static String ALL_NUMERIC = "1234567890";
    static String ALL_LOWER_ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
}
