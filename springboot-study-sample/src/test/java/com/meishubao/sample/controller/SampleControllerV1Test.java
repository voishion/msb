package com.meishubao.sample.controller;

import com.meishubao.sample.model.entity.User;
import com.meishubao.sample.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class SampleControllerV1Test {

    @Mock
    private UserService userService;

    @BeforeClass
    public static void pub() {
        System.out.println("pub");
    }

    @Before
    public void setUp() {
        System.out.println("setUp");
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() {
        System.out.println("tearDown");
        reset(userService);
    }

    @Test
    public void testUserSelectSuccess() {
        User user = User.builder().id(1L).name("lilu").password("123456").build();
        when(userService.getById(anyInt())).thenReturn(user);
        User result = userService.getById(1);
        assertThat(result.getName(), equalTo("lilu"));
    }

    @Test
    public void testUserSelectFailure() {
        User user = User.builder().id(1L).name("lilu").password("123456").build();
        doAnswer((Answer<User>) invocation -> user).when(userService).getById(1);
        User result = userService.getById(2);
        assertThat(result.getName(), equalTo("lilu"));
    }

    @Test
    public void testUserSelectException() {
        when(userService.getById(anyInt())).thenThrow(new RuntimeException("System EXEC"));
        User result = userService.getById(2);
        assertThat(result.getName(), equalTo("lilu"));
    }

}