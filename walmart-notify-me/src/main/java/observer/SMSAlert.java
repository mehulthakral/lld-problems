package observer;

import observable.ItemStock;

public class SMSAlert implements NotificationAlert {

    ItemStock stockObservable;

    public SMSAlert(ItemStock stockObservable) {
        this.stockObservable = stockObservable;
    }

    @Override
    public void update() {
        sendSMS("Stock updated for " + stockObservable.getItemName() + " Only few items left: "
                + stockObservable.getStock());
    }

    private void sendSMS(String msg) {
        System.out.println("SMS Sent: Msg - " + msg);
    }
}
