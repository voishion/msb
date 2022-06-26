package com.meishubao.sample.mockito.service;

import com.meishubao.sample.mockito.entity.User;

public interface RegistrationService {
    User register(String name, String phone) throws Exception;
}
