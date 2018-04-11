package com.ceszke.rsa.keys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KeysServiceTest {

    @Autowired
    private KeysService keysService;


    @Test
    public void createKeyPair() {
        KeyPair keyPair = keysService.createKeyPair(128);

        String textToEncrytp = "Ala ma kota";
        BigInteger toEncrytp = new BigInteger(textToEncrytp.getBytes());
        BigInteger bplaintext, bciphertext;

        bciphertext = encrypt(toEncrytp, keyPair.getPublicKey());
        bplaintext = decrypt(bciphertext, keyPair.getPrivateKey());

        assertEquals(textToEncrytp, new String(bplaintext.toByteArray()));
    }


    private BigInteger encrypt(BigInteger plaintext, Key publicKey) {
        return plaintext.modPow(publicKey.getExponent(), publicKey.getModulus());
    }

    private BigInteger decrypt(BigInteger ciphertext,  Key privateKey) {
        return ciphertext.modPow(privateKey.getExponent(), privateKey.getModulus());
    }
}