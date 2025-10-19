import java.sql.*;
import java.util.*;

public class Blockchain {
    private Block genesisBlock;
    private Block latestBlock;
    private int difficulty = 2;
    private ArrayList<Transaction> pendingTransactions = new ArrayList<>();;
    private float miningReward = 10;

    public Blockchain(){
        ArrayList<Transaction> transactions = new ArrayList<>();
        Wallet genesisWallet = new Wallet("GENESIS");
        transactions.add(Transaction.genesisTransaction(genesisWallet));
        Timestamp time = Timestamp.valueOf("2025-10-16 16:42:31.789");
        this.genesisBlock = new Block(0, transactions, time);
        this.latestBlock = genesisBlock;
    }

    public void registerTransaction(Wallet sender, Wallet receiver, float coinAmount){
        Transaction transaction = new Transaction(sender, receiver, coinAmount);
        transaction.signTransaction(transaction.getSender().getPrivateKey());
        if (transaction.isTransactionValid(transaction.getSender().getPublicKey())){
            this.pendingTransactions.add(transaction);
        }
    }

    public void processPendingBlocks(Wallet minerAdress){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        int newIndex = latestBlock.getIndex() + 1;
        Block newBlock = new Block(newIndex, this.pendingTransactions, time, latestBlock);
        newBlock.proofOfWork(difficulty);
        latestBlock = newBlock;
        System.out.println("Block Mined! " + newBlock.getHash());
        this.pendingTransactions = new ArrayList<>();
        
        this.pendingTransactions.add(new Transaction(null,minerAdress,this.miningReward));
    }

    public float fetchBalance(Wallet Address){
        float balance = 0;
        Block current = latestBlock;

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

    public boolean isChainValid(){
        Block current = latestBlock;

        while (current != null && current.getPreviousBlock() != null) {
            Block previousBlock = current.getPreviousBlock();
            int index = current.getIndex();
            String previousHash = previousBlock.getHash();
            Timestamp time = current.getTime();
            String transactions = current.getTransactions().toString(); 
            if (!current.getHash().equals(current.computeHash(index + previousHash + time + transactions))){
                return false;
            }
            if (!current.getPreviousHash().equals(previousBlock.getHash())){
                return false;
            }
            current = current.getPreviousBlock();
        }
        return true;
    }

    public String getBlockchainString() {
        StringBuilder sb = new StringBuilder();
        Block current = latestBlock;

        while (current != null) {
            sb.append("Block index: ").append(current.getIndex()).append("\n");
            sb.append("Timestamp : ").append(current.getTime()).append("\n");
            sb.append("Transactions      : ").append(current.getTransactions()).append("\n");
            sb.append("Hash      : ").append(current.getHash()).append("\n");
            sb.append("PrevHash  : ").append(current.getPreviousHash()).append("\n");
            sb.append("------------------------------\n");

            current = current.getPreviousBlock(); 
        }

        return sb.toString();
    }

    
    public Block getGenesisBlock() {
        return genesisBlock; 
    }
    public Block getLatestBlock(){
        return latestBlock; 
    }
}
