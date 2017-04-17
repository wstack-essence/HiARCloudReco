package io.hiar;

import io.hiar.signature.Signature;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String appKey = "test";
        String secret = "adf3aaflletgeadf";
        String timestamp = "1474601092811";
        String nonce = "dfafsaf";
        String signature = Signature.getSignature(appKey, secret, timestamp, nonce);
        System.out.println(signature);
    }
}
