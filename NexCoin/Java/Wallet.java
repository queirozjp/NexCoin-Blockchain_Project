import java.util.*;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.PrivateKey;

public class Wallet {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private String nickname;

    public Wallet(String nickname){
        KeyPair keyPair = KeyGenerator.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
        this.nickname = nickname;
    }

    public String getAddress(){
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
    
    public PrivateKey getPrivateKey(){
        return privateKey;
    }
    public PublicKey getPublicKey(){
        return publicKey;
    }
    public String getNickname(){
        return nickname;
    }
}
