package com.meishubao.elk;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wildfly.common.Assert;

/**
 * <a href="https://mp.weixin.qq.com/s/zb4_-Q9tL4LiX9eu80ODmw">参考资料<a/>
 *
 * @author lilu
 */
@SpringBootTest
public class JasyptEncryptorTest {

    @Autowired
    private StringEncryptor encryptor;


    @Test
    public void encrypt() {
        String url = encryptor.encrypt("jdbc:mysql://localhost:3306/mydb?autoReconnect=true&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8");
        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("123456");
        String author = encryptor.encrypt("voishion");


        System.out.println("database url: " + url);
        System.out.println("database name: " + name);
        System.out.println("database password: " + password);
        System.out.println("elk author: " + author);
        Assert.assertTrue(url.length() > 0);
        Assert.assertTrue(name.length() > 0);
        Assert.assertTrue(password.length() > 0);
        Assert.assertTrue(author.length() > 0);
    }
}