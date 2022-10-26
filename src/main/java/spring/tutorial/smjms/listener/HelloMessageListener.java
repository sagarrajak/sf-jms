package spring.tutorial.smjms.listener;

import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import spring.tutorial.smjms.config.JmsConfig;
import spring.tutorial.smjms.models.HelloWordMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@AllArgsConstructor
@Component
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWordMessage helloWordMessage , @Headers MessageHeaders headers, Message message) {
//        System.out.println(helloWordMessage);
//        System.out.println("Got new message");
//        throw new RuntimeException("sddsdsd");
    }

    @JmsListener(destination = JmsConfig.SEND_RECEIVE_QUEUE)
    public void listenForHellow(@Payload HelloWordMessage helloWordMessage , @Headers MessageHeaders headers, Message message) throws JMSException {
//        System.out.println("simple message received!");
        System.out.println(helloWordMessage);
        HelloWordMessage messageRepl = HelloWordMessage
                .builder()
                .id(UUID.randomUUID()).message("simple message received sending ack!").build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), messageRepl);
    }
}
