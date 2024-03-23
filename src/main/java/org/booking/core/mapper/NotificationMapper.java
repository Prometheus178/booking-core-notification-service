package org.booking.core.mapper;

import org.booking.core.domain.document.notification.Notification;
import org.booking.core.domain.notification.DefaultNotificationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface NotificationMapper {

	NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);


	Notification toEntity(DefaultNotificationDto dto);

}
