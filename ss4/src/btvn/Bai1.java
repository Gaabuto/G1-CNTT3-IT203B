import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/* ================= ENUM ================= */

enum OrderStatus {
    PENDING,
    PAID,
    CANCELLED
}

/* ================= EXCEPTION ================= */

class InsufficientStockException extends Exception {
    public InsufficientStockException(String msg) {
        super(msg);
    }
}

class InvalidOrderIdException extends Exception {
    public InvalidOrderIdException(String msg) {
        super(msg);
    }
}

/* ================= MODEL ================= */

abstract class MenuItem {

    private String id;
    private String name;
    private double basePrice;
    private int stockQuantity;

    public MenuItem(String id,String name,double basePrice,int stockQuantity){
        this.id=id;
        this.name=name;
        this.basePrice=basePrice;
        this.stockQuantity=stockQuantity;
    }

    public String getId(){ return id; }
    public String getName(){ return name; }
    public double getBasePrice(){ return basePrice; }
    public int getStockQuantity(){ return stockQuantity; }

    public void reduceStock(int quantity) throws InsufficientStockException {

        if(quantity > stockQuantity)
            throw new InsufficientStockException("Not enough stock");

        stockQuantity -= quantity;
    }

    public abstract double calculatePrice();
}

/* ================= FOOD ================= */

class Food extends MenuItem {

    private boolean isVegan;

    public Food(String id,String name,double price,int stock,boolean isVegan){
        super(id,name,price,stock);
        this.isVegan=isVegan;
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}

/* ================= DRINK ================= */

class Drink extends MenuItem {

    private String size;

    public Drink(String id,String name,double price,int stock,String size){
        super(id,name,price,stock);
        this.size=size;
    }

    @Override
    public double calculatePrice() {

        if(size.equalsIgnoreCase("M"))
            return getBasePrice()+5000;

        if(size.equalsIgnoreCase("L"))
            return getBasePrice()+10000;

        return getBasePrice();
    }
}

/* ================= DESSERT ================= */

class Dessert extends MenuItem {

    private int sugarLevel;

    public Dessert(String id,String name,double price,int stock,int sugarLevel){
        super(id,name,price,stock);
        this.sugarLevel=sugarLevel;
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}

/* ================= ORDER ITEM ================= */

class OrderItem {

    private MenuItem item;
    private int quantity;

    public OrderItem(MenuItem item,int quantity){
        this.item=item;
        this.quantity=quantity;
    }

    public MenuItem getItem(){ return item; }
    public int getQuantity(){ return quantity; }

    public double getSubTotal(){
        return item.calculatePrice()*quantity;
    }
}

/* ================= ORDER ================= */

class Order {

    private String orderId;
    private double discount;
    private double serviceFee;
    private LocalDateTime createdAt;
    private OrderStatus status;

    private List<OrderItem> items = new ArrayList<>();

    public Order(String id){
        this.orderId=id;
        this.createdAt=LocalDateTime.now();
        this.status=OrderStatus.PENDING;
    }

    public String getOrderId(){ return orderId; }

    public LocalDateTime getCreatedAt(){ return createdAt; }

    public OrderStatus getStatus(){ return status; }

    public List<OrderItem> getItems(){ return items; }

    public void addItem(MenuItem item,int quantity) throws InsufficientStockException {

        item.reduceStock(quantity);

        items.add(new OrderItem(item,quantity));
    }

    public double calculateTotal(){

        double total = items.stream()
                .mapToDouble(OrderItem::getSubTotal)
                .sum();

        return total - discount + serviceFee;
    }

    public void updateStatus(OrderStatus status){
        this.status=status;
    }
}

/* ================= REPOSITORY ================= */

class MenuRepository {

    private List<MenuItem> menu = new ArrayList<>();

    public void save(MenuItem item){
        menu.add(item);
    }

    public void delete(String id){
        menu.removeIf(i -> i.getId().equals(id));
    }

    public Optional<MenuItem> findById(String id){

        return menu.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    public List<MenuItem> findAll(){
        return menu;
    }
}

class OrderRepository {

    private List<Order> orders = new ArrayList<>();

    public void save(Order order){
        orders.add(order);
    }

    public Optional<Order> findById(String id){

        return orders.stream()
                .filter(o -> o.getOrderId().equals(id))
                .findFirst();
    }

    public List<Order> findAll(){
        return orders;
    }
}

/* ================= SERVICE ================= */

class MenuService {

    private MenuRepository repo;

    public MenuService(MenuRepository repo){
        this.repo=repo;
    }

    public void addMenuItem(MenuItem item){
        repo.save(item);
    }

    public List<MenuItem> searchByName(String name){

        return repo.findAll()
                .stream()
                .filter(i -> i.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<MenuItem> searchByPriceRange(double min,double max){

        return repo.findAll()
                .stream()
                .filter(i -> i.calculatePrice()>=min && i.calculatePrice()<=max)
                .collect(Collectors.toList());
    }
}

class OrderService {

    private OrderRepository repo;

    public OrderService(OrderRepository repo){
        this.repo=repo;
    }

    public Order createOrder(){

        Order order = new Order(UUID.randomUUID().toString());

        repo.save(order);

        return order;
    }

    public void processOrder(String orderId,MenuItem item,int quantity)
            throws InvalidOrderIdException,InsufficientStockException {

        Order order = repo.findById(orderId)
                .orElseThrow(() -> new InvalidOrderIdException("Order not found"));

        order.addItem(item,quantity);
    }

    public double calculateDailyRevenue(LocalDate date){

        return repo.findAll()
                .stream()
                .filter(o -> o.getStatus()==OrderStatus.PAID)
                .filter(o -> o.getCreatedAt().toLocalDate().equals(date))
                .mapToDouble(Order::calculateTotal)
                .sum();
    }

    public List<MenuItem> getTopSellingItems(){

        Map<MenuItem,Integer> sales = new HashMap<>();

        for(Order order : repo.findAll()){

            if(order.getStatus()!=OrderStatus.PAID)
                continue;

            for(OrderItem item : order.getItems()){

                sales.put(item.getItem(),
                        sales.getOrDefault(item.getItem(),0)+item.getQuantity());
            }
        }

        return sales.entrySet()
                .stream()
                .sorted((a,b)->b.getValue()-a.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}