package com.example.graphql.utils;

import org.apache.commons.net.util.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AESUtils {
    protected static final Logger logger = LogManager.getLogger("demo_graphql");
    // key
    public static final String KEY = "zhelixie16weimim";
    private static final String CHARSET = "utf-8";
    // Offset
    private static final int OFFSET = 16;
    private static final String TRANSFORMATION = "AES/CBC/NoPadding";
    private static final String AES = "AES";

    public static String encrypt(String content) {
        return encrypt(content, KEY);
    }


    public static String decrypt(String content) {
        return decrypt(content, KEY);
    }

    public static String encrypt(String data, String key) {

        logger.debug("Started encrypting password");
        Cipher cipher = null;//"Algorithm/Mode/Complementary Method" NoPadding PkcsPadding
        try {
            cipher = Cipher.getInstance(TRANSFORMATION);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        int blockSize = 0;
        if (cipher != null) {
            blockSize = cipher.getBlockSize();
        }

        byte[] dataBytes = data.getBytes();
        int plaintextLength = dataBytes.length;
        if (plaintextLength % blockSize != 0) {
            plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
        }

        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), AES);
        IvParameterSpec ivspec = new IvParameterSpec(key.getBytes());

        try {
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        byte[] encrypted = new byte[0];
        try {
            encrypted = cipher.doFinal(plaintext);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        logger.debug("Finished encrypting password");
        return new Base64().encodeToString(encrypted);
    }


    public static String decrypt(String content, String key) {
        logger.debug("Started decrypting password");
        System.out.println("content is " + content.contains(" "));
        SecretKeySpec skey = new SecretKeySpec(key.getBytes(), AES);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes(), 0, OFFSET);
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(TRANSFORMATION);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            if (cipher != null) {
                cipher.init(Cipher.DECRYPT_MODE, skey, iv);// Initialize
            }
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        byte[] result = new byte[0];
        try {
            if (cipher != null) {
                result = cipher.doFinal(new Base64().decode(content));
            }
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        System.out.println("content is " + result.toString().toCharArray());
        logger.debug("Finished decrypting password");
        return new String(result); // decrypt

    }

    private static String getTrailingSpaces(String str) {
        Pattern p = Pattern.compile(".*(\\s+)$");
        Matcher m = p.matcher(str);
        String trailing = "";
        if (m.matches()) {
            trailing = m.group(1);
        }
        return trailing;
    }
} 