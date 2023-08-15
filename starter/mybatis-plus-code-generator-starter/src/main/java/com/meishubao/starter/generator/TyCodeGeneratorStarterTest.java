package com.meishubao.starter.generator;

public class TyCodeGeneratorStarterTest {

    public static void main(String[] args) {
        String url = "jdbc:mysql://10.152.160.66:59990/face_rep?serverTimezone=GMT%2B8&tinyInt1isBit=false&useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
        String username = "db_admin";
        String password = "osOPT9HYpL_D";
        new TyCodeGeneratorStarter(url, username, password).run();
    }

}