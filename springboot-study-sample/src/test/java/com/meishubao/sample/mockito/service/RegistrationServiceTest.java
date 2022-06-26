package com.meishubao.sample.mockito.service;

import com.meishubao.sample.mockito.dao.SalesDao;
import com.meishubao.sample.mockito.dao.UserDao;
import com.meishubao.sample.mockito.entity.User;
import com.meishubao.sample.mockito.exception.DAOException;
import com.meishubao.sample.mockito.exception.ValidationException;
import com.meishubao.sample.mockito.service.Impl.RegistrationServiceImpl;
import com.meishubao.sample.mockito.util.FindUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

class RegistrationServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private SalesDao salesDao;

    @Spy
    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(userDao);
        reset(salesDao);
    }

    @Test
    void register() throws Exception {
        String name = null;
        String phone = "15071271412";
        try {
            registrationService.register(name, phone);
            fail("这里会挂掉");
        } catch (Exception e) {
            assertThat(e, instanceOf(ValidationException.class));
        }

        name = "一直游到海水变蓝";
        phone = null;
        try {
            registrationService.register(name, phone);
            fail("这里会挂掉");
        } catch (Exception e) {
            assertThat(e, instanceOf(ValidationException.class));
        }

        phone = "15071271412";
        MockedStatic<FindUtils> staticService = mockStatic(FindUtils.class);
        staticService.when(() -> FindUtils.getAreaCode("15071271412")).thenReturn("a");
        staticService.when(() -> FindUtils.getOperatorCode("15071271412")).thenReturn("b");
        // 1.数据库操作正常
        when(salesDao.findRep("a","b")).thenCallRealMethod();
        when(userDao.save(name, phone, "Echo")).thenCallRealMethod();
        User user = registrationService.register(name, phone);
        assertThat(user.getRepId(), equalTo("Echo"));

        // 2.数据库操作异常
        when(userDao.save(name, phone, "Echo")).thenThrow(new SQLException());
        try {
            registrationService.register(name, phone);
        } catch (Exception e) {
            assertThat(e, instanceOf(DAOException.class));
        }
    }

}