package com.cricketpe.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Team extends BaseObj {
    private String name;
    private boolean isBatting;
    private List<Player> playerList;

    public Team(String name) {
        this.name = name;
        playerList = new ArrayList<>();
    }

    public void addPlayer(String name) {
        if (playerList == null) {
            playerList = new ArrayList<Player>();
        }
        Player player = new Player(name);
        player.setTeam(this);
        playerList.add(player);
    }

    public List<Player> getCurrentBatsman() {
        return playerList.stream().filter(player -> {
            return player.isPlaying();
        }).collect(Collectors.toList());
    }

    public List<Player> getCurrentBowler() {
        return playerList.stream().filter(player -> {
            return player.isBowling();
        }).collect(Collectors.toList());
    }

    public void addNextPlayerForBatting() {
        Player nextPlayer = playerList.stream()
                .filter(player -> !player.getPlayerScore().isIsout() && !player.getPlayerScore().isPlaying())
                .findFirst().orElse(null);
        if (nextPlayer != null) {
            nextPlayer.setOnStrike(true);
            nextPlayer.setPlaying(true);
        }
    }
}
