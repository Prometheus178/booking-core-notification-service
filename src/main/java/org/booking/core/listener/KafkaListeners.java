package org.booking.core.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.booking.core.service.NotificationManager;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Log
@Component
public class KafkaListeners {

	private final NotificationManager notificationManager;

	@KafkaListener(topics = "booking-core-topic",
			groupId = "notification-service"

	)
	void listener(String message){
		log.info(message);
		notificationManager.sent(message);
	}
}
