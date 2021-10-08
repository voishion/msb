package com.alany.spider;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class SpidersApplication {

    public static void main(String[] args) throws InterruptedException, UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(SpidersApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        String name = env.getProperty("spring.application.name");

        if (StringUtils.isBlank(path)) {
            path = "";
        }

        int size = 15;
        StringBuilder builder = new StringBuilder("\n");

        builder.append("----------------------------------------------------------\n");
        builder.append("\t"+ name + "项目启动成功 *^_^* \n");
        builder.append("----------------------------------------------------------\n");
        builder.append("\tApplication is running! Access URLs:\n");
        builder.append(StringUtils.rightPad("\tLocal访问网址:", size) + "http://localhost:" + port + path + "\n");
        builder.append(StringUtils.rightPad("\tExternal访问网址:", size) + "http://" + ip + ":" + port + path + "\n");
        builder.append(StringUtils.rightPad("\tKnife4j文档网址:", size) + "http://" + ip + ":" + port + path + "doc.html" + "\n");
        builder.append("----------------------------------------------------------");
        log.info(builder.toString());

//        AsyncProcessTask asyncProcessTask = SpringContextHolder.getBean(AsyncProcessTask.class);
//        asyncProcessTask.initProxy();
//        Thread.sleep(1000 * 30);
//        asyncProcessTask.startProcessorsByBusiness(BizEnum.tabobao.getName());
    }

}
