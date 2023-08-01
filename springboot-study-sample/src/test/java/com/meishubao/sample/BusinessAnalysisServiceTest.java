package com.meishubao.sample;
//
//import cn.gt.hicip.HicipApplication;
//import cn.gt.hicip.entity.vo.HicipOrganizationVo;
//import cn.gt.hicip.entity.vo.UserInfoVo;
//import cn.gt.hicip.service.impl.AuthService;
//import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.doAnswer;
//import static org.mockito.Mockito.reset;
//
//@Slf4j
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = {HicipApplication.class})
public class BusinessAnalysisServiceTest {
//
//    @Autowired
//    BusinessAnalysisService businessAnalysisService;
//
//    @MockBean
//    AuthService authService;
//
//    @BeforeEach
//    void setUp() {
//        log.info("开始执行>>>>>>>>>>>>>>>>>>>>>>>");
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @AfterEach
//    void tearDown() {
//        log.info("执行完成<<<<<<<<<<<<<<<<<<<<<<<");
//        reset(authService);
//    }
//
//    @Test
//    public void getHicipOrganizationList() {
//        doAnswer(t -> null).when(authService).getUserInfo(Mockito.anyBoolean());
//        List<HicipOrganizationVo> list = businessAnalysisService.getHicipOrganizationList();
//        assertTrue(CollectionUtils.isEmpty(list));
//
//        reset(authService);
//        doAnswer(t -> new UserInfoVo()).when(authService).getUserInfo(Mockito.anyBoolean());
//        list = businessAnalysisService.getHicipOrganizationList();
//        assertTrue(CollectionUtils.isNotEmpty(list));
//
//        reset(authService);
//        doAnswer(t -> {
//            UserInfoVo vo = new UserInfoVo();
//            vo.setDataRole(1);
//            return vo;
//        }).when(authService).getUserInfo(Mockito.anyBoolean());
//        list = businessAnalysisService.getHicipOrganizationList();
//        assertTrue(CollectionUtils.isEmpty(list));
//
//        reset(authService);
//        doAnswer(t -> {
//            UserInfoVo vo = new UserInfoVo();
//            vo.setDataRole(1);
//            vo.getOrgCodeList().add("10");
//            vo.getOrgCodeList().add("12");
//            return vo;
//        }).when(authService).getUserInfo(Mockito.anyBoolean());
//        list = businessAnalysisService.getHicipOrganizationList();
//        assertTrue(CollectionUtils.isNotEmpty(list));
//    }
//
}