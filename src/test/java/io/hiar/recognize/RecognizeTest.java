package io.hiar.recognize;

import io.hiar.config.CloudConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Author lerry
 * @Datetime 02/05/2017 18:04
 */
public class RecognizeTest
{
    @Before
    public void init()
    {
        CloudConfig.appkey = "nzShtWOaYL";
        CloudConfig.secret = "52689a2b5ca45152bc0a6f3a58d6fed3";
    }


    @Test
    public void testRecognize()
    {
        String imagePath = "testData/100.jpg";
        Recognize reco = new Recognize();
        try
        {
            RecognizeResult recoResult = reco.recognize(imagePath, "", 1);
            Assert.assertEquals(recoResult.getRetCode(), 0);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}
