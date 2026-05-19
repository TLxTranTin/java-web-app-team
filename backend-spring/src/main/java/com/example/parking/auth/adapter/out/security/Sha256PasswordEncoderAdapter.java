package com.example.parking.auth.adapter.out.security;

import com.example.parking.auth.application.port.out.IPasswordEncoderPort;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Sha256PasswordEncoderAdapter implements IPasswordEncoderPort {

    @Override
    public String encode(String rawPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();

            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("Không thể mã hóa mật khẩu");
        }
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}