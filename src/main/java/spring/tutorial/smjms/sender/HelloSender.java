package spring.tutorial.smjms.sender;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.tutorial.smjms.config.JmsConfig;
import spring.tutorial.smjms.models.HelloWordMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Objects;
import java.util.UUID;

@Component
@AllArgsConstructor
public class HelloSender {
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        HelloWordMessage message = HelloWordMessage
                                    .builder()
                                    .id(UUID.randomUUID()).message("simple message").build();
        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);
    }

//    @Scheduled(fixedRate = 10000)
//    public void sendAndReceiveMessage() throws JMSException {
//        HelloWordMessage message = HelloWordMessage
//                .builder()
//                .id(UUID.randomUUID()).message("send and waiting to receive").build();
//        Message receive = jmsTemplate.sendAndReceive(JmsConfig.SEND_RECEIVE_QUEUE, new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                TextMessage textMessage = null;
//                try {
//                    textMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
//                    textMessage.setStringProperty("_type", "spring.tutorial.smjms.models.HelloWordMessage");
//                    return textMessage;
//                } catch (JsonProcessingException e) {
//                    throw new JMSException(e.getMessage());
//                }
//            }
//        });
//        System.out.println(receive.getBody(String.class));
//    }
}
