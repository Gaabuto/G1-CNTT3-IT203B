package project;

public class DigitalProduct extends Product{
    private double fileSizeByMB;

    public DigitalProduct(String id, String name, double price, double fileSizeByMB) {
        super(id, name, price);
        this.fileSizeByMB = fileSizeByMB;
    }

    public double getFileSize() {
        return fileSizeByMB;
    }

    public void setFileSize(double fileSizeByMB) {
        this.fileSizeByMB = fileSizeByMB;
    }
    public void displayInfo() {
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Price: " + getPrice());
        System.out.println("File Size: " + fileSizeByMB + " MB");
    }
}
