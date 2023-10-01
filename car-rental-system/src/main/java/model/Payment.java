package model;

import lombok.Builder;

@Builder
public class Payment {

    Bill bill;

    public void payBill() {
        bill.setPaid(Boolean.TRUE);
        System.out.println("Bill amount " + bill.getAmount() + " successfully paid!");
    }

}
