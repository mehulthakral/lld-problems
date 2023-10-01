package observable;

import observer.NotificationAlert;

import java.util.ArrayList;
import java.util.List;

public class IphoneStock implements ItemStock {

    List<NotificationAlert> notificationAlerts = new ArrayList<>();
    int stock = 0;

    @Override
    public void add(NotificationAlert alert) {
        notificationAlerts.add(alert);
    }

    @Override
    public void remove(NotificationAlert alert) {
        notificationAlerts.remove(alert);
    }

    @Override
    public void notifyAlerts() {
        for (NotificationAlert alert: notificationAlerts) {
            alert.update();
        }
    }

    @Override
    public void setStock(int stockAmount) {
        int prevStock = stock;
        stock = stockAmount;
        if (prevStock == 0) {
            notifyAlerts();
        }
    }

    @Override
    public int getStock() {
        return stock;
    }

    @Override
    public String getItemName() {
        return "iphone";
    }
}
