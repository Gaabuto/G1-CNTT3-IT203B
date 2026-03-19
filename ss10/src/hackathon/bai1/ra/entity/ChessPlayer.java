package hackathon.bai1.ra.entity;

import java.util.Scanner;

public class ChessPlayer {
    private String playerID;
    private String playerName;
    private int age;
    private int elo;

    public ChessPlayer() {
    }

    public ChessPlayer(String playerID, String playerName, int age, int elo) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.age = age;
        this.elo = elo;
    }



    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    public void inputData(Scanner scanner){
        boolean flag = true;
        while(true){
            System.out.println("Enter Player ID");
            this.playerID = scanner.nextLine();
            if(this.playerID.matches("CT[0-9]{3}")){
                break;
            }
        }
        while(true){
            System.out.println("Enter Player Name");
            this.playerName = scanner.nextLine();
            if(!(this.playerName == null || this.playerName.trim().isEmpty())){
                break;
            }
        };
        while(true) {
            System.out.println("Enter Age");
            this.age = Integer.parseInt(scanner.nextLine());
            if(this.age > 17){
                break;
            }
        };
        while (true){
            System.out.println("Enter Elo");
            this.elo = Integer.parseInt(scanner.nextLine());
            if(this.elo > 0) break;
            System.out.println("So elo phai lon hon 0");
        }
    }

    public void displayData(Scanner scanner){
        System.out.println("Player ID: " + this.playerID);
        System.out.println("Player Name: " + this.playerName);
        System.out.println("Age: " + this.age);
        System.out.println("Elo: " + this.elo);
    }
}
