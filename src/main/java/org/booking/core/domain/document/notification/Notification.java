package org.booking.core.domain.document.notification;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document()
@ToString
public class Notification implements Serializable {

	@Id
	private String id;
	private LocalDateTime createdAt = LocalDateTime.now();

	private NotificationChannel notificationChannel;
	private String uuid;
	private String action;
	private List<Contact> contacts;
}
