package com.kevin.java.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Created by: kevin
 * Date: 2022-06-21
 */
public class AESUtils {
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_STR = "AES/ECB/PKCS5Padding";
    private static final String ALGORITHM_NO_PADDING_STR = "AES/ECB/NoPadding";

    /**
     * SecretKeySpec类是KeySpec接口的实现类,用于构建秘密密钥规范
     */
    private SecretKeySpec key;

    public AESUtils(String hexKey) {
        key = new SecretKeySpec(hexKey.getBytes(), ALGORITHM);
    }

    /**
     * AES加密
     * @param data
     * @return
     * @throws Exception
     */
    public String encryptData(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR); // 创建密码器
        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
        byte[] bytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * AES解密
     * @param base64Data
     * @return
     * @throws Exception
     */
    public String decryptData(String base64Data) throws Exception{
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decode = Base64.getDecoder().decode(base64Data);
        return new String(cipher.doFinal(decode));
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

    private static byte[] hex2byte (byte[] b, int offset, int len) {
        byte[] d = new byte[len];
        for (int i=0; i<len*2; i++) {
            int shift = i%2 == 1 ? 0 : 4;
            d[i>>1] |= Character.digit((char) b[offset+i], 16) << shift;
        }
        return d;
    }

    public static void main(String[] args) throws Exception {
        AESUtils util = new AESUtils("abcdefghijklmnop"); // 密钥
        System.out.println("cardNo:"+util.encryptData("this is block 10this is block 10this is block 10")); // 加密
        System.out.println("exp:"+util.decryptData("34+Jzs4KkwaCQWVyyAgwLA==")); // 解密
    }
}
