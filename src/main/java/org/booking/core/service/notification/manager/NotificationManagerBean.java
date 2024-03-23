package org.booking.core.service.notification.manager;

import lombok.RequiredArgsConstructor;
import org.booking.core.domain.notification.DefaultNotificationDto;
import org.booking.core.service.notification.NotificationStrategy;
import org.booking.core.service.notification.data.MessageDataProcessor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationManagerBean implements NotificationManager{

	private final MessageDataProcessor<DefaultNotificationDto> dataProcessor;
	private final NotificationStrategy notificationStrategy;

	@Override
	public void sent(String message) {
		DefaultNotificationDto notificationDto = dataProcessor.execute(message, DefaultNotificationDto.class);
		notificationStrategy.apply(notificationDto);
	}



}
