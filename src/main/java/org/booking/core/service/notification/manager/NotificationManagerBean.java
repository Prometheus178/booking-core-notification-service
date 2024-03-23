package org.booking.core.service.notification.manager;

import lombok.RequiredArgsConstructor;
import org.booking.core.domain.notification.DefaultNotificationDto;
import org.booking.core.service.notification.NotificationStrategy;
import org.booking.core.utils.JsonDecoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationManagerBean implements NotificationManager {

	private final JsonDecoder jsonDecoder;
	private final NotificationStrategy notificationStrategy;

	@Override
	public void sent(String message) {
		DefaultNotificationDto notificationDto = jsonDecoder.decode(message, DefaultNotificationDto.class);
		notificationStrategy.apply(notificationDto);
	}


}
