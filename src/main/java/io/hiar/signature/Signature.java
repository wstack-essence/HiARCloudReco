package io.hiar.signature;

import java.util.Arrays;
import org.apache.commons.codec.binary.Base64;

/**
 * Created by lerry on 9/23/16.
 */
public class Signature {


    public static String getSignature(String appKey,  String secret,  String timestamp, String nonce) {
        String[] params = {appKey, secret, timestamp, nonce};
        for(int i=0; i< params.length; i++) {
            System.out.print(params[i] + "  ");
        }
        System.out.println();
        Arrays.sort(params);
        StringBuilder plaintext = new StringBuilder("");
        for (int i=0; i< params.length; i++){
            plaintext.append(params[i]);
        }
        System.out.println(plaintext);
        try {

            return Base64.encodeBase64String(HMACSHA1.getHMAC_SHA1(plaintext.toString(), secret));
        }catch (Exception e) {
            return "";
        }
    }
}
