package com.meishubao.cloud.stream.kafka;

import com.meishubao.cloud.stream.kafka.bean.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@Log4j2
// 生产者, 消费者
@EnableBinding({Source.class, Sink.class})
@RestController
@RequiredArgsConstructor
public class SpringbootStudyCloudStreamKafkaApplication {

    private final MessageChannel output;

    @PostMapping("/publishEvent")
    public Book publishEvent(@RequestBody Book book) {
        output.send(MessageBuilder.withPayload(book).build());
        return book;
    }

    @StreamListener(value = "input")
    public void consumeMessage(Book book) {
        log.info("consumeMessage:{}", book);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStudyCloudStreamKafkaApplication.class, args);
    }

}
