package com.ceszke.security.rsa.encryption.encrypt;

import com.ceszke.security.rsa.keypair.Key;
import com.ceszke.security.rsa.keypair.KeyPair;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@Controller
@AllArgsConstructor
public class EncryptionController {

    private EncryptionService encryptionService;

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @PostMapping("/")
    public String encode(@RequestParam("textToEncrypt") String textToEncrypt, @RequestParam Integer length, Model model) {

        KeyPair keyPair = encryptionService.getKeys(length);
        Key publicKey = keyPair.getPublicKey();
        Key privateKey = keyPair.getPrivateKey();
        BigInteger encryptedText = encryptionService.encrypt(textToEncrypt, publicKey);

        model.addAttribute("textToEncrypt", textToEncrypt);
        model.addAttribute("length", length);
        model.addAttribute("publicExponent", publicKey.getExponent());
        model.addAttribute("privateExponent", privateKey.getExponent());
        model.addAttribute("modulus", publicKey.getExponent());
        model.addAttribute("encryptedText", encryptedText);

        return "index";
    }
}
