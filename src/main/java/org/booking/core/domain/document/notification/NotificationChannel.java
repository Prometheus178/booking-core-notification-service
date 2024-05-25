package org.booking.core.domain.document.notification;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationChannel implements Serializable {

	private String sender;
	private String receiver;
	private List<String> notifyBy;

}
