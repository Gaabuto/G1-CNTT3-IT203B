package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();
        int choice;
        do{
            System.out.println("""
                ---------------------- QUẢN LÝ SẢN PHẨM----------------------
                1. Thêm mới sản phẩm
                2. Xem danh sách sản phẩm
                3. Cập nhật thông tin sản phẩm
                4. Xoá sản phẩm
                5. Thoát
                -------------------------------------------------------------
                Lựa chọn của bạn:
                
                """);
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:  ProductDatabase.createProduct(products, scanner);
                case 2:  ProductDatabase.showProducts(products);
                case 3:  ProductDatabase.editProduct(products, scanner);
                case 4:  ProductDatabase.deleteProduct(products, scanner);
                case 5: System.out.println("Thoát chương trình. Hẹn gặp lại!");
                default: System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }while(choice != 5);
    }


}
