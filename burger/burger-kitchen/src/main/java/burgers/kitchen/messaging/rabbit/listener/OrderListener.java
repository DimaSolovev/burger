package burgers.kitchen.messaging.rabbit.listener;

import burgers.BurgerOrder;
import burgers.kitchen.KitchenUI;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("rabbitmq-listener")
@Component
public class OrderListener {

  private KitchenUI ui;

  @Autowired
  public OrderListener(KitchenUI ui) {
    this.ui = ui;
  }

  @RabbitListener(queues = "tacocloud.order.queue")
  public void receiveOrder(BurgerOrder order) {
    ui.displayOrder(order);
  }

}
