import java.util.*;

public class Bai3 {
    static class Ticket {
        String ticketId;
        String roomName;
        boolean isSold;

        public Ticket(String id, String room) {
            this.ticketId = id;
            this.roomName = room;
            this.isSold = false;
        }
    }

    static class TicketPool {
        String roomName;
        List<Ticket> tickets = new ArrayList<>();
        int nextId = 1;

        public TicketPool(String roomName, int total) {
            this.roomName = roomName;

            for (int i = 0; i < total; i++) {
                addTicket();
            }
        }

        private void addTicket() {
            String id = roomName + "-" + String.format("%03d", nextId++);
            tickets.add(new Ticket(id, roomName));
        }

        public synchronized Ticket sellTicket() {

            for (Ticket t : tickets) {
                if (!t.isSold) {
                    t.isSold = true;
                    return t;
                }
            }
            return null;
        }

        public synchronized void addTickets(int count) {
            for (int i = 0; i < count; i++) {
                addTicket();
            }
            System.out.println("Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);
        }

        public synchronized int remainingTickets() {

            int count = 0;

            for (Ticket t : tickets) {
                if (!t.isSold) count++;
            }

            return count;
        }
    }

    static class BookingCounter implements Runnable {
        String counterName;
        TicketPool roomA;
        TicketPool roomB;
        int soldCount = 0;
        Random rand = new Random();

        public BookingCounter(String name, TicketPool a, TicketPool b) {
            this.counterName = name;
            this.roomA = a;
            this.roomB = b;
        }

        @Override
        public void run() {
            while (true) {
                int choice = rand.nextInt(2);
                Ticket ticket = null;
                if (choice == 0) {
                    ticket = roomA.sellTicket();
                    if (ticket == null)
                        ticket = roomB.sellTicket();
                } else {
                    ticket = roomB.sellTicket();
                    if (ticket == null)
                        ticket = roomA.sellTicket();
                }

                if (ticket != null) {
                    soldCount++;
                    System.out.println(counterName + " đã bán vé " + ticket.ticketId);
                }

                try {
                    Thread.sleep(200);
                } catch (Exception e) {}
            }
        }
    }

    static class TicketSupplier implements Runnable {

        TicketPool roomA;
        TicketPool roomB;
        int supplyCount;
        int interval;
        int rounds;

        public TicketSupplier(TicketPool a, TicketPool b, int supplyCount, int interval, int rounds) {
            this.roomA = a;
            this.roomB = b;
            this.supplyCount = supplyCount;
            this.interval = interval;
            this.rounds = rounds;
        }

        @Override
        public void run() {

            for (int i = 1; i <= rounds; i++) {

                try {
                    Thread.sleep(interval);
                } catch (Exception e) {}

                roomA.addTickets(supplyCount);
                roomB.addTickets(supplyCount);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        BookingCounter counter1 =
                new BookingCounter("Quầy 1", roomA, roomB);

        BookingCounter counter2 =
                new BookingCounter("Quầy 2", roomA, roomB);

        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);

        TicketSupplier supplier = new TicketSupplier(roomA, roomB, 3, 3000, 3);
        Thread supplierThread = new Thread(supplier);

        t1.start();
        t2.start();
        supplierThread.start();
        supplierThread.join();
        Thread.sleep(5000);

        System.out.println("\n===== TỔNG KẾT =====");
        System.out.println("Quầy 1 bán được: " + counter1.soldCount + " vé");
        System.out.println("Quầy 2 bán được: " + counter2.soldCount + " vé");
        System.out.println("Vé còn lại phòng A: " + roomA.remainingTickets());
        System.out.println("Vé còn lại phòng B: " + roomB.remainingTickets());
        System.exit(0);
    }
}