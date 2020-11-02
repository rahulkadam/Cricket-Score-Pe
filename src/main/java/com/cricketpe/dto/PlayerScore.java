package com.cricketpe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerScore extends BaseObj {
    private int score;
    private int fours;
    private int sixes;
    private int balls;
    private boolean isout;
}
