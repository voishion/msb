package com.meishubao.java8.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.*;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import org.w3c.dom.Document;

import javax.xml.xpath.XPathConstants;
import java.math.BigDecimal;
import java.security.KeyPair;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author lilu
 */
public class HutoolTest {

    public static void main(String[] args) {
        //dateUtilTest();
        //xmlUtilTest();
        //sm2Test();
        //convertTest();
        convertTest1();
    }

    public static void convertTest1() {
        BigDecimal before = new BigDecimal("6429.12").add(new BigDecimal("1797.31"));
        //BigDecimal after = new BigDecimal("6035.83").add(new BigDecimal("2141.85"));
        BigDecimal after = new BigDecimal("6035.83").add(new BigDecimal("1797.31").subtract(new BigDecimal("150.79")));
        System.out.println(before);
        System.out.println(after);
        System.out.println(before.subtract(after));

        System.out.println(new BigDecimal("2292.64").subtract(new BigDecimal("2141.85")));
    }

    public static void convertTest() {
        long time = 4535345;
        long minutes = Convert.convertTime(time, TimeUnit.MILLISECONDS, TimeUnit.MINUTES);
        System.out.println(StrUtil.format("时间单位转换=>{}毫秒={}分钟", time, minutes));

        double money = 67556.32;
        String digitUppercase = Convert.digitToChinese(money);
        System.out.println(StrUtil.format("金额大小写转换=>{}元={}", money, digitUppercase));
    }

    private static void sm2Test() {
        String text = "你好啊，！@#￥%……&15327sdfghjQWERTYJK23453456789！@#￥%……&*（1234567!@#$%^&*(";

        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        String privateKey = Base64.encode(pair.getPrivate().getEncoded());
        String publicKey = Base64.encode(pair.getPublic().getEncoded());
        System.out.println("privateKey=>\n" + privateKey);
        System.out.println("publicKey=>\n" + publicKey);

        SM2 encryptSm2 = SmUtil.sm2(null, publicKey);
        // 公钥加密
        String encryptStr = Base64.encode(encryptSm2.encrypt(text, KeyType.PublicKey));
        System.out.println("encryptStr=>\n" + encryptStr);

        // 私钥解密
        SM2 decryptSm2 = SmUtil.sm2(privateKey, null);
        String decryptStr = decryptSm2.decryptStr(encryptStr, KeyType.PrivateKey);
        System.out.println("decryptStr=>\n" + decryptStr);

        // 结果
        System.out.println("result=>" + text.equals(decryptStr));
    }

    private static void xmlUtilTest() {
        // => Xpath操作
        //xmlUtilTest1();
        xmlUtilTest2WeixinCallback();
    }

    private static void xmlUtilTest1() {
        String xmlFile = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "\n" +
                "<returnsms> \n" +
                "  <returnstatus>Success（成功）</returnstatus>  \n" +
                "  <message>ok</message>  \n" +
                "  <remainpoint>1490</remainpoint>  \n" +
                "  <taskID>885</taskID>  \n" +
                "  <successCounts>1</successCounts> \n" +
                "</returnsms>\n";
        Document docResult= XmlUtil.readXML(xmlFile);
        //结果为“ok”
        Object value = XmlUtil.getByXPath("//returnsms/message", docResult, XPathConstants.STRING);
        println(value);
    }

    private static void xmlUtilTest2WeixinCallback() {
        String xmlFile = "<xml>     \n" +
                "<ToUserName>gh_abcdefg</ToUserName> \n" +
                "<FromUserName>oABCD</FromUserName>      \n" +
                "<CreateTime>12344555555</CreateTime>     \n" +
                "<MsgType>event</MsgType>      \n" +
                "<Event>open_product_order_cancel</Event>\n" +
                "<order_info>\n" +
                "     <out_order_id>123456</out_order_id>\n" +
                "     <order_id>1234567</order_id>\n" +
                "</order_info>\n" +
                "</xml>\n" +
                "\n";
        Document docResult= XmlUtil.readXML(xmlFile);
        //结果为“ok”
        Object value = XmlUtil.getByXPath("//xml/order_info/order_id", docResult, XPathConstants.STRING);
        println(value);
    }

    private static void dateUtilTest() {
        // => 开始和结束时间
        dateUtilTest1();
        // => 日期时间偏移
        dateUtilTest2();
        // => 日期时间差
        dateUtilTest3();
        // => 格式化时间差
        dateUtilTest4();
        // => 星座和属相
        dateUtilTest5();
        // => 其他
        dateUtilTest6();
    }

    private static void dateUtilTest6() {
        //年龄
        println(DateUtil.ageOfNow("1990-01-30"));
        //是否闰年
        println(DateUtil.isLeapYear(2017));
    }

    private static void dateUtilTest5() {
        // "摩羯座"
        String zodiac = DateUtil.getZodiac(Month.JANUARY.getValue(), 19);
        println(zodiac);
        // "狗"
        String chineseZodiac = DateUtil.getChineseZodiac(1994);
        println(chineseZodiac);
    }

    private static void dateUtilTest4() {
        //Level.MINUTE表示精确到分
        String formatBetween = DateUtil.formatBetween(60 * 2 * 1000, BetweenFormatter.Level.MINUTE);
        //输出：31天1小时
        println(formatBetween);
    }

    private static void dateUtilTest3() {
        String dateStr1 = "2017-03-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2017-04-01 23:33:23";
        Date date2 = DateUtil.parse(dateStr2);

        //相差一个月，31天
        long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);
        println(betweenDay);
    }

    private static void dateUtilTest2() {
        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);
        //结果：2017-03-03 22:33:23
        Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);
        //常用偏移，结果：2017-03-04 22:33:23
        DateTime newDate2 = DateUtil.offsetDay(date, 3);
        //常用偏移，结果：2017-03-01 19:33:23
        DateTime newDate3 = DateUtil.offsetHour(date, -3);

        //昨天
        println(DateUtil.yesterday());
        //明天
        println(DateUtil.tomorrow());
        //上周
        println(DateUtil.lastWeek());
        //下周
        println(DateUtil.nextWeek());
        //上个月
        println(DateUtil.lastMonth());
        //下个月
        println(DateUtil.nextMonth());
    }

    private static void dateUtilTest1() {
        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);
        //一天的开始，结果：2017-03-01 00:00:00
        Date beginOfDay = DateUtil.beginOfDay(date);
        println(beginOfDay);
        //一天的结束，结果：2017-03-01 23:59:59
        Date endOfDay = DateUtil.endOfDay(date);
        println(endOfDay);
    }

    private static void println(Object object) {
        System.out.println(object);
    }

}
