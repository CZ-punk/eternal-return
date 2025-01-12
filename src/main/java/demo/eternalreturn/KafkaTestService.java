package demo.eternalreturn;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaTestService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishMessage() {
        kafkaTemplate.send()
    }
    @Bean
    public KafkaAdmin.NewTopics newTopics() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder
                        .name("my_topic")
                        .partitions(3)
                        .replicas(1)
                        .build()
        );
    }
}
