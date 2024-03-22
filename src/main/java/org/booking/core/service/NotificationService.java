package org.booking.core.service;

import org.booking.core.domain.notification.NotificationDto;

public interface NotificationService {

	void sent(NotificationDto notificationDto);

}
