package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDatabase {
    List<Product> products = new ArrayList<>();

    static void createProduct(List<Product> products, Scanner scanner) {
        System.out.println("Nhập loại sản phẩm (1: Vật lý, 2: Kỹ thuật số): ");
        int type = Integer.parseInt(scanner.nextLine());
        String id;
        while (true) {
            System.out.println("Nhập ID sản phẩm: ");
            String uncheckedID = scanner.nextLine();
            boolean exists = products.stream().anyMatch(p -> p.getId().equals(uncheckedID));
            if (exists) {
                System.out.println("ID đã tồn tại. Vui lòng nhập lại.");
            } else {
                id = uncheckedID;
                break;
            }
        }
        System.out.println("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        System.out.println("Nhập giá sản phẩm: ");
        double price = Double.parseDouble(scanner.nextLine());
        if (type == 1) {
            System.out.println("Nhập trọng lượng sản phẩm (kg): ");
            double weight = Double.parseDouble(scanner.nextLine());
            products.add(new PhysicalProduct(id, name, price, weight));
            System.out.println("Sản phẩm đã được thêm vào cơ sở dữ liệu.");
        } else if (type == 2) {
            System.out.println("Nhập dung lượng file sản phẩm (MB): ");
            double fileSize = Double.parseDouble(scanner.nextLine());
            products.add(new DigitalProduct(id, name, price, fileSize));
            System.out.println("Sản phẩm đã được thêm vào cơ sở dữ liệu.");
        } else {
            System.out.println("Loại sản phẩm không hợp lệ.");
        }
    }

    static void showProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào trong cơ sở dữ liệu.");
        } else {
            for (Product product : products) {
                if (product instanceof PhysicalProduct) {
                    ((PhysicalProduct) product).displayInfo();
                } else if (product instanceof DigitalProduct) {
                    ((DigitalProduct) product).displayInfo();
                }
                System.out.println("-----------------------------");
            }
        }
    }

    static void editProduct(List<Product> products, Scanner scanner) {
        System.out.println("Mời bạn nhập ID sản phẩm cần cập nhật: ");
        String editId = scanner.nextLine();
        Product productToEdit = products.stream().filter(p -> p.getId() == editId).findFirst().orElse(null);

        if (productToEdit == null) {
            System.out.println("Không tìm thấy sản phẩm với ID đã nhập.");
            return;
        }
        System.out.println("Nhập tên sản phẩm mới: ");
        String newName = scanner.nextLine();
        System.out.println("Nhập giá sản phẩm mới: ");
        double newPrice = Double.parseDouble(scanner.nextLine());
        productToEdit.setName(newName);
        productToEdit.setPrice(newPrice);
        if (productToEdit instanceof PhysicalProduct) {
            System.out.println("Nhập trọng lượng sản phẩm mới (kg): ");
            double newWeight = Double.parseDouble(scanner.nextLine());
            ((PhysicalProduct) productToEdit).setWeight(newWeight);
            System.out.println("Sản phẩm đã được cập nhật.");
        } else if (productToEdit instanceof DigitalProduct) {
            System.out.println("Nhập dung lượng file sản phẩm mới (MB): ");
            double newFileSize = Double.parseDouble(scanner.nextLine());
            ((DigitalProduct) productToEdit).setFileSize(newFileSize);
            System.out.println("Sản phẩm đã được cập nhật.");
        }
    }

    public static void deleteProduct(List<Product> products, Scanner scanner) {
        System.out.println("Mời bạn nhập ID sản phẩm cần xoá: ");
        String deleteId = scanner.nextLine();
        Product productToDelete = products.stream().filter(p -> p.getId() == deleteId).findFirst().orElse(null);

        if (productToDelete == null) {
            System.out.println("Không tìm thấy sản phẩm với ID đã nhập.");
            return;
        }
        products.remove(productToDelete);
        System.out.println("Sản phẩm đã được xoá khỏi cơ sở dữ liệu.");
    }
}