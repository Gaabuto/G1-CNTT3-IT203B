import java.util.*;

public class Bai5 {

    static class Ticket {
        String ticketId;
        String roomName;

        boolean isSold = false;
        boolean isHeld = false;
        boolean isVIP = false;

        long holdExpiryTime = 0;
        String heldBy = null;

        public Ticket(String id, String room) {
            this.ticketId = id;
            this.roomName = room;
        }
    }

    static class TicketPool {
        String roomName;
        List<Ticket> tickets = new ArrayList<>();
        public TicketPool(String roomName, int total) {
            this.roomName = roomName;
            for (int i = 1; i <= total; i++) {

                String id = roomName + "-" + String.format("%03d", i);
                tickets.add(new Ticket(id, roomName));
            }
        }

        public synchronized Ticket holdTicket(String counter, boolean vip) {
            for (Ticket t : tickets) {
                if (!t.isSold && !t.isHeld) {

                    t.isHeld = true;
                    t.isVIP = vip;
                    t.heldBy = counter;
                    t.holdExpiryTime = System.currentTimeMillis() + 5000;

                    System.out.println(counter +
                            ": Đã giữ vé " + t.ticketId +
                            (vip ? " (VIP)" : "") +
                            ". Thanh toán trong 5s");

                    return t;
                }
            }

            return null;
        }

        public synchronized boolean sellHeldTicket(String counter, Ticket ticket) {
            if (ticket != null && ticket.isHeld && !ticket.isSold && counter.equals(ticket.heldBy)) {
                ticket.isSold = true;
                ticket.isHeld = false;

                System.out.println(counter +
                        ": Thanh toán thành công vé " + ticket.ticketId);

                return true;
            }

            return false;
        }

        public synchronized void releaseExpiredTickets() {
            long now = System.currentTimeMillis();
            for (Ticket t : tickets) {
                if (t.isHeld && !t.isSold && now > t.holdExpiryTime) {
                    System.out.println("TimeoutManager: Vé " + t.ticketId + " hết hạn giữ, trả lại kho");
                    t.isHeld = false;
                    t.heldBy = null;
                }
            }
        }
    }

    static class BookingCounter implements Runnable {

        String counterName;
        List<TicketPool> pools;
        Random rand = new Random();

        public BookingCounter(String name, List<TicketPool> pools) {
            this.counterName = name;
            this.pools = pools;
        }

        @Override
        public void run() {

            while (true) {

                TicketPool pool = pools.get(rand.nextInt(pools.size()));

                boolean vip = rand.nextBoolean();

                Ticket ticket = pool.holdTicket(counterName, vip);

                if (ticket != null) {

                    try {
                        Thread.sleep(3000); // khách suy nghĩ
                    } catch (Exception e) {}

                    pool.sellHeldTicket(counterName, ticket);
                }

                try {
                    Thread.sleep(500);
                } catch (Exception e) {}
            }
        }
    }

    static class TimeoutManager implements Runnable {
        List<TicketPool> pools;

        public TimeoutManager(List<TicketPool> pools) {
            this.pools = pools;
        }

        @Override
        public void run() {

            while (true) {
                for (TicketPool p : pools) {

                    p.releaseExpiredTickets();
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}
            }
        }
    }

    public static void main(String[] args) {
        TicketPool roomA = new TicketPool("A", 5);
        TicketPool roomB = new TicketPool("B", 6);
        TicketPool roomC = new TicketPool("C", 4);

        List<TicketPool> pools =
                Arrays.asList(roomA, roomB, roomC);

        for (int i = 1; i <= 5; i++) {
            Thread t = new Thread(
                    new BookingCounter("Quầy " + i, pools));

            t.start();
        }
        Thread manager =
                new Thread(new TimeoutManager(pools));

        manager.start();
    }
}