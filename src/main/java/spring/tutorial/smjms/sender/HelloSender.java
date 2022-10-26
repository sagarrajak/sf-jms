package spring.tutorial.smjms.sender;


import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.tutorial.smjms.config.JmsConfig;
import spring.tutorial.smjms.models.HelloWordMessage;

import java.util.UUID;

@Component
@AllArgsConstructor
public class HelloSender {
    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        HelloWordMessage message = HelloWordMessage
                                    .builder()
                                    .id(UUID.randomUUID()).message("simple message").build();
        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);
    }
}
