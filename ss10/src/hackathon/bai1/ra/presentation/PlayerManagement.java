package hackathon.bai1.ra.presentation;

import hackathon.bai1.ra.business.PlayerBusiness;
import hackathon.bai1.ra.entity.ChessPlayer;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class PlayerManagement {
    static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        PlayerBusiness pb = new PlayerBusiness();
        do{
            System.out.println("""
                **************Quan ly co thu************
                1. Hien thi toan bo danh sach co thu
                2. Them moi co thu
                3. Cap nhat sinh vien co thu theo ma
                4. Xoa sinh vien theo ma co thu
                5. Tim kiem co thu theo ten
                6. Loc danh sach co thu xuat sac nhat (>1500 elo)
                7. Sap xep danh sach theo elo giam dan
                8. Thoat
                Moi ban nhap lua chon cua minh
                """);
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    pb.showPlayers();
                    break;
                case 2:
                    do{
                        ChessPlayer c = new ChessPlayer();
                        c.inputData(sc);

                        System.out.println("Bạn có muốn tiếp tục?(Y/N)");
                    }while(sc.nextLine().equalsIgnoreCase("Y"));
                    break;
                case 3:

                    System.out.println("Nhap ma");
                    String id = sc.nextLine();

                    Optional<ChessPlayer> optionalChessPlayer = pb.findPlayerbyID(id);
                    if(optionalChessPlayer.isEmpty()) {
                        System.out.println("Ma khong ton tai");
                        break;
                    }
                    ChessPlayer chessPlayer = optionalChessPlayer.get();

                    System.out.println("Moi nhap ten moi");
                    String name = sc.nextLine();
                    if(!name.isEmpty()) {

                    }
                    break;
                case 4:

                    System.out.println("Nhap ma ");
                    String delID = sc.nextLine();
                    if(!pb.deletePlayer(delID)){
                        System.out.println("Ma khong ton tai");
                    }
                    break;
                case 5:
                    System.out.println("Moi ban nhap ten co thu can tim");
                    String searchName =  sc.nextLine();
                    List<ChessPlayer> result = pb.searchPlayerByName(searchName);
                    System.out.println("Tim thay " + result.size());
                    result.forEach(System.out::println);
                    break;
                case 6:
                    pb.sortDesc().forEach(System.out::println);
                    break;
                case 7:
                    pb.filter1500elo().forEach(System.out::println);
                    break;
                case 8:
                    System.out.println("Thoat");
                    break;
                default:
                    System.out.println("Lua chon khong hop le");
                    break;
            }
        }while(choice != 8);
    }
}
