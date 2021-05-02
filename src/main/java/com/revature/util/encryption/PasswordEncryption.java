package com.revature.util.encryption;

import org.springframework.stereotype.Component;
import at.favre.lib.crypto.bcrypt.BCrypt;

@Component
public class PasswordEncryption {

    public String encryptString(final String str) {
        return BCrypt.withDefaults().hashToString(12,str.toCharArray());
    }

    public boolean verifyPassword(final String pw, final String password) {
        return BCrypt.verifyer().verify(pw.toCharArray(),password).verified;
    }

}
