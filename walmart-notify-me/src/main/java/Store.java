import observable.IphoneStock;
import observable.ItemStock;
import observer.EmailAlert;
import observer.NotificationAlert;
import observer.SMSAlert;

public class Store {
    public static void main(String[] args) {
        ItemStock iphoneStockObservable = new IphoneStock();

        NotificationAlert emailAlertObserver = new EmailAlert(iphoneStockObservable);
        NotificationAlert smsAlertObserver = new SMSAlert(iphoneStockObservable);

        iphoneStockObservable.add(emailAlertObserver);
        iphoneStockObservable.add(smsAlertObserver);

        iphoneStockObservable.setStock(10);
        iphoneStockObservable.setStock(0);
        iphoneStockObservable.setStock(100);

    }
}
