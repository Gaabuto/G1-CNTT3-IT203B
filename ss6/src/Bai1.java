
public class Bai1 {

    // ===== Lớp quản lý vé =====
    static class TicketPool {
        private String roomName;
        private int tickets;

        public TicketPool(String roomName, int tickets) {
            this.roomName = roomName;
            this.tickets = tickets;
        }
        public String sellTicket() {
            if (tickets > 0) {
                String ticketId = roomName + "-00" + tickets;
                tickets--;
                return ticketId;
            }
            return null;
        }
        public void returnTicket() {
            tickets++;
        }

        public String getRoomName() {
            return roomName;
        }
    }

    // ===== Lớp quầy bán vé =====
    static class BookingCounter implements Runnable {

        private String counterName;
        private TicketPool roomA;
        private TicketPool roomB;
        private boolean reverseOrder;

        public BookingCounter(String name, TicketPool a, TicketPool b, boolean reverse) {
            this.counterName = name;
            this.roomA = a;
            this.roomB = b;
            this.reverseOrder = reverse;
        }

        public void sellCombo() {

            if (reverseOrder) {

                synchronized (roomB) {
                    System.out.println(counterName + ": Đã lấy vé từ phòng B");
                    try { Thread.sleep(100); } catch (Exception e) {}
                    System.out.println(counterName + ": Đang chờ vé phòng A...");
                    synchronized (roomA) {
                        String ticketA = roomA.sellTicket();
                        String ticketB = roomB.sellTicket();
                        if (ticketA != null && ticketB != null) {
                            System.out.println(counterName + " bán combo thành công: " + ticketA + " & " + ticketB);
                        } else {
                            System.out.println(counterName + ": Bán combo thất bại");
                        }
                    }
                }

            } else {

                synchronized (roomA) {
                    System.out.println(counterName + ": Đã lấy vé từ phòng A");

                    try { Thread.sleep(100); } catch (Exception e) {}

                    System.out.println(counterName + ": Đang chờ vé phòng B...");

                    synchronized (roomB) {

                        String ticketA = roomA.sellTicket();
                        String ticketB = roomB.sellTicket();

                        if (ticketA != null && ticketB != null) {
                            System.out.println(counterName + " bán combo thành công: " + ticketA + " & " + ticketB);
                        } else {
                            System.out.println(counterName + ": Bán combo thất bại");
                        }
                    }
                }
            }
        }

        @Override
        public void run() {
            sellCombo();
        }
    }

    // ===== Hàm main =====
    public static void main(String[] args) {
        TicketPool roomA = new TicketPool("A", 2);
        TicketPool roomB = new TicketPool("B", 2);
        Thread counter1 = new Thread(
                new BookingCounter("Quầy 1", roomA, roomB, false));
        Thread counter2 = new Thread(
                new BookingCounter("Quầy 2", roomA, roomB, true));

        counter1.start();
        counter2.start();
    }
}