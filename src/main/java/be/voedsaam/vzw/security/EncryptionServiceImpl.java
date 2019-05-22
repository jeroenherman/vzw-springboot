package be.voedsaam.vzw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class EncryptionServiceImpl implements EncryptionService {

    private BCryptPasswordEncoder strongEncryptor;

    @Autowired
    public void setStrongEncryptor(BCryptPasswordEncoder strongEncryptor) {
        this.strongEncryptor = strongEncryptor;
    }

    public String encryptString(String input){
        return strongEncryptor.encode(input);
    }

    public boolean checkPassword(String plainPassword, String encryptedPassword){
        return strongEncryptor.matches(plainPassword,encryptedPassword);
    }
}
