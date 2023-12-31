package com.meishubao.java8.optional;

import lombok.Data;

import java.util.Optional;

/**
 * User
 *
 * @author biezhi
 * @date 2018/2/11
 */
@Data
public class User {

    private String  username;
    private String  password;
    private Integer age;
    private Address address;

    private Optional<Address> optAddress;

}
