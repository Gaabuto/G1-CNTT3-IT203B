package bai04;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TemperatureSensor sensor = new TemperatureSensor();

        Fan fan = new Fan();
        Humidifier humidifier = new Humidifier();

        int choice;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Đăng ký thiết bị");
            System.out.println("2. Set nhiệt độ");
            System.out.println("3. Thoát");
            System.out.print("Chọn: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    sensor.attach(fan);
                    System.out.println("Quạt: Đã đăng ký nhận thông báo");

                    sensor.attach(humidifier);
                    System.out.println("Máy tạo ẩm: Đã đăng ký");
                    break;

                case 2:
                    System.out.print("Nhập nhiệt độ: ");
                    int temp = Integer.parseInt(sc.nextLine());
                    sensor.setTemperature(temp);
                    break;

                case 3:
                    System.out.println("Thoát...");
                    return;

                default:
                    System.out.println("Sai!");
            }
        }while(choice!=3);
    }
}
