package btvn.bai3;

interface PaymentMethod {}

interface CODPayable extends PaymentMethod {
    void processCOD(double amount);
}

interface CardPayable extends PaymentMethod {
    void processCard(double amount);
}

interface EWalletPayable extends PaymentMethod {
    void processMomo(double amount);
}

class CODPayment implements CODPayable {
    public void processCOD(double amount) {
        System.out.println("Xử lý thanh toán COD: " + (int)amount + " - Thành công");
    }
}

class CreditCardPayment implements CardPayable {
    public void processCard(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng: " + (int)amount + " - Thành công");
    }
}

class MomoPayment implements EWalletPayable {
    public void processMomo(double amount) {
        System.out.println("Xử lý thanh toán MoMo: " + (int)amount + " - Thành công");
    }
}

class PaymentProcessor {
    public void payCOD(CODPayable payment, double amount) {
        payment.processCOD(amount);
    }
    public void payCard(CardPayable payment, double amount) {
        payment.processCard(amount);
    }
    public void payMomo(EWalletPayable payment, double amount) {
        payment.processMomo(amount);
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        CODPayable cod = new CODPayment();
        processor.payCOD(cod, 500000);
        CardPayable card = new CreditCardPayment();
        processor.payCard(card, 1000000);
        EWalletPayable momo = new MomoPayment();
        processor.payMomo(momo, 750000);
        EWalletPayable anotherWallet = new MomoPayment();
        processor.payMomo(anotherWallet, 300000);
    }
}