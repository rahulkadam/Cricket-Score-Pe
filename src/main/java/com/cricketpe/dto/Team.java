package com.cricketpe.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Team extends BaseObj {
    private String name;
    private boolean isBatting;
    private List<Player> playerList;

    public Team(String name) {
        this.name = name;
    }

    public void addPlayer(String name) {
        if (playerList == null) {
            playerList = new ArrayList<Player>();
        }
        Player player = new Player(name);
        player.setTeam(this);
        playerList.add(player);
    }
}
