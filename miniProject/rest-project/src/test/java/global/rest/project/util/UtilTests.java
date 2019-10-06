package global.rest.project.util;

import global.rest.project.model.user.Phone;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class UtilTests {

    @Test
    public void passValidationTest() {

        assertTrue(UserUtil.isValidPass("12Hz"));
        assertTrue(UserUtil.isValidPass("A12s"));
        assertTrue(UserUtil.isValidPass("12sA"));
        assertFalse(UserUtil.isValidPass("A"));
        assertFalse(UserUtil.isValidPass("Bb"));
        assertFalse(UserUtil.isValidPass("Cc1"));
    }

    @Test
    public void emailValidationTest() throws IOException {
        List<Phone> listOfPhones = UserUtil.getListOfPhonesFromString("[{\"number\": \"1234567\",\"cityCode\": \"1\",\"countryCode\": \"57\"}]");
        System.out.println(listOfPhones.toString());
        assertTrue(UserUtil.isValidEmail("a@z.com"));
        assertTrue(UserUtil.isValidEmail("ya@z.com.ti"));
        assertFalse(UserUtil.isValidEmail("@z.com"));
        assertFalse(UserUtil.isValidEmail("a@"));
    }

    @Test
    public void phoneListParsingTest() throws IOException {
        String phones = "[{\"number\": \"1234567\",\"cityCode\": \"1\",\"countryCode\": \"57\"}]";
        List<Phone> listOfPhones = UserUtil.getListOfPhonesFromString(phones);

        assertTrue(listOfPhones.get(0).getNumber().equals("1234567"));
        assertTrue(listOfPhones.get(0).getCityCode().equals("1"));
        assertTrue(listOfPhones.get(0).getCountryCode().equals("57"));
    }
}
