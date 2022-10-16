package burgers.kitchen.messaging.rabbit;

import burgers.BurgerOrder;
import burgers.kitchen.OrderReceiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("rabbitmq-template")
@Component("templateOrderReceiver")
public class RabbitOrderReceiver implements OrderReceiver {

    private RabbitTemplate rabbit;

    public RabbitOrderReceiver(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    public BurgerOrder receiveOrder() {
        return (BurgerOrder) rabbit.receiveAndConvert("tacocloud.order.queue");
    }

}
