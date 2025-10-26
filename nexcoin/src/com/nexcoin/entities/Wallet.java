package com.nexcoin.entities;
import com.nexcoin.util.KeyGenerator;

import java.util.*;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.PrivateKey;

public class Wallet {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private String username;


    // Constructor - create a wallet
    public Wallet(String username){
        KeyPair keyPair = KeyGenerator.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
        this.username = username;
    }

    // Getters - Address | privateKey | publicKey 
    public String getAddress(){
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
    public PrivateKey getPrivateKey(){
        return privateKey;
    }
    public PublicKey getPublicKey(){
        return publicKey;
    }
    public String getUsername(){
        return username;
    }

}