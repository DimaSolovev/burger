package burgers.kitchen.messaging.kafka.listener;

import burgers.BurgerOrder;
import burgers.kitchen.KitchenUI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Profile("kafka-listener")
@Component
@Slf4j
public class OrderListener {

    private KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;

    }

    @KafkaListener(topics = "burgertopic")
    public void handle(BurgerOrder order, ConsumerRecord<String, BurgerOrder> record) {
        log.info("Received from partition {} with timestamp {}",
                record.partition(), record.timestamp());

        ui.displayOrder(order);
    }

//
// Alternate implementation
//

//  @KafkaListener(topics="burgertopic")
//  public void handle(BurgerOrder order, Message<BurgerOrder> message) {
//    MessageHeaders headers = message.getHeaders();
//    log.info("Received from partition {} with timestamp {}",
//        headers.get(KafkaHeaders.RECEIVED_PARTITION_ID),
//        headers.get(KafkaHeaders.RECEIVED_TIMESTAMP));
//    ui.displayOrder(order);
//  }


}
