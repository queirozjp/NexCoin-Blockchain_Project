package com.nexcoin.util;

import java.security.*;

public class KeyGenerator{
    // Generate a pair of keys (public and private) for the wallet
    public static KeyPair generateKeyPair(){
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC"); 
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            keyGen.initialize(256, random);
            return keyGen.generateKeyPair();
        } catch (Exception e) { throw new RuntimeException(e); }
    }
}