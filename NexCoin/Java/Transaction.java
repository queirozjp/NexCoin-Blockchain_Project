import java.security.PrivateKey;
import java.security.PublicKey;

public class Transaction {
    private String signature;
    private Wallet sender;
    private Wallet receiver;
    private float coinAmount;

    // Constructor to register a transaction

    public Transaction(Wallet sender, Wallet receiver, float coinAmount){
        this.sender = sender;
        this.receiver = receiver;
        this.coinAmount = coinAmount;
    }

    public static Transaction genesisTransaction(Wallet receiver) {
        return new Transaction(null, receiver, 0); // sender = null
    }

    public void signTransaction(PrivateKey privateKey){
        if (sender == null) return;
        String data = sender.getAddress() + receiver.getAddress() + coinAmount;
        this.signature = TransactionSignature.signTransaction(data, privateKey);
    }
    
    public boolean isTransactionValid(PublicKey publicKey){
        if (sender == null) return true;
        String data = sender.getAddress() + receiver.getAddress() + coinAmount;
        return TransactionSignature.verifyTransaction(data, signature, publicKey);
    }

    // Getters - Sender | Receiver | CoinAmount

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
