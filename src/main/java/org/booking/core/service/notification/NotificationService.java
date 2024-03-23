package org.booking.core.service.notification;

import org.booking.core.domain.notification.NotificationDto;

public interface NotificationService {

	void sent(NotificationDto notificationDto);

}
