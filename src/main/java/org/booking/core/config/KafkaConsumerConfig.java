package org.booking.core.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.TopicBuilder;

import static org.booking.core.config.NotificationTopology.NOTIFICATION_IN_0;

@Configuration
@EnableKafkaStreams
public class KafkaConsumerConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public NewTopic topicBuilder() {
		return TopicBuilder.name(NOTIFICATION_IN_0)
				.partitions(2)
				.replicas(1)
				.build();
	}
}
