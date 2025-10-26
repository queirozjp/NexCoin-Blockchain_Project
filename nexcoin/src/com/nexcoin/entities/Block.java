package com.nexcoin.entities;

import java.sql.*;
import java.util.*;
import java.security.MessageDigest;

public class Block {
    private int index;
    private String hash;
    private String previousHash;
    private ArrayList<Transaction> transactions;
    private Timestamp time;
    private Block previousBlock;
    private int nonce;

    // Constructor - Register the Genesis Block 
    public Block(int index, ArrayList<Transaction> transactions, Timestamp time){
        this.index = index;
        this.transactions = new ArrayList<>(transactions);
        this.time = time;;
        this.previousHash = "0";
        this.previousBlock = null;
        this.hash = computeHash(hashStringBuilder(this.transactions));
    }

    // Constructor - Register any other block
    public Block(int index, ArrayList<Transaction> transactions, Timestamp time, Block previousBlock){
        this.index = index;
        this.transactions = new ArrayList<>(transactions);
        this.time = time;
        this.previousBlock = previousBlock;
        this.previousHash = previousBlock.getHash();
        this.hash = computeHash(hashStringBuilder(this.transactions));
    }

    // ProofOfWork - Prevent tempering of the blockchain | Security
    public void proofOfWork(int difficulty){
        String target = "0".repeat(difficulty);
        while (true){
            this.hash = computeHash(hashStringBuilder(this.transactions));
            if (this.hash.substring(0, difficulty).equals(target)){
                break;
            }
            this.nonce++;
        }
    }

    // Create a String to use as input to create the hash of a Block 
    public String hashStringBuilder(ArrayList<Transaction> transactions){
        StringBuilder sb = new StringBuilder();
        sb.append(this.index).append(this.previousHash).append(this.time.toString()).append(this.nonce);
        for (Transaction tx : transactions) {
            sb.append(tx.toString()); 
        }
        return sb.toString();
    }
    
    // Create hash for a Block 
    public String computeHash(String in){
        try{
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            byte[] hash = instance.digest(in.getBytes("UTF-8"));
            StringBuilder hexadecimal = new StringBuilder();
            for (byte i : hash){
                String temp = Integer.toHexString(0xff & i);
                if (temp.length() == 1) hexadecimal.append('0');
                hexadecimal.append(temp);
            }
            return hexadecimal.toString();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Print the trasactions arraylist
    public String transactionsToString(){
        StringBuilder sb = new StringBuilder();
        for (Transaction t : transactions){
            sb.append("[" + t.getSender().getUsername() + " -> " + t.getReceiver().getUsername() + " Amount: " + t.getCoinAmount() + "]" +"\n");
        }
        return sb.toString();
    }

    // Getters - Index | Transactions | Time | PreviousHash | PreviousBlock | Hash
    public int getIndex(){ 
        return index; 
    } 
    public ArrayList<Transaction> getTransactions(){ 
        return new ArrayList<>(transactions); 
    }
    public Timestamp getTime(){ 
        return time; 
    }
    public String getPreviousHash(){ 
        return previousHash; 
    }
    public Block getPreviousBlock(){ 
        return previousBlock; 
    }
    public String getHash(){ 
        return hash; 
    }

}
