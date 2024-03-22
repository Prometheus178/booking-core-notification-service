package org.booking.core.service;

import lombok.extern.java.Log;
import org.booking.core.domain.notification.NotificationDto;
import org.booking.core.constant.NotificationType;
import org.springframework.stereotype.Service;

@Log
@Service(value = NotificationType.EMAIL)
public class EmailNotificationService implements NotificationService {

	@Override
	public void sent(NotificationDto notificationDto) {
		log.info(notificationDto.toString());

	}
}
