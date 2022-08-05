import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

// java版aes-cbc-pkcs7加密解密hex字符串输入输出

public class MyClass {

    public static void main(String args[]) {
        String data = "this is Aes encryption with pkcs7";//加密的内容
        String key = "1111111111111111"; //16位字符串密钥
        String iv = "2222222222222222";//16位字符串偏移
        //加密数据
        String encodeResult = encrypt(data, key, iv);
        System.out.println(String.format("aes-cbc-128 ->  %s", encodeResult));

        //解密数据
        String decodeResult = decrypt(encodeResult, key, iv);
        System.out.println(String.format("decode: -> %s", decodeResult));

    }

    /**
     * 加密数据
     *
     * @param data 明文内容
     * @param key  密钥
     * @param iv   偏移
     * @return
     */
    private static String encrypt(String data, String key, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES"), new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
            byte[] result = cipher.doFinal(pkcs7padding(data, cipher.getBlockSize())); // 好气啊, 要自己手动实现 PKCS7Padding 填充
            return new String(Hex.encodeHex(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "NULL";
    }

    /**
     * 解密数据
     *
     * @param data 加密内容
     * @param key  密钥
     * @param iv   偏移
     * @return
     */
    private static String decrypt(String data, String key, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES"), new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
            byte[] result = cipher.doFinal(Hex.decodeHex(data.toCharArray()));
            return new String(result, StandardCharsets.UTF_8).trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "NULL";
    }

    /**
     * pkcs7填充
     *
     * @param content
     * @param blockSize
     * @return
     */
    private static byte[] pkcs7padding(String content, int blockSize) {
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        int pad = blockSize - (contentBytes.length % blockSize); // 计算需要补位的长度
        byte padChr = (byte) pad; // 补位字符 (即用补位长度)
        byte[] paddedBytes = new byte[contentBytes.length + pad]; // 在原有的长度上加上补位长度
        System.arraycopy(contentBytes, 0, paddedBytes, 0, contentBytes.length); // 原有的先复制过去
        for (int i = contentBytes.length; i < paddedBytes.length; i++) { // 补位字符填充
            paddedBytes[i] = padChr;
        }
        return paddedBytes;
    }
}