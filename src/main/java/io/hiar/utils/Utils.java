package io.hiar.utils;

import org.apache.commons.codec.binary.Base64;

import java.util.Random;

/**
 * @Author lerry
 * @Datetime 02/05/2017 16:59
 */
public class Utils
{
    private static Random randGen = new Random();

    private static char[] numbersAndLetters = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            .toCharArray();

    /**
     * 生成随机字符串
     *
     * @param int length 返回字符串的长度
     * @return string 字符串
     */
    public static String randomString(int length) {
        if (length < 1) {
            return null;
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; ++i) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(randBuffer);
    }

    /**
     * 生成字符串的Base64
     *
     * @author lerry
     * @date   2015年6月5日
     * @param str
     * @return
     */
    public static String base64(String str){
        return Utils.base64(str.getBytes());
    }

    /**
     * 根据二进制数组生成Base64
     *
     * @author lerry
     * @date   2015年6月5日
     * @param data
     * @return
     */
    public static String base64(byte[] data){
        return Base64.encodeBase64String(data);
    }
}
