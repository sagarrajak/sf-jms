package spring.tutorial.smjms.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import spring.tutorial.smjms.config.JmsConfig;
import spring.tutorial.smjms.models.HelloWordMessage;

import javax.jms.Message;

@Component
public class HelloMessageListener {
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWordMessage helloWordMessage , @Headers MessageHeaders headers, Message message) {
        System.out.println(helloWordMessage);
        System.out.println("Got new message");
//        throw new RuntimeException("sddsdsd");
    }
}
