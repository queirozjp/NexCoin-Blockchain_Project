import java.util.Base64;
import java.security.Signature;
import java.security.PublicKey;
import java.security.PrivateKey;

public class TransactionSignature{
    
    public static String signTransaction(String data, PrivateKey privateKey){
        try{
            Signature sign = Signature.getInstance("SHA256withECDSA");
            sign.initSign(privateKey);
            sign.update(data.getBytes());
            byte[] signature = sign.sign();
            return Base64.getEncoder().encodeToString(signature);
        }catch (Exception e){ throw new RuntimeException(e); }
    }

    public static boolean verifyTransaction(String data, String signature, PublicKey publicKey) {
        try {
            Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes());
            byte[] signatureBytes = Base64.getDecoder().decode(signature);
            return ecdsaVerify.verify(signatureBytes);
        } catch (Exception e) { throw new RuntimeException(e); }
    }
}
