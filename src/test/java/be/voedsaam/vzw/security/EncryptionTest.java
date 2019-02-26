package be.voedsaam.vzw.security;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncryptionTest {

    @Test
    public void encryptPassword(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encoded = passwordEncoder.encode("admin");
        System.out.println(encoded);
    }
}
