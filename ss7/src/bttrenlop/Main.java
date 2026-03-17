package bttrenlop;

// ==========================================
// 1. TẦNG TRỪU TƯỢNG (INTERFACES)
// ==========================================
interface OrderRepository {
    void save(Order order);
}

interface NotificationService {
    void send(String recipient, String message);
}

interface PaymentStrategy {
    void pay();
}

// ==========================================
// 2. MODEL & IMPLEMENTATIONS
// ==========================================
class Order {
    private String customerEmail;
    public Order(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
}

class DatabaseRepository implements OrderRepository {
    @Override
    public void save(Order order) {
        System.out.println("Đã lưu đơn hàng vào DB SQL.");
    }
}

class EmailNotification implements NotificationService {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Đã gửi Email tới: " + recipient + " | Nội dung: " + message);
    }
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("Đang kết nối API Ngân hàng... Thanh toán Thẻ tín dụng thành công.");
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("Đang mở cổng PayPal... Thanh toán PayPal thành công.");
    }
}

class MoMoPayment implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("Đang quét mã QR... Thanh toán MoMo thành công.");
    }
}

// ==========================================
// 3. CLASS ĐIỀU PHỐI (ORDER PROCESSOR)
// ==========================================
class OrderProcessor {
    private final OrderRepository repository;
    private final NotificationService notificationService;

    // Dependency Injection
    public OrderProcessor(OrderRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public void processOrder(Order order, PaymentStrategy paymentMethod) {
        repository.save(order);
        paymentMethod.pay();
        notificationService.send(order.getCustomerEmail(), "Đơn hàng của bạn đã được xử lý thành công!");
    }
}

// ==========================================
// 4. LỚP CHẠY CHƯƠNG TRÌNH (MAIN)
// ==========================================
public class Main {
    public static void main(String[] args) {
        System.out.println("=== HỆ THỐNG XỬ LÝ ĐƠN HÀNG TECHSHOP ===\n");

        OrderRepository db = new DatabaseRepository();
        NotificationService emailSender = new EmailNotification();
        OrderProcessor processor = new OrderProcessor(db, emailSender);

        Order order1 = new Order("khachhang_1@gmail.com");
        Order order2 = new Order("khachhang_2@gmail.com");

        System.out.println("-> Khách hàng 1 đặt hàng:");
        PaymentStrategy creditCard = new CreditCardPayment();
        processor.processOrder(order1, creditCard);

        System.out.println("\n----------------------------------\n");

        System.out.println("-> Khách hàng 2 đặt hàng:");
        PaymentStrategy momo = new MoMoPayment();
        processor.processOrder(order2, momo);
    }
}