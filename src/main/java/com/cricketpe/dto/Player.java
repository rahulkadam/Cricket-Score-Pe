package com.cricketpe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Player extends BaseObj {
    private String name;
    private Team team;
    private PlayerScore playerScore;
}
