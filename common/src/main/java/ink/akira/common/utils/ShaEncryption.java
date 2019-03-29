package ink.akira.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by akira on 2019/3/29.
 * 加密算法
 */
public class ShaEncryption {
    private static final String SHA1 = "SHA-1";
    private static final int HASH_ITERATIONS = 1024;
    private static final int SALT_SIZE = 8;
    private static SecureRandom random = new SecureRandom();
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 生成随机的Byte[]作为salt.
     *
     * @param numBytes byte数组的大小
     */
    private static byte[] generateSalt(int numBytes) {
        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * 对字符串进行散列
     * @param input 字符串字节
     * @param salt 随机生成的盐，使散列结果不固定
     * @return 散列结果
     */
    private static byte[] digest(byte[] input, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(SHA1);
            if (salt != null) {
                md.update(salt);
            }
            byte[] result = md.digest(input);
            for (int i = 1; i < HASH_ITERATIONS; i++) {
                md.reset();
                result = md.digest(result);
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * encode，将字节数组转化字符数组，字符数组中元素由DIGITS_LOWER中元素组成
     * 字符数组元素这里用的是十六进制元素，因此字节数组中一个元素对应字符数组两个元素（均是一个字节，十六进制元素占4位，半个字节）
     * 数组大小变成两倍
     * @param data 字节数组
     * @return 字符数组
     * @see #decodeHex(char[])
     */
    private static char[] encodeHex(byte[] data) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_LOWER[0x0F & data[i]];
        }
        return out;
    }

    /**
     * decode， 将字符数组转化为字节数组，字符数组中元素由DIGITS_LOWER中元素组成
     * 字符数组元素这里用的是十六进制元素，因此字节数组中一个元素对应字符数组两个元素（均是一个字节，十六进制元素占4位，半个字节）
     * 数组大小变成两倍
     * 字符数组
     * @param data 字符数组
     * @return 字节数组
     * @see #encodeHex(byte[])
     */
    private static byte[] decodeHex(char[] data) {
        final int len = data.length;
        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }
        final byte[] out = new byte[len >> 1];
        for (int i = 0, j = 0; j < len; i++) {
            int f = Character.digit(data[j], 16) << 4;
            j++;
            f = f | Character.digit(data[j], 16);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }


    /**
     * 加密，由于使用了随机salt，同一明文密码每次加密后结果不一致
     * 加密后密码前 SALT_SIZE * 2 位即为salt（验证密码时，需将此salt取出后再次加密以对比两次结果）
     * @param plainPassword 明文密码
     * @return 加密后密码
     */
    public static String encryptPassword(String plainPassword) {
        byte[] salt = generateSalt(SALT_SIZE);
        byte[] hashPassword = digest(plainPassword.getBytes(), salt);
        return new String(encodeHex(salt)) + new String(encodeHex(hashPassword));
    }

    /**
     * 验证密码，由于使用了随机salt，同一明文密码每次加密后结果不一致
     * 加密后密码前 SALT_SIZE * 2 位即为salt（验证密码时，需将此salt取出后再次加密以对比两次结果）
     * @param plainPassword 明文密码
     * @param cipherPassword 其中一个加密后的密码
     * @return true 密码正确、验证通过；false 密码错误、验证未通过
     */
    public static boolean validatePassword(String plainPassword, String cipherPassword) {
        byte[] salt = decodeHex(cipherPassword.substring(0, SALT_SIZE << 1).toCharArray());
        byte[] hashPassword = digest(plainPassword.getBytes(), salt);
        String expectedPass = new String(encodeHex(salt)) + new String(encodeHex(hashPassword));
        return cipherPassword.equals(expectedPass);
    }

}
