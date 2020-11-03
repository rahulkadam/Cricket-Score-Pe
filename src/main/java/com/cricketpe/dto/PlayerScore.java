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

    private boolean isbowling;
    private int oversbowled;
    private int ballsbowled;
    private int bowling_wicket;
    private int run_conceded;
    private int extras;
    private int fours_concede;
    private int sixes_conceded;

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


    public void addRunConcede(int runs) {
        this.run_conceded = this.run_conceded + runs;
        this.ballsbowled = this.ballsbowled + 1;

        if (runs == 4) {
            this.fours_concede++;
        }
        if (runs == 6) {
            this.sixes_conceded++;
        }
        completeOver();
    }

    public void addWicket() {
        this.ballsbowled = this.ballsbowled + 1;
        this.bowling_wicket++;
        completeOver();
    }

    public void completeOver() {
        if (this.ballsbowled == 6) {
            this.oversbowled++;
            this.ballsbowled = 0;
            this.isbowling = false;
        }
    }

}
