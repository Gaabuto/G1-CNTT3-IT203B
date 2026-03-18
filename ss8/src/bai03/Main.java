package bai03;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        RemoteControl remote = new RemoteControl();

        int choice;
        do{
            System.out.println("\n===== MENU =====");
            System.out.println("1. Gán command");
            System.out.println("2. Nhấn nút");
            System.out.println("3. Undo");
            System.out.println("4. Thoát");
            System.out.print("Chọn: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("1. Bật đèn");
                    System.out.println("2. Tắt đèn");
                    System.out.println("3. Set điều hòa");
                    int c = Integer.parseInt(sc.nextLine());

                    if (c == 1) {
                        remote.setCommand(new LightOnCommand(light));
                    } else if (c == 2) {
                        remote.setCommand(new LightOffCommand(light));
                    } else if (c == 3) {
                        System.out.print("Nhập nhiệt độ: ");
                        int temp = Integer.parseInt(sc.nextLine());
                        remote.setCommand(new ACSetTempCommand(ac, temp));
                    } else {
                        System.out.println("Sai!");
                    }
                    break;

                case 2:
                    remote.pressButton();
                    break;

                case 3:
                    remote.undo();
                    break;

                case 4:
                    System.out.println("Thoát...");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }
        }while(choice!=4);
    }
}
