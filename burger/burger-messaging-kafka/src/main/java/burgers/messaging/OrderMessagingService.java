package burgers.messaging;

import burgers.domain.BurgerOrder;

public interface OrderMessagingService {

  void sendOrder(BurgerOrder order);
  
}
