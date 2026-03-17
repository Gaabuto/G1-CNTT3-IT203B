package btvn.bai4;
import java.util.*;

class Order {
    String id;
    public Order(String id) {
        this.id = id;
    }
}

interface Order {
    void save(Order Order);
    List<Order> findAll();
}

class FileOrderRepository implements Order {
    public void save(Order Order) {
        System.out.println("Lưu đơn hàng vào file: " + Order.id);
    }
    public List<Order> findAll() {
        return new ArrayList<>();
    }
}

class DatabaseOrderRepository implements Order {
    public void save(Order Order) {
        System.out.println("Lưu đơn hàng vào database: " + Order.id);
    }
    public List<Order> findAll() {
        return new ArrayList<>();
    }
}

interface NotificationService {
    void send(String message, String recipient);
}

class EmailService4 implements NotificationService {
    public void send(String message, String recipient) {
        System.out.println("Gửi email: " + message);
    }
}

class SMSNotification implements NotificationService {
    public void send(String message, String recipient) {
        System.out.println("Gửi SMS: " + message);
    }
}

class OrderService {
    private Order orderRepository4;
    private NotificationService notificationService;
    public OrderService(Order orderRepository4, NotificationService notificationService) {
        this.orderRepository4 = orderRepository4;
        this.notificationService = notificationService;
    }
    public void createOrder(Order Order) {
        orderRepository4.save(Order);
        notificationService.send("Đơn hàng " + Order.id + " đã được tạo", "customer");
    }
}

public class Main {
    public static void main(String[] args) {
        Order repo1 = new FileOrderRepository();
        NotificationService notify1 = new EmailService4();
        OrderService service1 = new OrderService(repo1, notify1);
        Order order1 = new Order("ORD001");
        service1.createOrder(order1);
        System.out.println();
        Order repo2 = new DatabaseOrderRepository();
        NotificationService notify2 = new SMSNotification();
        OrderService service2 = new OrderService(repo2, notify2);
        Order order2 = new Order("ORD002");
        service2.createOrder(order2);
    }
}