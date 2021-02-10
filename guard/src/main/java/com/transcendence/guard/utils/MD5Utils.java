package com.transcendence.guard.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author Joephone on 2021/2/10 0010 上午 10:18
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc m5加密的算法
 * @Edition 1.0
 * @EditionHistory
 */
public class MD5Utils {
    /**
     *
     */
    public static String encode(String text){
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b:result) {
                int number = b&0xff;
                String hex = Integer.toHexString(number);
                if(hex.length()==1){
                    sb.append("0"+hex);
                }else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
