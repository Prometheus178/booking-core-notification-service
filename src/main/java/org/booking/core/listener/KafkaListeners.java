package org.booking.core.listener;

import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log
@Component
public class KafkaListeners {

	@KafkaListener(topics = "booking-core-topic",
			groupId = "notification-service"

	)
	void listener(String data){
		log.info(data);
	}
}
