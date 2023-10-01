package observer;

import observable.ItemStock;

public class EmailAlert implements NotificationAlert {

    ItemStock stockObservable;

    public EmailAlert(ItemStock stockObservable) {
        this.stockObservable = stockObservable;
    }

    @Override
    public void update() {
        sendEmail("Stock updated for " + stockObservable.getItemName() + " Only few items left: "
                + stockObservable.getStock());
    }

    private void sendEmail(String msg) {
        System.out.println("Email sent: Msg - " + msg);
    }
}
