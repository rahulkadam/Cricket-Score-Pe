package com.cricketpe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = {"team"})
public class Player extends BaseObj {
    private String name;
    private Team team;
    private PlayerScore playerScore;

    public Player(String name) {
        this.name = name;
        playerScore = new PlayerScore();
    }

    public boolean isPlaying() {
       return  playerScore.isPlaying();
    }

    public void setPlaying(boolean playing) {
        playerScore.setPlaying(playing);
    }

    public void setOnStrike(boolean onStroke) {
        playerScore.setOnStrike(onStroke);
    }

    public boolean isOnStrike() {
        return playerScore.isOnStrike();
    }
}
