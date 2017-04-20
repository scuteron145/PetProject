package com.project.utils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ValidationUtils {
    public static ValidationUtils instanse = new ValidationUtils();
    private String allowablePasswordSymbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public boolean isCorrectNickOrPassword(String password, int minPasswordlength, int maxPasswordlength) throws Exception{
        if(password == null || password.equals("")){
            return false;
        }
        if( (password.length() > maxPasswordlength) || (password.length() < minPasswordlength) ){
            return false;
        }
        char[] passwordInChars = password.toCharArray();
        for (char i : passwordInChars
             ) {
            if(!allowablePasswordSymbols.contains(String.valueOf(i))){
                return false;
            }
        }
        return true;
    }

    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public boolean isCorrectAge(String age){
        try{
            int intAge = Integer.valueOf(age);
            if(intAge < 0){
                return false;
            }
        } catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }
}
