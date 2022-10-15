package burgers.kitchen.messaging.jms;

import burgers.BurgerOrder;
import burgers.kitchen.OrderReceiver;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Profile("jms-template")
@Component
public class JmsOrderReceiver implements OrderReceiver {

  private JmsTemplate jms;

  public JmsOrderReceiver(JmsTemplate jms) {
    this.jms = jms;
  }

  @Override
  public BurgerOrder receiveOrder() {
    return (BurgerOrder) jms.receiveAndConvert("tacocloud.order.queue");
  }

}
