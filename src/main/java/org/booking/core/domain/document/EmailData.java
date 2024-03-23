package org.booking.core.domain.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("email_datas")
@Getter
@Setter
public class EmailData implements Serializable {

	private String subject;
	private String text;
}
