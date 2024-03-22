package org.booking.core.domain.notification;

import java.util.List;

public interface NotificationDto {

	MetaInfoDto getMetaInfo();
	String getUuid();
	String getAction();
	List<ContactDto> getContacts();
}
