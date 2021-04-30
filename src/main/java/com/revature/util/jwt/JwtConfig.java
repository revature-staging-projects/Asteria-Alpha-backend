package com.revature.util.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

public class JwtConfig {

    private final String secret_key = (System.getProperty("secret_key") != null) ? System.getProperty("secret_key"): System.getenv("secret_key");

    private final SignatureAlgorithm SIG_ALG = SignatureAlgorithm.HS256;

    private final Key SIGNING_KEY;

    {
        final byte[] secret_bytes = DatatypeConverter.parseBase64Binary(secret_key);
        SIGNING_KEY = new SecretKeySpec(secret_bytes,SIG_ALG.getJcaName());

    }

    public SignatureAlgorithm getSignatureAlgorithm() {
        return SIG_ALG;
    }

    public Key getSigningKey() {
        return SIGNING_KEY;
    }

}
