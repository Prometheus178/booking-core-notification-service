package org.booking.core.service.notification.wsocket;

import lombok.extern.java.Log;
import org.booking.core.constant.NotificationType;
import org.booking.core.domain.notification.NotificationDto;
import org.booking.core.service.notification.NotificationService;
import org.springframework.stereotype.Service;

@Log
@Service(value = NotificationType.WEB_SOCKET)
public class WebSocketNotificationService implements NotificationService {

	@Override
	public void sent(NotificationDto notificationDto) {
		log.info("From: " + NotificationType.WEB_SOCKET);
		log.info(notificationDto.toString());
	}
}
