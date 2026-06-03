package com.uditray.enterprise.service;

import com.uditray.enterprise.dto.NotificationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendNotification(String message) {

        System.out.println(
                "WEBSOCKET NOTIFICATION SENT: "
                        + message
        );

        messagingTemplate.convertAndSend(
                "/topic/notifications",
                new NotificationMessage(message)
        );
    }
}

