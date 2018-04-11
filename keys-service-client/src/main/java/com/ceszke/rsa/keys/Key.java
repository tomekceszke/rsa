package com.ceszke.rsa.keys;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
public class Key {

    private BigInteger exponent;
    private BigInteger modulus;

}
