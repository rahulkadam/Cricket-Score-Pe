package com.cricketpe.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Team extends BaseObj {
    private String name;
    private List<Player> playerList;
}
