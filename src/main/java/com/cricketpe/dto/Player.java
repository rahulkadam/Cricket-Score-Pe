package com.cricketpe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Player extends BaseObj {
    private String name;
    private Team team;
    private PlayerScore playerScore;

    public Player(String name) {
        this.name = name;
        playerScore = new PlayerScore();
    }

    public boolean getIsPlayerBatting() {
       return  playerScore.isPlaying();
    }

    public boolean isOnStrike() {
        return playerScore.isOnStrike();
    }
}
