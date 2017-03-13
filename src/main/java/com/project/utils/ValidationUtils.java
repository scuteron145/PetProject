package com.project.utils;
public class ValidationUtils {
        public static ValidationUtils instanse = new ValidationUtils();

        public boolean isCorrectNickOrPassword(String password, int minPasswordlength, int maxPasswordlength) throws Exception{
            if(password == null || password.equals("")){
                return false;
            }
            if( (password.length() > maxPasswordlength) || (password.length() < minPasswordlength) ){
                return false;
            }

            return true;
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
