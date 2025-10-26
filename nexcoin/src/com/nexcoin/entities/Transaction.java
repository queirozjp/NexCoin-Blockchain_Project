package com.nexcoin.entities;

import com.nexcoin.util.TransactionSignature;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Transaction {
    private String signature;
    private Wallet sender;
    private Wallet receiver;
    private float coinAmount;


    // Constructor - create a new transaction
    public Transaction(Wallet sender, Wallet receiver, float coinAmount){
        this.sender = sender;
        this.receiver = receiver;
        this.coinAmount = coinAmount;
    }

    // Construction - create the initial genesis transaction
    public static Transaction genesisTransaction(Wallet genesis) {
        return new Transaction(genesis, genesis, 0); 
    }

    // Sign the transaction 
    public void signTransaction(PrivateKey privateKey){
        if (sender == null) return;
        String data = sender.getAddress() + receiver.getAddress() + coinAmount;
        this.signature = TransactionSignature.signTransaction(data, privateKey);
    }
    
    // Check validity of the transaction
    public boolean isTransactionValid(PublicKey publicKey){
        if (sender == null) return true;
        String data = sender.getAddress() + receiver.getAddress() + coinAmount;
        return TransactionSignature.verifyTransaction(data, signature, publicKey);
    }


    // Getters - sender | receiver | coinAmount
    public Wallet getSender(){
        return sender; 
    }
    public Wallet getReceiver(){ 
        return receiver; 
    }
    public float getCoinAmount(){ 
        return coinAmount;
    }


    @Override
    public String toString() {
        return sender + " -> " + receiver + ": " + coinAmount;
    }
}