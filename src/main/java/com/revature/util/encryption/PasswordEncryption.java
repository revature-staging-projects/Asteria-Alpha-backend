package com.revature.util.encryption;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryption {

    public String encryptString(final String str) {
        return BCrypt.withDefaults().hashToString(12,str.toCharArray());
    }

    public boolean verifyPassword(final String pw, final String password) {
        return BCrypt.verifyer().verify(pw.toCharArray(),password).verified;
    }

}
