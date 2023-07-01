package com.meishubao.sample.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meishubao.sample.mapper.UserMapper;
import com.meishubao.sample.model.entity.User;
import com.meishubao.sample.service.ServiceConstant;
import com.meishubao.sample.service.UserServiceC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
        user.setName(ServiceConstant.SAVE_USER_NAME0 + "C");
        this.save(user);
        try {
            userServiceImplD.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //throw new RuntimeException("DB异常");
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void saveUser1(User user) {
        user.setId(null);
        user.setName(ServiceConstant.SAVE_USER_NAME1 + "C");
        this.save(user);
        userServiceImplD.saveUser1(user);
        //try {
        //    userServiceImplD.saveUser1(user);
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        throw new RuntimeException("DB异常");
    }

}