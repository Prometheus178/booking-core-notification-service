package org.booking.core.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EmailData implements Serializable {

	private String subject;
	private String text;
}
