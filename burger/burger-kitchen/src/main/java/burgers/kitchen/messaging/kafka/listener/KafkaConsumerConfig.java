package burgers.kitchen.messaging.kafka.listener;

import burgers.BurgerOrder;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
//    //@Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrapServers = "localhost:9092";
//
//    public Map<String, Object> consumerConfig() {
//        HashMap<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        return props;
//    }
//
//    @Bean
//    public ConsumerFactory<String, BurgerOrder> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(
//                consumerConfig(),
//                new StringDeserializer(),
//                new JsonDeserializer<>());
//    }
//
//    public KafkaListenerContainerFactory<
//            ConcurrentMessageListenerContainer<String, BurgerOrder>> factory(
//            ConsumerFactory<String, BurgerOrder> consumerFactory
//    ) {
//        ConcurrentKafkaListenerContainerFactory<String, BurgerOrder> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//        return factory;
//    }

}
