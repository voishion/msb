package com.meishubao.sample.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meishubao.sample.mapper.UserMapper;
import com.meishubao.sample.model.entity.User;
import com.meishubao.sample.service.ServiceConstant;
import com.meishubao.sample.service.UserServiceC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImplC extends ServiceImpl<UserMapper, User> implements UserServiceC {

    private final UserServiceImplD userServiceImplD;

    @Override
    // 会回滚
    @Transactional
    // 不会回滚
    //@Transactional(noRollbackFor = RuntimeException.class)
    public void saveUser(User user) {
        user.setId(null);
        user.setName(ServiceConstant.SAVE_USER_NAME + "C");
        this.save(user);
        userServiceImplD.saveUser(user);
        throw new RuntimeException("DB异常");
    }

}