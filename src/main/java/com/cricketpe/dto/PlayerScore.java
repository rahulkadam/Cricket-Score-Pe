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
}
