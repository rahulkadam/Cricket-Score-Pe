package com.cricketpe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Match {
    private String description;
    private String result;
    private int playerCount;
    private int overCount;
    private Team team1;
    private Team team2;
    private TeamScore team1Score;
    private TeamScore team2Score;
    private boolean autoInput;

    public Team getBattingTeam() {
        if (team1.isBatting()) {
            return team1;
        }
        return team2;
    }

    public TeamScore getBattingTeamScore() {
        if (team1.isBatting()) {
            return team1Score;
        }
        return team2Score;
    }

}
