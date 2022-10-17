package burgers.messaging;

import burgers.domain.BurgerOrder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Properties;


@Service
public class KafkaOrderMessagingService implements OrderMessagingService {
  
  private KafkaTemplate<String, BurgerOrder> kafkaTemplate;
  
  @Autowired
  public KafkaOrderMessagingService(
          KafkaTemplate<String, BurgerOrder> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }
  
  @Override
  public void sendOrder(BurgerOrder order) {
    kafkaTemplate.send("burgertopic", order);
  }
  
}
