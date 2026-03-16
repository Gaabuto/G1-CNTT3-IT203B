public class Bai2 {

    static class TicketPool {
        private String roomName;
        private int tickets;
        private int ticketCounter;
        public TicketPool(String roomName, int tickets) {
            this.roomName = roomName;
            this.tickets = tickets;
            this.ticketCounter = tickets;
        }

        public synchronized String sellTicket() {

            while (tickets == 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": Hết vé phòng " + roomName + ", đang chờ...");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            String ticketId = roomName + "-" + String.format("%03d", ticketCounter);
            tickets--;
            ticketCounter++;

            return ticketId;
        }

        public synchronized void addTickets(int amount) {
            tickets += amount;
            System.out.println("Nhà cung cấp: Đã thêm " + amount + " vé vào phòng " + roomName);

            notifyAll();
        }
    }

    static class BookingCounter implements Runnable {

        private TicketPool pool;

        public BookingCounter(TicketPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            while (true) {
                String ticket = pool.sellTicket();
                System.out.println(Thread.currentThread().getName() + " bán vé " + ticket);

                try {
                    Thread.sleep(500);
                } catch (Exception e) {}
            }
        }
    }

    static class Supplier implements Runnable {
        private TicketPool pool;
        public Supplier(TicketPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(5000);
                pool.addTickets(3);
            } catch (Exception e) {}
        }
    }

    public static void main(String[] args) {
        TicketPool roomA = new TicketPool("A", 2);
        Thread counter1 = new Thread(new BookingCounter(roomA), "Quầy 1");
        Thread counter2 = new Thread(new BookingCounter(roomA), "Quầy 2");

        Thread supplier = new Thread(new Supplier(roomA));

        counter1.start();
        counter2.start();
        supplier.start();
    }
}