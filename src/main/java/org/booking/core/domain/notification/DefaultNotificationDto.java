package org.booking.core.domain.notification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
@Builder
@Getter
@Setter
public class DefaultNotificationDto implements NotificationDto, Serializable {

	MetaInfoDto metaInfo;
	String uuid;
	String action;
	List<ContactDto> contacts;

}
