package global.rest.project.util.constants;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.regex.Pattern;

public class UserConstants {

    public static String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9+_.-]+$";
    public static Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
    public static String PASS_REGEX = "^((?=.*[A-Z])(?=.*[a-z])(?=.*\\d{2})).*$";
    public static Pattern passPattern = Pattern.compile(PASS_REGEX);
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static String MESSAGE_KEY = "mensaje";
    public static String RESPONSE_ID = "id";
    public static String RESPONSE_CREATED = "created";
    public static String RESPONSE_MODIFIED = "modified";
    public static String RESPONSE_LAST_LOGIN = "last_login";
    public static String RESPONSE_TOKEN = "token";
    public static String RESPONSE_IS_ACTIVE = "isActive";
}
