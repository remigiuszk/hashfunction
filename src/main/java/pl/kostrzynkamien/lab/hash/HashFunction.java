package pl.kostrzynkamien.lab.hash;

import org.springframework.security.crypto.password.PasswordEncoder;

public class HashFunction implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        String passwordString = charSequence.toString();
        String salt = "sz9QOPHlpu";
        passwordString += salt;
        final String RANDOMIZED_STRING = "SKNk7XAAbFic6s8BLdJ";
               return xor(passwordString, RANDOMIZED_STRING);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        String passwordString = charSequence.toString();
        String salt = "sz9QOPHlpu";
        passwordString += salt;
        final String RANDOMIZED_STRING = "SKNk7XAAbFic6s8BLdJ";
        return xor(passwordString, RANDOMIZED_STRING).equals(s);
    }

    static String xor(String password, String randomizer) {
        char firstChar;
        char secondChar;
        StringBuilder temp=new StringBuilder();
        int i=0;
        if(password.length()<randomizer.length())
        {
            int difference = randomizer.length()-password.length();
            for(int k=0; k<difference;k++){
                password+=1;
            }
        }
        for(int j=1;j<password.length();j++){
            if(j>=randomizer.length()) {
                secondChar=randomizer.charAt(i++);
            }
            else {
                secondChar = randomizer.charAt(j);
            }
                firstChar = password.charAt(j);
                String xorVal = Integer.toHexString((firstChar ^ secondChar));
                temp.append(xorVal.charAt(0));
        }
        return temp.toString();
    }
}
