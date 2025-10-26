package com.nexcoin.entities;

import java.sql.Timestamp;
import java.util.*;

public class Blockchain {
    private Block genesisBlock;
    private Wallet genesisWallet;
    private Block latestBlock;
    private int difficulty = 5;
    private ArrayList<Transaction> pendingTransactions = new ArrayList<>();;
    private float miningReward = 10;

    // Constructor - Initiate the blockchain and reward the mining operation
    public Blockchain(){
        ArrayList<Transaction> transactions = new ArrayList<>();
        this.genesisWallet = new Wallet("GENESIS");
        transactions.add(Transaction.genesisTransaction(genesisWallet));
        Timestamp time = Timestamp.valueOf("2025-10-16 16:42:31.789");
        this.genesisBlock = new Block(0, transactions, time);
        this.latestBlock = genesisBlock;
    }
    
    // Print Blockchain
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Block current = latestBlock;

        while (current != null) {
            sb.append("Block index: ").append(current.getIndex()).append("\n");
            sb.append("Timestamp : ").append(current.getTime()).append("\n");
            sb.append("Transactions:").append(current.transactionsToString());
            sb.append("Hash      : ").append(current.getHash()).append("\n");
            sb.append("PrevHash  : ").append(current.getPreviousHash()).append("\n");
            sb.append("------------------------------\n");

            current = current.getPreviousBlock(); 
        }

        return sb.toString();
    }

    // Getters - genesisBlock | genesisWallet | latestBlock | pendingTransactions | difficulty | miningReward
    public Block getGenesisBlock() {
        return this.genesisBlock; 
    }
    public Wallet getGenesisWallet(){
        return this.genesisWallet;
    }
    public Block getLatestBlock(){
        return this.latestBlock; 
    }
    public ArrayList<Transaction> getPendingTransactions(){
        return this.pendingTransactions;
    }
    public int getDifficulty(){
        return this.difficulty;
    }
    public float getMiningReward(){
        return this.miningReward;
    }

    // Setter - latestBlock
    public void setLatestBlock(Block newBlock){
        this.latestBlock = newBlock; 
    }

    // Add a Transcation to the transactions arraylist
    public void addPendingTransaction(Transaction transaction){
        this.pendingTransactions.add(transaction);
    }
    // Clear transactions arraylist
    public void clearPendingTransactions(){
        this.pendingTransactions = new ArrayList<>();
    }
}