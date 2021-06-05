package tools;

import common.exception.InvalidDeliveryInfoException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
        if (validatePhoneNumber(info.get("phone"))
                || validateName(info.get("name"))
                || validateAddress(info.get("address"))) return;
        else throw new InvalidDeliveryInfoException();
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 10) return false;
        if (!phoneNumber.startsWith("0")) return false;
        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean validateName(String name) {
        if (Objects.isNull(name)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public boolean validateAddress(String address) {
        if (Objects.isNull(address)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
}
