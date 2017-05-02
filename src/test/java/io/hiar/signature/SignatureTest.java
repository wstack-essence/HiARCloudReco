package io.hiar.signature;

import com.sun.tools.classfile.Signature_attribute;
import io.hiar.utils.Utils;
import org.junit.Test;

import java.util.Date;

/**
 * @Author lerry
 * @Datetime 02/05/2017 19:24
 */
public class SignatureTest
{
    @Test
    public void getSignature()
    {
        String timeStamp = Long.toString(new Date().getTime());
        String nonce = Utils.randomString(8);

        String appkey = "nzShtWOaYL";
        String secret = "52689a2b5ca45152bc0a6f3a58d6fed3";

        String signature = Signature.getSignature(appkey, secret, timeStamp, nonce);
        System.out.println(signature);
    }
}
