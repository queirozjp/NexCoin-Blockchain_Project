package com.nexcoin;
import com.nexcoin.entities.Wallet;
import com.nexcoin.util.WalletValidation;
import com.nexcoin.entities.Blockchain;
import com.nexcoin.controller.BlockchainController;
import com.nexcoin.entities.Transaction;

import java.util.*;

public class NexCoinApplication {

    public static void menu(){
        System.out.println("\n1. Create a new wallet");
        System.out.println("2. Fetch wallet balance");
        System.out.println("3. Display list of wallets");
        System.out.println("4. Display public key");
        System.out.println("5. Make a new transaction");
        System.out.println("6. Display pending transactions");
        System.out.println("7. Mine transactions");
        System.out.println("8. View blockchain");
        System.out.println("9. Verify blockchain integrity");   
        System.out.println("10. Return to main menu");
        System.out.print("Option: ");

    } 

    public static WalletValidation isWalletValid(Wallet [] walletlist, String publicKey, int walletsindex){
        for (int i = 0; i < walletsindex; i++){
            if (publicKey.equals(walletlist[i].getAddress())){
                return new WalletValidation(walletlist[i], true);
            }
        }
        return new WalletValidation(null, false);
    }

    public static void main(String[] args) {
        Wallet[] walletlist = new Wallet[500];
        String start = "0", option = "0";
        int walletscount = 0;
        float deposit;
        String username, publicKey;
        String receiverPublicKey = "1", senderPublicKey;
        Scanner sc = new Scanner(System.in);
        while (!start.equals("2")){
            System.out.println("------------ Blockchain | NexCoin (NX) ------------");
            System.out.println("1. Create a new blockchain");
            System.out.println("2. Exit");
            System.out.print("Option: ");
            start = sc.nextLine();
            if ( !start.equals("2") && !start.equals("1")){
                System.out.println("TYPE A VALID NUMBER!!!");
            }
            if (start.equals("1")){
                Blockchain blockchain  = new Blockchain();
                Wallet genesis = blockchain.getGenesisWallet();
                do {
                    menu(); // 1. MOSTRA o menu e o prompt "Option: "
                    option = sc.nextLine(); // 2. LÊ a opção do usuário
                    switch (option) {
                        case "1":
                            System.out.println("To cancel operation type 0");
                            System.out.print("Enter the wallet username, it must be unique: ");
                            username = sc.nextLine().toLowerCase();
                            if (!username.equals("0")){
                                for (int i = 0; i < walletscount; i++){
                                    if (username.equals(walletlist[i].getUsername())){
                                        System.out.println("THE USERNAME MUST BE UNIQUE!!!");
                                        System.out.print("Enter the wallet username: ");
                                        username = sc.nextLine();
                                    }
                                }
                                walletlist[walletscount] = new Wallet(username);
                                System.out.print("Enter the deposit amount (it will only appear in your wallet after the block containing this transaction is mined!!): ");
                                deposit = sc.nextFloat();
                                sc.nextLine();
                                BlockchainController.registerTransaction(genesis, walletlist[walletscount], deposit, blockchain);
                                System.out.println("New wallet created!!!");
                                System.out.print("Public Key: " + walletlist[walletscount].getAddress());
                                walletscount++;
                            }
                            break;
                        case "2":
                            System.out.println("To cancel operation type 0");
                            System.out.print("Enter the wallet public key: ");
                            publicKey = sc.nextLine();
                            if (!publicKey.equals("0")) { 
                                WalletValidation userValidation = isWalletValid(walletlist, publicKey, walletscount);
                                if (userValidation.getExist()){
                                    System.out.println("Wallet found!!");
                                    System.out.print(userValidation.getWallet().getUsername() + 
                                                        "'s Balance: " + 
                                                        BlockchainController.
                                                        fetchBalance( userValidation.getWallet(), blockchain)
                                                    );
                                }
                                else{ System.out.println("WALLET NOT FOUND!!!"); }
                            }
                            break;
                        case "3":
                            for (int i = 0; i < walletscount; i++){
                                if ( i + 1 < walletscount){ System.out.print(walletlist[i].getUsername() + " | "); }
                                else{ System.out.print(walletlist[walletscount-1].getUsername()); }  
                            }
                            break;
                        case "4":
                            System.out.println("To cancel operation type 0");
                            System.out.print("Enter the wallet username: ");
                            username = sc.nextLine().toLowerCase();
                            boolean walletExists = true;
                            if (!username.equals("0")) { 
                                for (int i = 0; i < walletscount; i++){
                                    if (walletlist[i].getUsername().equals(username)){
                                        System.out.print("Public Key: " + walletlist[i].getAddress());
                                        walletExists = true;
                                        break;
                                    }
                                    walletExists = false;
                                }
                                if (!walletExists || walletscount == 0) { System.out.println("WALLET NOT FOUND!!!"); }
                            }
                            break;
                        case "5":
                            System.out.println("To cancel operation type 0");
                            System.out.print("Enter the sender's wallet public key: ");
                            senderPublicKey = sc.nextLine();
                            WalletValidation senderValidation = isWalletValid(walletlist, senderPublicKey, walletscount);
                            float confirmedBalance = BlockchainController.fetchBalance(senderValidation.getWallet(), blockchain);
                            float pendingSpending = BlockchainController.fetchPendingSpending(senderValidation.getWallet(), blockchain);
                            float effectiveBalance = confirmedBalance - pendingSpending;
                            while (!senderPublicKey.equals("0") &&senderValidation.getExist()) {
                                System.out.print("Enter the receivers's wallet public key: ");
                                receiverPublicKey = sc.nextLine();
                                WalletValidation receiverValidation = isWalletValid(walletlist, receiverPublicKey, walletscount);
                                int attempts = 0;
                                while (!receiverValidation.getExist() && attempts < 3){
                                    System.out.print("WALLET NOT FOUND!!!");
                                    System.out.print("Enter the receivers's wallet public key " + "(" + (3-attempts) + " attempts left): ");
                                    receiverPublicKey = sc.nextLine();
                                    receiverValidation = isWalletValid(walletlist, receiverPublicKey, walletscount);
                                    attempts++;
                                }
                                if (attempts == 3 || senderPublicKey.equals(receiverPublicKey)){ 
                                    System.out.println("FAILED TRANSACTION!!!");
                                    break; 
                                }
                                System.out.print("\nEnter the amount of nexcoins: ");
                                float amount = sc.nextFloat();
                                sc.nextLine();
                                if (amount > effectiveBalance || amount <= 0){
                                    System.out.println("FAILED TRANSACTION!!! (Insufficient funds or invalid amount)");
                                    System.out.println("Confirmed Balance: " + confirmedBalance);
                                    System.out.println("Pending Spending: " + pendingSpending);
                                    System.out.println("Effective Balance: " + effectiveBalance + " | Attempted: " + amount);
                                    break;
                                }else{
                                    BlockchainController.registerTransaction(senderValidation.getWallet(), receiverValidation.getWallet(), amount, blockchain);
                                    System.out.println("Transaction registered!!!");
                                    break;
                                }
                            }
                            if (!senderValidation.getExist()){ System.out.print("WALLET NOT FOUND!!!"); }
                            break;
                        case "6":
                            for (Transaction t : blockchain.getPendingTransactions()){
                                System.out.print(t.getSender().getUsername() + " -> " + t.getReceiver().getUsername() + " Amount: " + t.getCoinAmount() + " | ");
                            }
                            break;
                        case "7":
                            System.out.println("To cancel operation type 0");
                            System.out.print("Enter the wallet public key: ");
                            publicKey = sc.nextLine();
                            boolean found = true;
                            if (!publicKey.equals("0")) {
                                for (int i = 0; i < walletscount; i++){
                                    if (walletlist[i].getAddress().equals(publicKey)){
                                        BlockchainController.processPendingBlocks(walletlist[i], blockchain);
                                        System.err.println("Block Mined, You will receive 10NX when the next block is mined!!!");
                                        found = true;
                                        break;
                                    }
                                    found = false;
                                }  
                            }
                            if (!found || walletscount == 0){ System.out.println("WALLET NOT FOUND!!!"); }
                            break;
                        case "8":
                            System.out.println(blockchain.toString());
                            break;
                        case "9":
                            if(BlockchainController.isChainValid(blockchain)){
                                System.out.println("Blockchain is valid !!!"); 
                            }else {System.out.println("BLOCKCHAIN IS INVALID!!!");}
                            break;
                        default:
                            break;
                    }
                } while (!option.equals("10"));
            }
        }
        sc.close();
    }
}
