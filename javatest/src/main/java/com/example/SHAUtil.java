package com.example;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author zcq
 * @date 2018/4/9.
 */

public class SHAUtil {
    public static final String PUBLIC_KEY = "qpda5-eu8uc-bta6c-qrdm4-775v7-nmymb-c1trv";
    public static final String PRIVATE_KEY = "qFkgnEyPlw";

    public static void main(String[] args) {
        String encryptKey;
        try {
            encryptKey = MD5Encrypt.getMessageDigest(PRIVATE_KEY);
            System.out.println(" encryptKey : " + encryptKey);
            String str = sha256_HMAC("1419408940756:afdgf454\\nPOST\\n/v0.3/tokens/be38bc32-e5f9-421f-8da6-606cbfd2253e/actions/valid\\n101uccenter.web.sdp.99.com", "qFkgnEyPlw");
            System.out.println(" getSignature : " + str);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 将加密后的字节数组转换成字符串
     *
     * @param b
     *            字节数组
     * @return 字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString();
    }

    /**
     * sha256_HMAC加密
     *
     * @param message
     *            消息
     * @param secret
     *            秘钥
     * @return 加密后字符串
     */
    private static String sha256_HMAC(String message, String secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
            System.out.println("hash:"+hash);
            hash = base64Encrypt(hash);
            String decodeText = base64Decrypt(hash);
        } catch (Exception e) {
            System.out.println("Error HmacSHA256 ===========" + e.getMessage());
        }
        return hash;
    }

    private static String base64Encrypt(String text){
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] textByte = new byte[0];
        try {
            textByte = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //编码
        String encodedText = encoder.encodeToString(textByte);
        System.out.println("encodedText:"+encodedText);
        return encodedText;
    }

    private static String base64Decrypt(String text){
        Base64.Decoder decoder = Base64.getDecoder();
        String decodedText = "";
        //解码
        try {
            decodedText = new String(decoder.decode(text),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("decodedText:"+decodedText);
        return decodedText;
    }
}
