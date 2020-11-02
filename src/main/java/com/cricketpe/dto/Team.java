package com.cricketpe.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Team extends BaseObj {
    private String name;
    private boolean isBatting;
    private List<Player> playerList;

    public void addPlayer(String name) {
        if (playerList == null) {
            playerList = new ArrayList<Player>();
        }
        playerList.add(new Player(name));
    }
}
