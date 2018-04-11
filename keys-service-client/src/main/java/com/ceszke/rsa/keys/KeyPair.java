package com.ceszke.rsa.keys;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KeyPair {

    private Key publicKey;
    private Key privateKey;
}
