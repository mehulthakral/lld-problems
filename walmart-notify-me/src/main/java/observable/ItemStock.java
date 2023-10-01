package observable;

import observer.NotificationAlert;

public interface ItemStock {

    void add(NotificationAlert alert);
    void remove(NotificationAlert alert);
    void notifyAlerts();
    void setStock(int stockAmount);
    int getStock();

    String getItemName();

}
