package com.ceszke.security.rsa.encryption.encrypt;

import com.ceszke.security.rsa.encryption.client.KeyServiceClient;
import com.ceszke.security.rsa.keypair.Key;
import com.ceszke.security.rsa.keypair.KeyPair;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@AllArgsConstructor
public class EncryptionService {

    private KeyServiceClient keyServiceClient;

    public KeyPair getKeys(Integer length) {
        return keyServiceClient.getKeyPair(length);
    }

    public BigInteger encrypt(String plainText, Key publicKey) {
        return new BigInteger(plainText.getBytes()).modPow(publicKey.getExponent(), publicKey.getModulus());
    }

    public String decrypt(BigInteger ciphertext, Key privateKey) {
        return new String(ciphertext.modPow(privateKey.getExponent(), privateKey.getModulus()).toByteArray());
    }
}
