package com.meishubao.jsondoc;

import lombok.extern.slf4j.Slf4j;
import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * https://github.com/fabiomaffioletti/jsondoc-samples
 * @author lilu
 */
@Slf4j
@SpringBootApplication
@EnableJSONDoc
public class SpringbootJsondocStudyExampleApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(SpringbootJsondocStudyExampleApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        String name = env.getProperty("spring.application.name");

        if (StringUtils.hasText(path)) {
            path = "";
        }

        StringBuilder builder = new StringBuilder("\n");
        builder.append("----------------------------------------------------------\n");
        builder.append("\t"+ name + "项目启动成功 *^_^* \n");
        builder.append("----------------------------------------------------------\n");
        builder.append("\tApplication is running! Access URLs:\n");
        builder.append("\tjsondoc:" + "http://localhost:" + port + path + "/jsondoc-ui.html?url=/jsondoc#\n");
        builder.append("----------------------------------------------------------");
        log.info(builder.toString());
    }

}
