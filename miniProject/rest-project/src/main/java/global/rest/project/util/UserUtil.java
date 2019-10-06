package global.rest.project.util;

import global.rest.project.model.user.Phone;
import global.rest.project.model.user.UserDetails;
import global.rest.project.util.constants.UserConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import com.google.json.JsonSanitizer;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;

public class UserUtil extends UserConstants {

    public static boolean isValidToPersist(String inputEmail, String inputPass, Boolean found) {
        return !StringUtils.isEmpty(inputEmail)
                && isValidPass(inputPass)
                && !found
                && isValidEmail(inputEmail);
    }
    public static boolean isValidEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidPass(String pass) {
        Matcher matcher = passPattern.matcher(pass);
        return matcher.matches();
    }
    public static List<Phone> getListOfPhonesFromString(String phones) throws IOException {
        List<Phone> listOfPhones = new ArrayList<>();
        String jsonPhoneSplit = phones.replace("[","").replace("]","");
        List<String> listOfStrings = new ArrayList<String>(Arrays.asList(jsonPhoneSplit.split("},")));
        for(String phone : listOfStrings) {
            listOfPhones.add(objectMapper.readValue(JsonSanitizer.sanitize(phone), Phone.class));
        }
        return listOfPhones;
    }

    public static ResponseEntity<Object> userNotFound(String inputEmail) {
        Map<String, String> notFound = new LinkedHashMap<>();
        notFound.put(MESSAGE_KEY, "Usuario no encontrado para el email "+ inputEmail);
        return new ResponseEntity<>(notFound, HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<Object> errorResponses(String inputEmail, String pass, Boolean found) {
        Map<String, String> errorResponse = new LinkedHashMap<>();
        if(StringUtils.isEmpty(inputEmail)){
            errorResponse.put(MESSAGE_KEY, "El campo 'email' no puede ser omitido");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } else if(!UserUtil.isValidEmail(inputEmail)) {
            errorResponse.put(MESSAGE_KEY, "Formato de 'email' inválido: " + inputEmail);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } else if(found) {
            errorResponse.put(MESSAGE_KEY, "Correo ya registrado");
            return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
        } else if (!UserUtil.isValidPass(pass)) {
            errorResponse.put(MESSAGE_KEY, "Formato de 'password' inválido: " + pass);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    public static ResponseEntity<Object> successResponses(UserDetails createdUser, String email) {

        Map<String, String> response = new LinkedHashMap<>();
        if (createdUser != null) {
            response.put(RESPONSE_ID, createdUser.getId().toString());
            response.put(RESPONSE_CREATED, getDateFromDataSource(createdUser.getCreated()));
            response.put(RESPONSE_MODIFIED, getDateFromDataSource(createdUser.getModified()));
            response.put(RESPONSE_LAST_LOGIN, getDateFromDataSource(createdUser.getLastLogin()));
            response.put(RESPONSE_TOKEN, createdUser.getToken());
            response.put(RESPONSE_IS_ACTIVE, createdUser.getToken());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return userNotFound(email);
    }

    public static String getDateFromDataSource(Timestamp time) {
        if(!ObjectUtils.isEmpty(time)) {
            return time.toString();
        }
        return "null";
    }
}
