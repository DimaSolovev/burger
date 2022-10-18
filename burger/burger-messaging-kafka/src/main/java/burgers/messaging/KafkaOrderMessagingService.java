package burgers.messaging;

import burgers.domain.BurgerOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Slf4j
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
    log.info("kafka save order {}",order);
    kafkaTemplate.send("burgertopic", order);
  }
  
}
