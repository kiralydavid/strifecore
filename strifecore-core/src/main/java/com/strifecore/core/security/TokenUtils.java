package com.strifecore.core.security;

import com.strifecore.core.util.Clock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TokenUtils {

    private final Clock CLOCK;

    private final String TOKEN_SECRET_KEY;

    @Autowired
    public TokenUtils(String tokenSecretKey, Clock clock) {
        this.TOKEN_SECRET_KEY = tokenSecretKey;
        this.CLOCK = clock;
    }

    public String createToken(UserDetails userDetails, Long expires) {
        StringBuilder tokenBuilder = new StringBuilder();
        tokenBuilder.append(userDetails.getUsername());
        tokenBuilder.append(":");
        tokenBuilder.append(expires);
        tokenBuilder.append(":");
        tokenBuilder.append(computeSignature(userDetails, expires));

        return tokenBuilder.toString();
    }

    public String computeSignature(UserDetails userDetails, Long expires) {
        StringBuilder signatureBuilder = new StringBuilder();
        signatureBuilder.append(userDetails.getUsername());
        signatureBuilder.append(":");
        signatureBuilder.append(expires);
        signatureBuilder.append(":");
        signatureBuilder.append(userDetails.getPassword());
        signatureBuilder.append(":");
        signatureBuilder.append(TOKEN_SECRET_KEY);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No SHA-256 algorithm available!");
        }

        return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String[] parts = token.split(":");
        long expires = Long.parseLong(parts[1]);
        String signature = parts[2];

        if (expires < CLOCK.getTimeInMillis()) {
            return false;
        }

        return signature.equals(computeSignature(userDetails, expires));
    }

    public String getUserNameFromToken(String authToken) {

        if (null == authToken) {
            return null;
        }

        String[] parts = authToken.split(":");
        return parts[0];
    }
}
