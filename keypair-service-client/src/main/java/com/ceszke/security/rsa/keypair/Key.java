package com.ceszke.security.rsa.keypair;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Key {

    private BigInteger exponent;
    private BigInteger modulus;

}
