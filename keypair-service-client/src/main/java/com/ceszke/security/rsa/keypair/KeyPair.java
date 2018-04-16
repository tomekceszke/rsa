package com.ceszke.security.rsa.keypair;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KeyPair {

    private Key publicKey;
    private Key privateKey;
}
