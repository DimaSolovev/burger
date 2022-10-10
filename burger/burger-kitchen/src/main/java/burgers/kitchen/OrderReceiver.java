package burgers.kitchen;

import burgers.BurgerOrder;

public interface OrderReceiver {
  BurgerOrder receiveOrder();

}