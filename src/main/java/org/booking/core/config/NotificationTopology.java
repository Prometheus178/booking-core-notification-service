package org.booking.core.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.booking.core.domain.document.notification.Notification;
import org.booking.core.repository.notification.NotificationRepository;
import org.booking.core.service.notification.email.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationTopology {

	public static final String NOTIFICATION_IN_0 = "notification-in-0";

	private final EmailNotificationService emailNotificationService;
	private final NotificationRepository notificationRepository;

	@Autowired
	public void process(StreamsBuilder streamsBuilder) {
		KStream<String, Notification> stream = streamsBuilder.stream(NOTIFICATION_IN_0,
				Consumed.with(Serdes.String(), new JsonSerde<>(Notification.class)));

		stream.peek((key, value) -> {
			notificationRepository.save(value);

			if (value.getNotificationChannel() == null || value.getNotificationChannel().getNotifyBy() == null) {
				log.warn("NotifyBy is missing in the message: " + value.getUuid());
			} else {
				List<String> notifyByList = value.getNotificationChannel().getNotifyBy();
				if (notifyByList.contains("EMAIL")) {
					emailNotificationService.sent(value);
				} else {
					log.info("NotifyBy types are: {}", notifyByList);
				}
			}
		});

	}
}
