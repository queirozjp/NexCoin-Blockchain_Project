package com.nexcoin.controller;

import java.sql.*;
import java.util.Objects;

import com.nexcoin.entities.Block;
import com.nexcoin.entities.Blockchain;
import com.nexcoin.entities.Transaction;
import com.nexcoin.entities.Wallet;

public class BlockchainController {

    // Register transaction signing and checking the validity
    public static void registerTransaction(Wallet sender, Wallet receiver, float coinAmount, Blockchain blockchain){
        Transaction transaction = new Transaction(sender, receiver, coinAmount);
        transaction.signTransaction(transaction.getSender().getPrivateKey());
        if (transaction.isTransactionValid(transaction.getSender().getPublicKey())){
            blockchain.addPendingTransaction(transaction);
        }
    }

    // Mining the blocks
    public static void processPendingBlocks(Wallet minerAdress, Blockchain blockchain){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        int newIndex = blockchain.getLatestBlock().getIndex() + 1;
        Block newBlock = new Block(newIndex, blockchain.getPendingTransactions(), time, blockchain.getLatestBlock());
        newBlock.proofOfWork(blockchain.getDifficulty());
        blockchain.setLatestBlock(newBlock);
        blockchain.clearPendingTransactions();
        blockchain.addPendingTransaction(new Transaction(blockchain.getGenesisWallet(),minerAdress,blockchain.getMiningReward()));
    }

    // Fetch the balance using the previous transactions
    public static float fetchBalance(Wallet Address, Blockchain blockchain){
        float balance = 0;
        Block current = blockchain.getLatestBlock();

        while (current != null) {
            for (Transaction tx : current.getTransactions()){
                if (Objects.equals(tx.getSender(), Address)){
                    balance -= tx.getCoinAmount();
                }
                if (Objects.equals(tx.getReceiver(), Address)){
                    balance += tx.getCoinAmount();
                }
            }
            current = current.getPreviousBlock();
        }
        return balance;
    }

    public static float fetchPendingSpending(Wallet address, Blockchain blockchain) {
        float pendingSpending = 0;
        for (Transaction tx : blockchain.getPendingTransactions()) {
            if (Objects.equals(tx.getSender(), address)) {
                pendingSpending += tx.getCoinAmount();
            }
        }
        return pendingSpending;
    }

    // Check the validity of the chain by computing the hash of the current block again and comparing,
    // and by comparing the previoushash of the current block with the hash of the previous block
    public static boolean isChainValid(Blockchain blockchain){
        Block current = blockchain.getLatestBlock();

        while (current != null 
                       && current.getPreviousBlock() != null 
                       && !current.getPreviousBlock().getPreviousHash().equals("0")) {
            Block anteriorBlock = current.getPreviousBlock(); 
            if (!current.getHash().equals(current.computeHash(current.hashStringBuilder(current.getTransactions())))) {
                System.out.println("ERROAQUI");
                return false;
            }
            if (!current.getPreviousHash().equals(anteriorBlock.getHash())) {
                return false;
            }
            current = anteriorBlock;
        }
        return true;
    }
}
