package org.booking.core.repository;

import org.booking.core.domain.entity.EmailData;
import org.springframework.stereotype.Repository;

@Repository
public class ActionMongoRepository implements ActionRepository {

	@Override
	public EmailData get(String action) {
		EmailData emailData = new EmailData();
		emailData.setSubject("test subject");
		emailData.setText("test");
		return emailData;
	}
}
