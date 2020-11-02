package com.cricketpe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class PlayerScore extends BaseObj {
    private int score;
    private int fours;
    private int sixes;
    private int balls;
    private boolean isout;
    private boolean isPlaying;
    private boolean isOnStrike;

    public void addScore(int runs) {
        this.score = this.score + runs;
        this.balls = this.balls + 1;
        if (runs == 4) {
            this.fours++;
        }
        if (runs == 6) {
            this.sixes++;
        }
    }

    public void addOut() {
        this.isout = true;
        this.isPlaying = false;
        this.isOnStrike = false;
        this.balls++;
    }
}
