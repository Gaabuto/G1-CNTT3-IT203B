package kiemTra;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Product> ListProducts = new ArrayList<>();
        int choice;




        do{
            System.out.println("""
                ========= PRODUCT MANAGEMENTSYSTEM=========
                1. Thêm sản phẩm mới
                2. Hiển thị danh sách sản phẩm
                3. Cập nhật số lượng theo ID
                4. Xóa sản phẩm đã hết hàng
                5. Thoát chương trình\s
                =============================================
                """);
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    int id;
                    while(true){
                        System.out.println("Nhập ID sản phẩm: ");
                        int checkId = Integer.parseInt(sc.nextLine());
                        if(checkId == 0){
                            return;
                        }
                            boolean flag = ListProducts.stream().anyMatch(p -> p.getId() == checkId);
                        if (flag) {
                            System.out.println("ID đã tồn tại. Vui lòng nhập ID khác(bấm 0 để thoát ra ngoài).");
                        } else {
                            id = checkId;
                            break;
                        }

                    }

                    System.out.println("Nhập tên sản phẩm: ");
                    String name = sc.nextLine();

                    System.out.println("Nhập giá sản phẩm: ");
                    double price = Double.parseDouble(sc.nextLine());

                    System.out.println("Nhập số lượng sản phẩm: ");
                    int quantity = Integer.parseInt(sc.nextLine());

                    System.out.println("Nhập danh mục sản phẩm: ");
                    String category = sc.nextLine();

                    Product newProduct = new Product(id, name, price, quantity, category) {};
                    ListProducts.add(newProduct);

                    break;
                case 2:
                    System.out.println("Danh sách sản phẩm:");
                    ListProducts.stream().forEach(System.out::println);
                    break;
                case 3: ;
                    System.out.println("Nhập ID sản phẩm cần cập nhật: ");
                    int updateId = Integer.parseInt(sc.nextLine());

                    break;
                case 4:
                    ListProducts.stream().forEach( p -> {
                        if (p.getQuantity() == 0) {
                            ListProducts.remove(p);
                        }
                    });
                    System.out.println("Sản phẩm đã hết hàng đã được xóa khỏi danh sách.");
                    break;
                case 5:
                    System.out.println("Thoát chương trình. Hẹn gặp lại!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }while(choice != 5);



    }
}
