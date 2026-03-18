package bai01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        HardwareConnection connection = null;
        Device device = null;
        do {
            System.out.println("""
                    =====MENU CONSOLE=====
                    1. Kết nối phần cứng
                    2. Tạo thiết bị mới
                    3. Bật/Tắt thiết bị vừa tạo
                    4. Thoát
                    """);
            System.out.printf("Nhập lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    connection = HardwareConnection.getInstance();
                    connection.connect();
                    break;
                case 2:
                    System.out.println("==THIẾT BỊ==");
                    System.out.println("1. Đèn");
                    System.out.println("2. Quạt");
                    System.out.println("3. Điều hòa");
                    System.out.printf("Chọn loại thiết bị:");
                    int type = Integer.parseInt(sc.nextLine());

                    DeviceFactory factory = null;

                    switch (type) {
                        case 1:
                            factory = new LightFactory();
                            break;
                        case 2:
                            factory = new FanFactory();
                            break;
                        case 3:
                            factory = new AirConditionerFactory();
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ!");
                            continue;
                    }

                    device = factory.createDevice();
                    System.out.println("Tạo thiết bị thành công!");
                    break;
                case 3:
                    System.out.println("1. Bật");
                    System.out.println("2. Tắt");
                    System.out.print("Chọn: ");
                    int action = Integer.parseInt(sc.nextLine());

                    if (action == 1) {
                        device.turnOn();
                    } else if (action == 2) {
                        device.turnOff();
                    } else {
                        System.out.println("Lựa chọn không hợp lệ!");
                    }
                    break;
                case 4:
                    System.out.println("Thoát chương trình");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Hãy chọn lại");
                    break;
            }
        }while(choice!=4);
    }
}
