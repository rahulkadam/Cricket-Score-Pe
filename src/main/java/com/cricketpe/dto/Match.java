package com.cricketpe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Match {
    private String description;
    private String result;
    private int playerCount;
    private int overCount;
    private Team team1;
    private Team team2;
    private TeamScore team1Score;
    private TeamScore team2Score;
}
