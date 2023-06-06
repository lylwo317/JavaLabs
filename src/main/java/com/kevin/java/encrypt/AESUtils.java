package com.kevin.java.encrypt;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Base64;

public class AESUtils {
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_ECB_STR = "AES/ECB/PKCS5Padding";
    private static final String ALGORITHM_GCM_STR = "AES/GCM/NoPadding";
    private static final String ALGORITHM_CBC_STR = "AES/CBC/PKCS5Padding";
    private static final String ALGORITHM_CBC_NO_PADDING_STR = "AES/CBC/NoPadding";
    private static final String ALGORITHM_ECB_NO_PADDING_STR = "AES/ECB/NoPadding";

    /**
     * SecretKeySpec类是KeySpec接口的实现类,用于构建秘密密钥规范
     */
//    private SecretKeySpec key;

    /**
     * AES加密
     * @param data
     * @return
     * @throws Exception
     */
    public byte[] encryptData(String data, String mode, byte[] key, byte[] iv) throws Exception {
        return encryptData(data.getBytes(), mode, key, iv);
    }

    private static final int KEY_LENGTH = 16;
    private static final String DEFAULT_VALUE = "";

    public File encryptFile(File sourceFile, String toFile, byte[] key, String head) {
        File encryptFile = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        CipherInputStream cipherInputStream = null;
        try {
            inputStream = new FileInputStream(sourceFile);
            encryptFile = new File(toFile);
            outputStream = new FileOutputStream(encryptFile);
            Cipher cipher = initAESCipher(key, Cipher.ENCRYPT_MODE);
            cipherInputStream = new CipherInputStream(inputStream, cipher);
            byte[] cache = new byte[1024];
            int length;
            if (head != null && !(head.length() == 0)) {
                outputStream.write(head.getBytes(), 0, head.length());
            }

            while((length = cipherInputStream.read(cache)) != -1) {
                outputStream.write(cache, 0, length);
                outputStream.flush();
            }


        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(cipherInputStream);
            closeStream(outputStream);
            closeStream(inputStream);
        }
        return encryptFile;
    }

    public File decryptFile(String soureFile, String toFile, byte[] key, String head) {
        File decryptFile = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        CipherOutputStream cipherOutputStream = null;
        try {
            inputStream = new FileInputStream(soureFile);
            if (head != null && !(head.length() == 0)) {
                inputStream.skip(head.length());
            }

            decryptFile = new File(toFile);
            outputStream = new FileOutputStream(decryptFile);
            Cipher cipher = initAESCipher(key, Cipher.DECRYPT_MODE);
            cipherOutputStream = new CipherOutputStream(outputStream, cipher);
            byte[] buffer = new byte[1024];
            int length;
            while((length = inputStream.read(buffer))!= -1) {
                cipherOutputStream.write(buffer, 0, length);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(cipherOutputStream);
            closeStream(outputStream);
            closeStream(inputStream);
        }
        return decryptFile;
    }


    public void closeStream(Closeable stream) {
        if(stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Cipher initAESCipher(byte[] key, int mode) {
        Cipher cipher = null;
        try {
//            byte rawKey[] = toMakeKey(key, KEY_LENGTH, DEFAULT_VALUE).getBytes();
            byte[] rawKey = key;
            SecretKeySpec keySpec = new SecretKeySpec(rawKey, "AES");
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(mode, keySpec);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return cipher;
    }

    private String toMakeKey(String str, int strLength, String value) {
        int length = str.length();
        if(length < strLength) {
            while(length < strLength) {
                str = str + value;
                length = str.length();
            }
        }
        return str;
    }

    /**
     * AES加密
     * @param data
     * @return
     * @throws Exception
     */
    public byte[] encryptData(byte[] data, String mode, byte[] keyBytes, byte[] iv) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyBytes, ALGORITHM);
        Cipher cipher = Cipher.getInstance(mode); // 创建密码器
        AlgorithmParameterSpec ivParameterSpec = null;
        if (iv != null && iv.length > 0) {
            if (ALGORITHM_GCM_STR.equals(mode)) {
                ivParameterSpec = new GCMParameterSpec(128, iv);
            } else if (ALGORITHM_CBC_STR.equals(mode) || ALGORITHM_CBC_NO_PADDING_STR.equals(mode)) {
                ivParameterSpec = new IvParameterSpec(iv);
            }
        }
        if (ivParameterSpec != null) {
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);// 初始化
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
        }
        byte[] bytes = cipher.doFinal(data);
        System.out.println("模式：" + mode);
        System.out.println("加密输入：" + Arrays.toString(data));
        System.out.println("加密输出：" + Arrays.toString(bytes));
        return bytes;
//        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * AES解密
     * @param base64Data
     * @return
     * @throws Exception
     */
    public String decryptData(String base64Data, String mode, byte[] key, byte[] iv) throws Exception{
        byte[] decode = Base64.getDecoder().decode(base64Data);
        return decryptData(decode, mode, key, iv);
    }

    /**
     * AES解密
     * @param data
     * @return
     * @throws Exception
     */
    public String decryptData(byte[] data, String mode, byte[] keyBytes, byte[] iv) throws Exception{
        SecretKeySpec key = new SecretKeySpec(keyBytes, ALGORITHM);
        Cipher cipher = Cipher.getInstance(mode); // 创建密码器
        AlgorithmParameterSpec ivParameterSpec = null;
        if (iv != null && iv.length > 0) {
            if (ALGORITHM_GCM_STR.equals(mode)) {
                ivParameterSpec = new GCMParameterSpec(128, iv);
            } else if (ALGORITHM_CBC_STR.equals(mode) || ALGORITHM_CBC_NO_PADDING_STR.equals(mode)) {
                ivParameterSpec = new IvParameterSpec(iv);
            }
        }
        if (ivParameterSpec != null) {
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key);
        }

        byte[] bytes = cipher.doFinal(data);
        System.out.println("模式：" + mode);
        System.out.println("解密输入：" + Arrays.toString(data));
        System.out.println("解密输出：" + Arrays.toString(bytes));
        return new String(bytes);
    }

    /**
     * hex字符串 转 byte数组
     * @param s
     * @return
     */
    private static byte[] hex2byte(String s) {
        if (s.length() % 2 == 0) {
            return hex2byte (s.getBytes(), 0, s.length() >> 1);
        } else {
            return hex2byte("0"+s);
        }
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static byte[] hex2byte (byte[] b, int offset, int len) {
        byte[] d = new byte[len];
        for (int i=0; i<len*2; i++) {
            int shift = i%2 == 1 ? 0 : 4;
            d[i>>1] |= Character.digit((char) b[offset+i], 16) << shift;
        }
        return d;
    }

    public static void main(String[] args) throws Exception {
        AESUtils util = new AESUtils(); // 密钥
        byte[] key = new byte[]{49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102, 103};
        byte[] iv = new byte[]{49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102, 103};
        System.out.println("key " + Arrays.toString(key));
//        System.out.println("iv " + Arrays.toString(iv));
//        byte[] key = "1234567890123456".getBytes();
//        byte[] iv = "1234567890123456".getBytes();
        byte[] cbc = util.encryptData("|AAAAAAAAAAAAAAA|AAAAAAAAAAAAAAA|AAAAAAAAAAAAAAA", ALGORITHM_ECB_STR, key, null);
//        String encode = Base64.getEncoder().encodeToString(cbc);
        String s = util.decryptData(cbc, ALGORITHM_ECB_STR, key, null);
        System.out.println("" + s);

//        byte[] cbc = util.encryptData("Hello AES CBC", ALGORITHM_CBC_STR, key, iv);
//        System.out.println("CBC: " + util.decryptData(cbc, ALGORITHM_CBC_STR, key, iv));
//        byte[] cbcnopadding = util.encryptData("Hello CBC NoPadding", ALGORITHM_CBC_NO_PADDING_STR, key, iv);
//        System.out.println("CBC NoPadding: " + util.decryptData(cbcnopadding, ALGORITHM_CBC_NO_PADDING_STR, key, iv));

//        System.out.println("CBC NoPadding: " + util.decryptData(util.encryptData("Hello AES CBC NoPadding", ALGORITHM_CBC_STR, key, iv), ALGORITHM_CBC_STR, key, iv));
//        System.out.println("GCM: " + util.decryptData(util.encryptData("Hello AES GCM", ALGORITHM_GCM_STR, key, iv), ALGORITHM_GCM_STR, key, iv));


        //文件加解密
        File file = new File("target/Hello.txt");
        file.createNewFile();
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write("Hello".getBytes(StandardCharsets.UTF_8));
        }
        File encryptFile = util.encryptFile(file, "target/encryptFile.txt", key, "");

        util.decryptFile("target/encryptFile.txt", "target/out.txt", key, "");
//        util.encryptFile(new File())
    }
}
