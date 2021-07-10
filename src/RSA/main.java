package RSA;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class main {

    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {

        String s = "\"On the other hand, we denounce with ri" +
                "ghteous indignation and dislike men who are so beguiled and de" +
                "moralized by the charms of pleasure of the moment, so blinded by des" +

                "ire, that they cannot foresee the pain and trouble that are bound to ensue; an" +
                "d equal blame belongs to those who fail in their duty through weakness of w" +
                "ill, which is the same as saying through shrinking from toil and pain. These " +
                "cases are perfectly simple and easy to distinguish. In a free hour, when our pow" +
                "er of choice is untrammelled and when nothing prevents our being able to do what we" +
                " like best, every pleasure is to be welcomed and every pain avoided. But in certai" +
                "n circumstances and owing to the claims of duty or the obligations of business it " +
                "will frequently occur that pleasures have to be repudiated and annoyances accepted. " +
                "The wise man therefore always holds in these matters to this principle of selection:" +
                " he rejects pleasures to secure other" +


                " greater pleasures, or else he endures pains to avoid worse pains.\"";

        Cipher cipher = Cipher.getInstance("RSA");

        KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");

        pairgen.initialize(4096);

        KeyPair keyPair = pairgen.generateKeyPair();
        Key publicKey = keyPair.getPublic();
        Key privateKey = keyPair.getPrivate();

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] bytes = cipher.doFinal(s.getBytes());
        for (byte b : bytes) {
            System.out.print(b);
        }
        System.out.println("\n");

        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decripteBytes = decriptCipher.doFinal(bytes);

        for (byte b : decripteBytes) {
            System.out.print((char) b);
        }
    }
}
