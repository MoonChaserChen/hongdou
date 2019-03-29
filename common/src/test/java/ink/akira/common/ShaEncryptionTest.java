package ink.akira.common;

import ink.akira.common.utils.ShaEncryption;
import org.junit.Test;

/**
 * Created by akira on 2019/3/29.
 */
public class ShaEncryptionTest {
    @Test
    public void testEncrypt(){
        String pwd = "abcd";
        System.out.println(ShaEncryption.encryptPassword(pwd));
        System.out.println(ShaEncryption.encryptPassword(pwd));
        System.out.println(ShaEncryption.validatePassword(pwd, "4bf508505f68eff1ff044f5130137598a5d6118147bb7bbbd93f4fd2"));
        pwd = "陈";
        System.out.println(ShaEncryption.encryptPassword(pwd));
        System.out.println(ShaEncryption.encryptPassword(pwd));
        System.out.println(ShaEncryption.validatePassword(pwd, "43c5d36c698042a7f1af8d4b117f608a4e129b7201f79273d4ddc805"));
    }
}
