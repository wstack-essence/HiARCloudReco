package io.hiar;

import io.hiar.config.CloudConfig;
import io.hiar.recognize.Recognize;
import io.hiar.recognize.RecognizeResult;

import java.io.IOException;

/**
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //set your appkey and secret
        CloudConfig.appkey = "";
        CloudConfig.secret = "";

        Recognize reco = new Recognize();
        try
        {
            RecognizeResult recoResult = reco.recognize("testData/100.jpg", "", 1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
