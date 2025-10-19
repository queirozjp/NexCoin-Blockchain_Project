

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain  = new Blockchain();
        Wallet alice = new Wallet("Alice");
        Wallet bob = new Wallet("Bob");
        Wallet charlie = new Wallet("Charlie");
        System.out.println("Wallets criadas:");
        System.out.println("Alice: " + alice.getAddress());
        System.out.println("Bob: " + bob.getAddress());
        System.out.println("Charlie: " + charlie.getAddress());
        // Alice envia 10 moedas para Bob
        blockchain.registerTransaction(alice, bob, 10);

        // Bob envia 5 moedas para Charlie
        blockchain.registerTransaction(bob, charlie, 5);
        blockchain.processPendingBlocks(charlie);
        blockchain.registerTransaction(alice, charlie, 2);
        blockchain.processPendingBlocks(alice);
        System.out.println("Saldo Alice: " + blockchain.fetchBalance(alice));
        System.out.println("Saldo Bob: " + blockchain.fetchBalance(bob));
        System.out.println("Saldo Charlie: " + blockchain.fetchBalance(charlie));

        /*chain.registerTransaction(new Transaction("Joao", "Pedro", 12));
        chain.registerTransaction(new Transaction("Carlos", "Joao", 22));
        System.out.println("Starting the miner...");
        chain.processPendingBlocks("QueirozJpx");
        System.out.println("Balance QueirozJpx: " + chain.fetchBalance("QueirozJpx"));
        System.out.println("Balance Joao: " + chain.fetchBalance("Joao"));
        chain.processPendingBlocks("QueirozJpx");
        System.out.println("Balance QueirozJpx: " + chain.fetchBalance("QueirozJpx"));*/
        System.out.println(blockchain.getBlockchainString());
    }
}
