package com.base.ProjectTestAndroid;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Login {
    public static String OTP_CHARACTERS = "0123456789";

    public static boolean Validate_EmailID(String EmailID) {
        String regex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:vitstudent\\.ac\\.in)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(EmailID);
        return matcher.matches();
    }

    public static String generate_OTP(){
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int index = secureRandom.nextInt(OTP_CHARACTERS.length());
            otp.append(OTP_CHARACTERS.charAt(index));
        }

        return otp.toString();
    }
}
