package info.startupbuilder.spring.websocket;

import lombok.Data;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Controller
public class ChatController {

    @Data
    public static class Message {
        String sender;
        String text;
    }

    @Value
    public static class MessageReceipt {
        String id;
        LocalDateTime timestamp;
        Message message;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public MessageReceipt send(Message message) {
        log.info("Message Received: {}", message);

        return new MessageReceipt(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                message
        );
    }
}
