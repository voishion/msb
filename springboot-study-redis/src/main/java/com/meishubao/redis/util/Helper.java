package com.meishubao.redis.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

/**
 * @author lilu
 */
public class Helper {

    private static final String AES_KEY = "09bcf0d6eba5f100833b2d5922bdbbb3";

    private static final SymmetricCrypto AES;

    static {
        AES = new SymmetricCrypto(SymmetricAlgorithm.AES, HexUtil.decodeHex(AES_KEY));
    }

    /**
     * AES加密
     *
     * @param content 明文内容
     * @return 密文内容
     */
    public static String aesEncrypt(String content) {
        return AES.encryptHex(content);
    }

    /**
     * AES解密
     *
     * @param content 密文内容
     * @return 明文内容
     */
    public static String aesDecrypt(String content) {
        return AES.decryptStr(content, CharsetUtil.CHARSET_UTF_8);
    }

}
