package hackathon.bai1.ra.business;

import hackathon.bai1.ra.entity.ChessPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlayerBusiness {
    List<ChessPlayer> players;


    public void showPlayers() {
        if(players.isEmpty()) {
            System.out.println("No players found");
            return;
        }
        System.out.println("List of players:");
        for(ChessPlayer player : players) {
            players.forEach(c -> System.out.printf("%s  |  %s  | %d  | %d", c.getPlayerID(), c.getPlayerName(), c.getAge(), c.getElo() ));
        }
    }

    public boolean addPlayer(ChessPlayer player) {
        boolean exists = players.stream().anyMatch(c -> Objects.equals(c.getPlayerID(), player.getPlayerID()));
        if (exists) return false;
        players.add(player);
        return true;
    }

    public Optional<ChessPlayer> findPlayerbyID(String playerID) {
        return players.stream().filter(c -> c.getPlayerID().equals(playerID)).findFirst();
    }

    public List<ChessPlayer> searchPlayerByName(String playerName) {
        return players.stream().filter(c -> c.getPlayerName().equals(playerName)).collect(Collectors.toList());
    }

    public boolean deletePlayer(String playerID) {
        Optional<ChessPlayer> optChessPlayer = findPlayerbyID(playerID);
        players.remove(optChessPlayer.get());
        return true;
    }



public List<ChessPlayer> sortDesc(){
        return players.stream().sorted((a,b) -> b.getElo() - a.getElo() )
                .collect(Collectors.toList());
}


public List<ChessPlayer> filter1500elo(){
        return players.stream()
                .filter(c -> c.getElo() >= 1500)
                .collect(Collectors.toList());
}
}
