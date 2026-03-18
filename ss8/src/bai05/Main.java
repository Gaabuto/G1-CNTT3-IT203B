package bai05;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        TemperatureSensor sensor = new TemperatureSensor();

        //Đăng ký observer
        sensor.attach(fan);
        sensor.attach(ac);

        Command sleepCommand = new SleepModeCommand(light, fan, ac);

        int choice;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Kích hoạt chế độ ngủ");
            System.out.println("2. Thay đổi nhiệt độ");
            System.out.println("3. Thoát");
            System.out.print("Chọn: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    sleepCommand.execute();
                    break;

                case 2:
                    System.out.print("Nhập nhiệt độ: ");
                    int temp = Integer.parseInt(sc.nextLine());
                    sensor.setTemperature(temp);
                    break;

                case 3:
                    System.out.println("Thoát...");
                    break;

                default:
                    System.out.println("Nhập sai");
                    break;
            }
        }while(choice!=3);
    }
}
